package service;

import model.Article;
import repository.ArticleRepository;
import util.Validation;
import java.util.List;

public class ArticleService {
    private final ArticleRepository repo;

    public ArticleService(String dataDir) {
        this.repo = new ArticleRepository(dataDir);
    }

    public List<Article> listAll() { return repo.findAll(); }

    public Article find(String name) { return repo.findByName(name); }

    public boolean addArticle(Article a) throws IllegalArgumentException {
        if (!Validation.notEmpty(a.getName())) throw new IllegalArgumentException("El nom no pot estar buit");
        if (!Validation.notEmpty(a.getPrice())) throw new IllegalArgumentException("El preu no pot estar buit");
        if (!Validation.validLength(a.getName(), 40)) throw new IllegalArgumentException("Nom d'article massa llarg (40)");
        if (!Validation.noForbiddenChars(a.getName())) throw new IllegalArgumentException("El nom no pot contenir el caràcter ';'");
        if (!Validation.validPrice(a.getPrice())) throw new IllegalArgumentException("Preu invàlid (màxim 6 dígits i 2 decimals)");
        if (!Validation.noForbiddenChars(a.getPrice())) throw new IllegalArgumentException("El preu no pot contenir el caràcter ';'");
        return repo.save(a);
    }

    public boolean deleteArticle(String name) {
        return repo.delete(name);
    }

    /**
     * Actualiza un artículo existente
     */
    public boolean updateArticle(String oldName, Article newArticle) throws IllegalArgumentException {
        if (!Validation.notEmpty(newArticle.getName())) throw new IllegalArgumentException("El nom no pot estar buit");
        if (!Validation.notEmpty(newArticle.getPrice())) throw new IllegalArgumentException("El preu no pot estar buit");
        if (!Validation.validLength(newArticle.getName(), 40)) throw new IllegalArgumentException("Nom d'article massa llarg (40)");
        if (!Validation.noForbiddenChars(newArticle.getName())) throw new IllegalArgumentException("El nom no pot contenir el caràcter ';'");
        if (!Validation.validPrice(newArticle.getPrice())) throw new IllegalArgumentException("Preu invàlid (màxim 6 dígits i 2 decimals)");
        if (!Validation.noForbiddenChars(newArticle.getPrice())) throw new IllegalArgumentException("El preu no pot contenir el caràcter ';'");
        return repo.update(oldName, newArticle);
    }
}
