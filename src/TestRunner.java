import service.*;
import model.*;
import java.io.File;
import java.util.*;

/**
 * Clase de pruebas/runners para verificar el funcionamiento básico de los servicios.
 * Crea algunos datos de ejemplo, genera una factura y muestra los ficheros de datos.
 * Uso: ejecutar desde IDE o línea de comandos para pruebas manuales.
 */
public class TestRunner {
    public static void main(String[] args) {
        try {
            String dataDir = System.getProperty("user.dir") + File.separator + "data";
            ClientService cs = new ClientService(dataDir);
            ArticleService as = new ArticleService(dataDir);
            ConfigService cfg = new ConfigService(dataDir);
            InvoiceService is = new InvoiceService(dataDir);

            // Add client
            Client c = new Client("12345678Z", "Maria Serra", "C/ Major 12", "Palma", "07001", "Illes Balears", "612345678");
            boolean addedClient = cs.addClient(c);
            System.out.println("Client added: " + addedClient);

            // Add article
            Article a1 = new Article("Bolígraf blau", "0.45");
            boolean addedA1 = as.addArticle(a1);
            System.out.println("Article added: " + addedA1);

            Article a2 = new Article("Llibreta A5", "2.50");
            boolean addedA2 = as.addArticle(a2);
            System.out.println("Article added: " + addedA2);

            // Create invoice with 2 lines
            List<InvoiceLine> lines = new ArrayList<>();
            lines.add(new InvoiceLine(null, 3, a1.getName(), a1.getPrice()));
            lines.add(new InvoiceLine(null, 2, a2.getName(), a2.getPrice()));

            String date = java.time.LocalDate.now().toString();
            String iva = cfg.getIva();
            Invoice inv = is.createInvoice(date, c.getDni(), lines, iva);
            InvoiceService.Totals totals = is.calculateTotals(lines, iva);
            System.out.println("Created invoice: " + inv.getId());
            System.out.println("Base: " + totals.base + " IVA: " + totals.iva + " Total: " + totals.total);

            System.out.println("\n--- Ficheros guardados en data/ ---");
            // Print files
            printFile("data\\clients.txt");
            printFile("data\\articles.txt");
            printFile("data\\factures.txt");
            printFile("data\\linies_factura.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper: muestra el contenido de un fichero en consola. Maneja errores de lectura.
     */
    private static void printFile(String path) {
        System.out.println("\n" + path + ":");
        try (java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = r.readLine()) != null) System.out.println(line);
        } catch (Exception e) {
            System.out.println("(no existe o error leyendo: " + e.getMessage() + ")");
        }
    }
}
