package repository;

import model.Invoice;
import java.io.*;
import java.util.*;

public class InvoiceRepository {
    private final File file;

    public InvoiceRepository(String dataDir) {
        this.file = new File(dataDir, "factures.txt");
        try { file.getParentFile().mkdirs(); file.createNewFile(); } catch (IOException e) {}
    }

    public List<Invoice> findAll() {
        List<Invoice> res = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            while ((line = r.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Invoice inv = Invoice.fromCSV(line);
                if (inv != null) res.add(inv);
            }
        } catch (Exception e) {
            System.err.println("Error leyendo factures: " + e.getMessage());
        }
        return res;
    }

    public Invoice findById(String id) {
        for (Invoice i : findAll()) if (id.equals(i.getId())) return i;
        return null;
    }

    public boolean save(Invoice invoice) {
        if (findById(invoice.getId()) != null) return false;
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            w.write(invoice.toCSV()); w.write(System.lineSeparator());
            return true;
        } catch (Exception e) {
            System.err.println("Error guardando factura: " + e.getMessage());
            return false;
        }
    }
}
