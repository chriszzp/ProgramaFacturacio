package service;

import java.io.*;

public class ConfigService {
    private final File file;
    private String iva = "21"; // default

    public ConfigService(String dataDir) {
        this.file = new File(dataDir, "config.txt");
        try { file.getParentFile().mkdirs(); file.createNewFile(); } catch (IOException e) {}
        load();
    }

    private void load() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line = r.readLine();
            if (line != null && line.startsWith("IVA=")) {
                iva = line.substring(4).trim();
            }
        } catch (Exception e) {
            // ignore, use default
        }
    }

    public String getIva() { return iva; }

    public boolean setIva(String newIva) {
        try {
            Integer.parseInt(newIva);
            iva = newIva;
            try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
                w.write("IVA=" + iva);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
