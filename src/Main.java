import java.io.File;
import javax.swing.SwingUtilities;

import service.ClientService;
import service.ArticleService;
import service.ConfigService;
import service.InvoiceService;
import ui.ConsoleUI;
import ui.GuiUI;

/**
 * Punto de entrada de la aplicación.
 * Crea los servicios de negocio usando la carpeta "data" y arranca
 * la interfaz de consola si se pasa el argumento --console, o la GUI Swing en
 * caso contrario.
 */
public class Main {
    public static void main(String[] args) {
        // Directorio donde se guardan los ficheros de datos (relativo al working dir)
        String dataDir = System.getProperty("user.dir") + File.separator + "data";

        // Inicialización de servicios (repositorios y lógica asociada)
        ClientService clientService = new ClientService(dataDir);
        ArticleService articleService = new ArticleService(dataDir);
        ConfigService configService = new ConfigService(dataDir);
        InvoiceService invoiceService = new InvoiceService(dataDir);

        // Si se pasa --console arrancamos la versión consola
        boolean console = false;
        for (String a : args) {
            if ("--console".equalsIgnoreCase(a)) { console = true; break; }
        }

        if (console) {
            // Interfaz basada en texto
            ConsoleUI ui = new ConsoleUI(clientService, articleService, invoiceService, configService);
            ui.run();
        } else {
            // start Swing GUI on EDT (Event Dispatch Thread)
            SwingUtilities.invokeLater(() -> {
                GuiUI gui = new GuiUI(clientService, articleService, invoiceService, configService);
                gui.show();
            });
        }
    }
}