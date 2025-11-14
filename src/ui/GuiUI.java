package ui;

import service.*;
import ui.components.AppleSidebar;
import ui.components.AppleStyler;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz gráfica principal - Versión 1.4
 * Diseño minimalista y limpio
 */
public class GuiUI {

    private final ClientService clientService;
    private final ArticleService articleService;
    private final InvoiceService invoiceService;
    private final ConfigService configService;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JLabel statusLabel;

    public GuiUI(ClientService cs, ArticleService as, InvoiceService is, ConfigService cfg) {
        this.clientService = cs;
        this.articleService = as;
        this.invoiceService = is;
        this.configService = cfg;
    }

    public void show() {
        AppleStyler.applyTheme();

        frame = new JFrame("Sistema de Facturació");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setMinimumSize(new Dimension(1000, 600)); // Tamaño mínimo para mantener diseño elegante
        frame.setLayout(new BorderLayout());

        // Container principal
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(AppleStyler.BG_LIGHT);
        mainContainer.setBorder(null); // Eliminar cualquier borde

        // Sidebar
        AppleSidebar sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);

        // Content area
        cardLayout = new CardLayout(0, 0); // Sin gaps
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(AppleStyler.BG_LIGHT);
        contentPanel.setBorder(null); // Eliminar borde

        // Create panels
        WelcomePanel welcomePanel = new WelcomePanel();
        welcomePanel.setOnNewClient(() -> showCard("CLIENTS"));
        welcomePanel.setOnNewInvoice(() -> showCard("INVOICES"));

        ClientsPanel clientsPanel = new ClientsPanel(clientService, this::updateStatus);
        ArticlesPanel articlesPanel = new ArticlesPanel(articleService, this::updateStatus);
        InvoicesPanel invoicesPanel = new InvoicesPanel(invoiceService, clientService,
            articleService, configService, this::updateStatus);
        ConfigPanel configPanel = new ConfigPanel(configService, this::updateStatus);

        contentPanel.add(welcomePanel, "WELCOME");
        contentPanel.add(clientsPanel, "CLIENTS");
        contentPanel.add(articlesPanel, "ARTICLES");
        contentPanel.add(invoicesPanel, "INVOICES");
        contentPanel.add(configPanel, "CONFIG");

        mainContainer.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainContainer, BorderLayout.CENTER);

        // Status bar
        statusLabel = createStatusBar();
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        showCard("WELCOME");
    }

    private AppleSidebar createSidebar() {
        AppleSidebar sidebar = new AppleSidebar();

        sidebar.addLogo("Facturacio");
        sidebar.addSpace(10);

        JButton btnHome = sidebar.addButton("Inici", e -> showCard("WELCOME"));
        sidebar.addButton("Clients", e -> showCard("CLIENTS"));
        sidebar.addButton("Articles", e -> showCard("ARTICLES"));
        sidebar.addButton("Factures", e -> showCard("INVOICES"));
        sidebar.addSpace(10);
        sidebar.addButton("Configuracio", e -> showCard("CONFIG"));

        sidebar.addFlexibleSpace();
        sidebar.addButton("Sortir", e -> System.exit(0));

        sidebar.selectButton(btnHome);

        return sidebar;
    }


    private JLabel createStatusBar() {
        JLabel status = new JLabel(" Sistema preparat");
        status.setFont(AppleStyler.FONT_SMALL);
        status.setForeground(AppleStyler.TEXT_GRAY);
        status.setBackground(AppleStyler.BG_WHITE);
        status.setOpaque(true);
        status.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, AppleStyler.BORDER),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        return status;
    }

    private void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
        updateStatus(getStatusMessage(cardName));
    }

    private String getStatusMessage(String cardName) {
        switch (cardName) {
            case "WELCOME": return "Sistema preparat";
            case "CLIENTS": return "Gesti de clients";
            case "ARTICLES": return "Gesti d'articles";
            case "INVOICES": return "Gesti de factures";
            case "CONFIG": return "Configuraci del sistema";
            default: return "Sistema preparat";
        }
    }

    private void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(" " + message);
        }
    }
}

