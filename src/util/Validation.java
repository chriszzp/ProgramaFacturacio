package util;

import java.math.BigDecimal;

public class Validation {
    /**
     * Valida DNI español: 8 dígitos + letra mayúscula
     */
    public static boolean validDni(String dni) {
        if (dni == null || dni.length() != 9) return false;

        // Verificar que los primeros 8 caracteres sean dígitos
        String numPart = dni.substring(0, 8);
        if (!numPart.matches("\\d{8}")) return false;

        // Verificar que el último carácter sea una letra
        char letter = dni.charAt(8);
        return Character.isLetter(letter);
    }

    /**
     * Valida teléfono español: 9 dígitos que empiece por 6, 7, 8 o 9
     */
    public static boolean validPhone(String phone) {
        if (phone == null) return false;
        if (!phone.matches("\\d{9}")) return false;

        // Verificar que empiece por 6, 7, 8 o 9 (móviles y fijos en España)
        char first = phone.charAt(0);
        return first == '6' || first == '7' || first == '8' || first == '9';
    }

    public static boolean validCP(String cp) {
        if (cp == null) return false;
        return cp.matches("\\d{5}");
    }

    public static boolean validLength(String s, int max) {
        if (s == null) return true;
        return s.length() <= max;
    }

    /**
     * Valida que un string no esté vacío
     */
    public static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    /**
     * Valida precio: número positivo, máximo 6 dígitos sin contar el punto decimal,
     * y máximo 2 decimales
     */
    public static boolean validPrice(String price) {
        if (price == null || price.trim().isEmpty()) return false;
        try {
            BigDecimal bd = new BigDecimal(price);
            if (bd.compareTo(BigDecimal.ZERO) < 0) return false;

            // Verificar máximo 2 decimales
            int dotIndex = price.indexOf('.');
            if (dotIndex >= 0) {
                String decimals = price.substring(dotIndex + 1);
                if (decimals.length() > 2) return false;
            }

            // check length excluding decimal point
            String plain = price.replace(".", "");
            return plain.length() <= 6;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida cantidad: entero positivo entre 1 y 9999
     */
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
