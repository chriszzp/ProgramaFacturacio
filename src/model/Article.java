package model;

public class Article {
    private static final int MAX_NAME_LENGTH = 40;
    private static final int MAX_PRICE_LENGTH = 6;

    private String name;
    private String price; // stored as string per spec (max 6 chars)

    public Article() {}
    public Article(String name, String price) {
        setName(name);
        setPrice(price);
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null) {
            this.name = "";
        } else if (name.length() > MAX_NAME_LENGTH) {
            this.name = name.substring(0, MAX_NAME_LENGTH);
        } else {
            this.name = name;
        }
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

    public String toCSV() {
        return String.join(";", escape(name), escape(price));
    }

    public static Article fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(";", -1); // -1 to keep empty trailing fields
        if (parts.length < 2) return null;
        return new Article(unescape(parts[0]), unescape(parts[1]));
    }

    // Escapa caracteres especiales: ; se convierte en \; y \ se convierte en \\
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
    public String toString() { return String.format("%s -> %s", name, price); }
}
