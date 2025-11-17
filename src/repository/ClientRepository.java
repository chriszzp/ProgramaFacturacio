package repository;

import model.Client;
import java.io.*;
import java.util.*;

public class ClientRepository {
    private final File file;

    public ClientRepository(String dataDir) {
        this.file = new File(dataDir, "clients.txt");
        try { file.getParentFile().mkdirs(); file.createNewFile(); } catch (IOException e) {}
    }

    public List<Client> findAll() {
        List<Client> res = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int lineNumber = 0;
            while ((line = r.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;
                Client c = Client.fromCSV(line);
                if (c != null) {
                    res.add(c);
                } else {
                    System.err.println("Avís: Línia " + lineNumber + " de clients.txt corrupta o invàlida: " + line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error llegint clients.txt: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    public Client findByDni(String dni) {
        for (Client c : findAll()) if (dni.equals(c.getDni())) return c;
        return null;
    }

    public boolean save(Client client) {
        // prevent duplicate DNI
        if (findByDni(client.getDni()) != null) return false;
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            w.write(client.toCSV()); w.write(System.lineSeparator());
            return true;
        } catch (Exception e) {
            System.err.println("Error guardando client: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String dni) {
        List<Client> all = findAll();
        boolean found = false;
        List<Client> remaining = new ArrayList<>();

        for (Client c : all) {
            if (!dni.equals(c.getDni())) {
                remaining.add(c);
            } else {
                found = true;
            }
        }

        if (!found) return false;

        // Rewrite file without the deleted client
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"))) {
            for (Client c : remaining) {
                w.write(c.toCSV());
                w.write(System.lineSeparator());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error eliminant client: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un cliente existente.
     * Busca por DNI y reemplaza los datos.
     */
    public boolean update(String dni, Client newClient) {
        List<Client> all = findAll();
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            if (dni.equals(all.get(i).getDni())) {
                all.set(i, newClient);
                found = true;
                break;
            }
        }

        if (!found) return false;

        // Reescribir archivo con los datos actualizados
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"))) {
            for (Client c : all) {
                w.write(c.toCSV());
                w.write(System.lineSeparator());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error actualitzant client: " + e.getMessage());
            return false;
        }
    }
}
