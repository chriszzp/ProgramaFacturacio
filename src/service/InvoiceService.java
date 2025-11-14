package service;

import model.Invoice;
import model.InvoiceLine;
import repository.InvoiceRepository;
import repository.InvoiceLineRepository;
import repository.ClientRepository;
import util.Validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class InvoiceService {
    private final InvoiceRepository invoiceRepo;
    private final InvoiceLineRepository lineRepo;
    private final ClientRepository clientRepo;

    public InvoiceService(String dataDir) {
        this.invoiceRepo = new InvoiceRepository(dataDir);
        this.lineRepo = new InvoiceLineRepository(dataDir);
        this.clientRepo = new ClientRepository(dataDir);
    }

    public List<Invoice> listAll() { return invoiceRepo.findAll(); }

    public Invoice find(String id) { return invoiceRepo.findById(id); }

    public List<InvoiceLine> findLines(String invoiceId) { return lineRepo.findByInvoiceId(invoiceId); }

    private String nextId() {
        List<Invoice> all = invoiceRepo.findAll();
        int max = 0;
        for (Invoice i : all) {
            String s = i.getId().replaceAll("[^0-9]", "");
            try { int v = Integer.parseInt(s); if (v > max) max = v; } catch (Exception e) { e.printStackTrace(); }
        }
        return String.format("F%05d", max + 1);
    }

    public Invoice createInvoice(String date, String clientDni, List<InvoiceLine> lines, String ivaPercent) throws IllegalArgumentException {
        // validations
        if (clientRepo.findByDni(clientDni) == null) throw new IllegalArgumentException("Client no existeix: " + clientDni);
        if (lines == null || lines.isEmpty()) throw new IllegalArgumentException("La factura ha de tenir almenys 1 línia");
        if (lines.size() > 10) throw new IllegalArgumentException("La factura no pot tenir més de 10 línies");

        for (InvoiceLine l : lines) {
            if (l.getQuantity() < 1 || l.getQuantity() > 9999) throw new IllegalArgumentException("Quantitat invàlida");
            if (!Validation.validLength(l.getName(), 40)) throw new IllegalArgumentException("Nom d'article massa llarg");
            if (!Validation.validPrice(l.getPrice())) throw new IllegalArgumentException("Preu de línia invàlid");
        }

        // create invoice id
        String id = nextId();
        Invoice inv = new Invoice(id, date, clientDni, ivaPercent);
        boolean saved = invoiceRepo.save(inv);
        if (!saved) throw new IllegalArgumentException("No s'ha pogut desar la factura (id duplicat?)");

        // save lines
        for (InvoiceLine l : lines) {
            l.setInvoiceId(id);
            boolean ok = lineRepo.saveLine(l);
            if (!ok) throw new IllegalArgumentException("No s'ha pogut desar una línia de la factura");
        }
        return inv;
    }

    public Totals calculateTotals(List<InvoiceLine> lines, String ivaPercent) {
        BigDecimal base = BigDecimal.ZERO;
        for (InvoiceLine l : lines) {
            BigDecimal price = new BigDecimal(l.getPrice());
            BigDecimal qty = new BigDecimal(l.getQuantity());
            base = base.add(price.multiply(qty));
        }
        BigDecimal iva = BigDecimal.ZERO;
        try {
            BigDecimal p = new BigDecimal(ivaPercent);
            iva = base.multiply(p).divide(new BigDecimal(100), 10, RoundingMode.HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigDecimal total = base.add(iva);
        base = base.setScale(2, RoundingMode.HALF_UP);
        iva = iva.setScale(2, RoundingMode.HALF_UP);
        total = total.setScale(2, RoundingMode.HALF_UP);
        return new Totals(base, iva, total);
    }

    public static class Totals {
        public final BigDecimal base;
        public final BigDecimal iva;
        public final BigDecimal total;
        public Totals(BigDecimal base, BigDecimal iva, BigDecimal total) {
            this.base = base; this.iva = iva; this.total = total;
        }
    }
}
