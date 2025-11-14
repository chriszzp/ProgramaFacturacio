package model;

public class Client {
    private static final int MAX_DNI_LENGTH = 9;
    private static final int MAX_NAME_LENGTH = 40;
    private static final int MAX_ADDRESS_LENGTH = 40;
    private static final int MAX_CITY_LENGTH = 20;
    private static final int MAX_POSTAL_CODE_LENGTH = 5;
    private static final int MAX_PROVINCE_LENGTH = 20;
    private static final int MAX_PHONE_LENGTH = 9;

    private String dni;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String province;
    private String phone;

    public Client() {}

    public Client(String dni, String name, String address, String city, String postalCode, String province, String phone) {
        setDni(dni);
        setName(name);
        setAddress(address);
        setCity(city);
        setPostalCode(postalCode);
        setProvince(province);
        setPhone(phone);
    }

    // Getters and setters with automatic truncation
    public String getDni() { return dni; }

    public void setDni(String dni) {
        this.dni = truncate(dni, MAX_DNI_LENGTH);
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = truncate(name, MAX_NAME_LENGTH);
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = truncate(address, MAX_ADDRESS_LENGTH);
    }

    public String getCity() { return city; }

    public void setCity(String city) {
        this.city = truncate(city, MAX_CITY_LENGTH);
    }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) {
        this.postalCode = truncate(postalCode, MAX_POSTAL_CODE_LENGTH);
    }

    public String getProvince() { return province; }

    public void setProvince(String province) {
        this.province = truncate(province, MAX_PROVINCE_LENGTH);
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = truncate(phone, MAX_PHONE_LENGTH);
    }

    // Helper method to truncate strings
    private String truncate(String s, int maxLength) {
        if (s == null) return "";
        if (s.length() > maxLength) return s.substring(0, maxLength);
        return s;
    }

    public String toCSV() {
        return String.join(";", escape(dni), escape(name), escape(address), escape(city), escape(postalCode), escape(province), escape(phone));
    }

    public static Client fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(";", -1); // -1 to keep empty trailing fields
        if (parts.length < 7) return null;
        return new Client(
            unescape(parts[0]),
            unescape(parts[1]),
            unescape(parts[2]),
            unescape(parts[3]),
            unescape(parts[4]),
            unescape(parts[5]),
            unescape(parts[6])
        );
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
    public String toString() {
        return String.format("%s - %s, %s, %s %s (%s)", dni, name, address, postalCode, city, phone);
    }
}
