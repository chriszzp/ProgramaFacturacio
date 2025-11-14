package util;

import java.math.BigDecimal;

public class Validation {
    public static boolean validDni(String dni) {
        if (dni == null) return false;
        return dni.length() == 9; // simple length check per spec
    }

    public static boolean validPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("\\d{9}");
    }

    public static boolean validCP(String cp) {
        if (cp == null) return false;
        return cp.matches("\\d{5}");
    }

    public static boolean validLength(String s, int max) {
        if (s == null) return true;
        return s.length() <= max;
    }

    public static boolean validPrice(String price) {
        if (price == null) return false;
        try {
            BigDecimal bd = new BigDecimal(price);
            if (bd.compareTo(BigDecimal.ZERO) < 0) return false;
            // check length excluding decimal point
            String plain = price.replace(".", "");
            return plain.length() <= 6;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validQuantity(String q) {
        if (q == null) return false;
        try {
            int v = Integer.parseInt(q);
            return v >= 1 && v <= 9999;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida que un string no contenga caracteres prohibidos que puedan romper el formato CSV.
     * Caracteres prohibidos: ; (punto y coma)
     */
    public static boolean noForbiddenChars(String s) {
        if (s == null) return true;
        // No permitir punto y coma porque rompe el formato CSV
        return !s.contains(";");
    }
}
