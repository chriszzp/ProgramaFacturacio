package model;

public class InvoiceLine {
    private static final int MAX_INVOICE_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 40;
    private static final int MAX_PRICE_LENGTH = 6;

    private String invoiceId;
    private int quantity;
    private String name;
    private String price; // string per spec

    public InvoiceLine() {}

    public InvoiceLine(String invoiceId, int quantity, String name, String price) {
        setInvoiceId(invoiceId);
        setQuantity(quantity);
        setName(name);
        setPrice(price);
    }

    public String getInvoiceId() { return invoiceId; }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = truncate(invoiceId, MAX_INVOICE_ID_LENGTH);
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        // Ensure quantity is within valid range
        if (quantity < 1) this.quantity = 1;
        else if (quantity > 9999) this.quantity = 9999;
        else this.quantity = quantity;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = truncate(name, MAX_NAME_LENGTH);
    }

    // alias getter/setter para compatibilidad con llamadas a getArticleName()/setArticleName()
    public String getArticleName() { return name; }

    public void setArticleName(String articleName) {
        setName(articleName);
    }

    public String getPrice() { return price; }

    public void setPrice(String price) {
        if (price == null) {
            this.price = "";
        } else {
            // Remove decimal point for length check
            String plainPrice = price.replace(".", "");
            if (plainPrice.length() > MAX_PRICE_LENGTH) {
                // Truncate maintaining decimal point if exists
                int dotIndex = price.indexOf('.');
                if (dotIndex >= 0 && dotIndex <= MAX_PRICE_LENGTH) {
                    this.price = price.substring(0, Math.min(price.length(), MAX_PRICE_LENGTH + 1));
                } else {
                    this.price = price.substring(0, MAX_PRICE_LENGTH);
                }
            } else {
                this.price = price;
            }
        }
    }

    // Helper method to truncate strings
    private String truncate(String s, int maxLength) {
        if (s == null) return "";
        if (s.length() > maxLength) return s.substring(0, maxLength);
        return s;
    }

    public String toCSV() {
        return String.join(";", escape(invoiceId), Integer.toString(quantity), escape(name), escape(price));
    }

    public static InvoiceLine fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(";", -1); // -1 to keep empty trailing fields
        if (parts.length < 4) return null;
        try {
            return new InvoiceLine(unescape(parts[0]), Integer.parseInt(parts[1]), unescape(parts[2]), unescape(parts[3]));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Escapa caracteres especiales de manera robusta
    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace(";", "\\;").replace("\n", "\\n").replace("\r", "\\r");
    }

    // Desescapa caracteres especiales
    private static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\r", "\r").replace("\\n", "\n").replace("\\;", ";").replace("\\\\", "\\");
    }

    @Override
    public String toString() { return String.format("%d x %s @ %s", quantity, name, price); }
}
