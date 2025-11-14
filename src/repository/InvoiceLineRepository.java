package repository;

import model.InvoiceLine;
import java.io.*;
import java.util.*;

public class InvoiceLineRepository {
    private final File file;

    public InvoiceLineRepository(String dataDir) {
        this.file = new File(dataDir, "linies_factura.txt");
        try { file.getParentFile().mkdirs(); file.createNewFile(); } catch (IOException e) {}
    }

    public List<InvoiceLine> findByInvoiceId(String invoiceId) {
        List<InvoiceLine> res = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            while ((line = r.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                InvoiceLine il = InvoiceLine.fromCSV(line);
                if (il != null && invoiceId.equals(il.getInvoiceId())) res.add(il);
            }
        } catch (Exception e) {
            System.err.println("Error leyendo lineas factura: " + e.getMessage());
        }
        return res;
    }

    public boolean saveLine(InvoiceLine line) {
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
            w.write(line.toCSV()); w.write(System.lineSeparator());
            return true;
        } catch (Exception e) {
            System.err.println("Error guardando linea factura: " + e.getMessage());
            return false;
        }
    }

    public boolean saveLines(List<InvoiceLine> lines) {
        boolean ok = true;
        for (InvoiceLine l : lines) ok = ok && saveLine(l);
        return ok;
    }
}
