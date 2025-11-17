package repository;

import model.Article;
import java.io.*;
import java.util.*;

public class ArticleRepository {
    private final File file;

    public ArticleRepository(String dataDir) {
        this.file = new File(dataDir, "articles.txt");
        try { file.getParentFile().mkdirs(); file.createNewFile(); } catch (IOException e) {}
    }

    public List<Article> findAll() {
        List<Article> res = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int lineNumber = 0;
            while ((line = r.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                Article a = Article.fromCSV(line);
                if (a != null) {
                    res.add(a);
                } else {
                    System.err.println("Avís: Línia " + lineNumber + " d'articles.txt corrupta o invàlida: " + line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error llegint articles.txt: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    public Article findByName(String name) {
        for (Article a : findAll()) if (name.equals(a.getName())) return a;
        return null;
    }

    public boolean save(Article article) {
        if (findByName(article.getName()) != null) return false;
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            w.write(article.toCSV()); w.write(System.lineSeparator());
            return true;
        } catch (Exception e) {
            System.err.println("Error guardando article: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String name) {
        List<Article> all = findAll();
        boolean found = false;
        List<Article> remaining = new ArrayList<>();

        for (Article a : all) {
            if (!name.equals(a.getName())) {
                remaining.add(a);
            } else {
                found = true;
            }
        }

        if (!found) return false;

        // Rewrite file without the deleted article
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"))) {
            for (Article a : remaining) {
                w.write(a.toCSV());
                w.write(System.lineSeparator());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error eliminant article: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un artículo existente.
     * Busca por nombre y reemplaza los datos.
     */
    public boolean update(String oldName, Article newArticle) {
        List<Article> all = findAll();
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            if (oldName.equals(all.get(i).getName())) {
                all.set(i, newArticle);
                found = true;
                break;
            }
        }

        if (!found) return false;

        // Reescribir archivo con los datos actualizados
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"))) {
            for (Article a : all) {
                w.write(a.toCSV());
                w.write(System.lineSeparator());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error actualitzant article: " + e.getMessage());
            return false;
        }
    }
}
