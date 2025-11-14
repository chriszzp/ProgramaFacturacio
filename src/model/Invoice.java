package model;

public class Invoice {
    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_DATE_LENGTH = 10;
    private static final int MAX_DNI_LENGTH = 9;
    private static final int MAX_IVA_LENGTH = 3;

    private String id;
    private String date; // YYYY-MM-DD
    private String clientDni;
    private String iva; // percent as string, e.g., "21"

    public Invoice() {}

    public Invoice(String id, String date, String clientDni, String iva) {
        setId(id);
        setDate(date);
        setClientDni(clientDni);
        setIva(iva);
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = truncate(id, MAX_ID_LENGTH);
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = truncate(date, MAX_DATE_LENGTH);
    }

    public String getClientDni() { return clientDni; }

    public void setClientDni(String clientDni) {
        this.clientDni = truncate(clientDni, MAX_DNI_LENGTH);
    }

    public String getIva() { return iva; }

    public void setIva(String iva) {
        this.iva = truncate(iva, MAX_IVA_LENGTH);
    }

    // Helper method to truncate strings
    private String truncate(String s, int maxLength) {
        if (s == null) return "";
        if (s.length() > maxLength) return s.substring(0, maxLength);
        return s;
    }

    public String toCSV() {
        return String.join(";", escape(id), escape(date), escape(clientDni), escape(iva));
    }

    public static Invoice fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(";", -1); // -1 to keep empty trailing fields
        if (parts.length < 4) return null;
        return new Invoice(unescape(parts[0]), unescape(parts[1]), unescape(parts[2]), unescape(parts[3]));
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
    public String toString() { return String.format("%s | %s | %s | IVA %s%%", id, date, clientDni, iva); }
}
