package ui.panels;

import model.*;
import service.*;
import ui.components.AppleStyler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Panel de gestión de facturas
 */
public class InvoicesPanel extends JPanel {

    private final InvoiceService invoiceService;
    private final ClientService clientService;
    private final ArticleService articleService;
    private final ConfigService configService;
    private final Consumer<String> statusUpdater;

    private JTable invoicesTable;
    private DefaultTableModel invoicesModel;
    private JPanel formPanel;

    // Formulario
    private JTextField tfClientDni, tfSearchId;
    private JComboBox<String> cbArticle;
    private JSpinner spQuantity;
    private DefaultListModel<String> linesListModel;
    private JList<String> linesList;
    private List<InvoiceLine> currentLines;

    public InvoicesPanel(InvoiceService invoiceService, ClientService clientService,
                         ArticleService articleService, ConfigService configService,
                         Consumer<String> statusUpdater) {
        this.invoiceService = invoiceService;
        this.clientService = clientService;
        this.articleService = articleService;
        this.configService = configService;
        this.statusUpdater = statusUpdater;
        this.currentLines = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(AppleStyler.BG_LIGHT);

        buildUI();
        refreshInvoicesTable();
    }

    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout(15, 0));
        header.setBackground(AppleStyler.BG_WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, AppleStyler.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel title = new JLabel("Factures");
        title.setFont(AppleStyler.FONT_TITLE);
        title.setForeground(AppleStyler.TEXT_BLACK);
        header.add(title, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);

        JButton btnNew = new JButton("Nova Factura");
        AppleStyler.styleButtonPrimary(btnNew);
        btnNew.addActionListener(e -> showForm());

        JButton btnRefresh = new JButton("Actualitzar");
        AppleStyler.styleButtonSecondary(btnRefresh);
        btnRefresh.addActionListener(e -> { refreshInvoicesTable(); updateStatus("Llista actualitzada"); });

        actions.add(btnNew);
        actions.add(btnRefresh);
        header.add(actions, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Contenido
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(AppleStyler.BG_LIGHT);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con tabla y búsqueda
        JPanel topPanel = new JPanel(new BorderLayout(0, 15));
        topPanel.setOpaque(false);

        // Búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchPanel.setOpaque(false);

        JLabel lblSearch = new JLabel("Buscar per ID:");
        lblSearch.setFont(AppleStyler.FONT_BODY);
        lblSearch.setForeground(AppleStyler.TEXT_GRAY);

        tfSearchId = new JTextField(10);
        AppleStyler.styleTextField(tfSearchId);

        JButton btnSearch = new JButton("Buscar");
        AppleStyler.styleButtonSecondary(btnSearch);
        btnSearch.addActionListener(e -> searchInvoice());

        searchPanel.add(lblSearch);
        searchPanel.add(tfSearchId);
        searchPanel.add(btnSearch);

        topPanel.add(searchPanel, BorderLayout.NORTH);

        // Tabla de facturas
        JPanel tablePanel = AppleStyler.createCard();
        tablePanel.setLayout(new BorderLayout());

        invoicesModel = new DefaultTableModel(
            new String[]{"ID", "Data", "DNI Client", "IVA (%)", "Total"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        invoicesTable = new JTable(invoicesModel);
        AppleStyler.styleTable(invoicesTable);

        JScrollPane scroll = new JScrollPane(invoicesTable);
        scroll.setBorder(null);
        tablePanel.add(scroll, BorderLayout.CENTER);

        topPanel.add(tablePanel, BorderLayout.CENTER);
        content.add(topPanel, BorderLayout.CENTER);

        // Formulario
        formPanel = createForm();
        formPanel.setVisible(false);
        content.add(formPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createForm() {
        JPanel card = AppleStyler.createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel formTitle = new JLabel("Nova Factura");
        formTitle.setFont(AppleStyler.FONT_TITLE);
        formTitle.setForeground(AppleStyler.TEXT_BLACK);
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(formTitle);
        card.add(Box.createVerticalStrut(20));

        // Panel de campos
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // DNI Cliente
        JPanel dniPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        dniPanel.setOpaque(false);
        dniPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        dniPanel.add(createLabel("DNI Client:"));
        tfClientDni = new JTextField(15);
        AppleStyler.styleTextField(tfClientDni);
        dniPanel.add(tfClientDni);
        fieldsPanel.add(dniPanel);

        fieldsPanel.add(Box.createVerticalStrut(10));

        // Selección de artículo
        JPanel articlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        articlePanel.setOpaque(false);
        articlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        articlePanel.add(createLabel("Article:"));
        cbArticle = new JComboBox<>();
        cbArticle.setPreferredSize(new Dimension(200, 35));
        cbArticle.setFont(AppleStyler.FONT_BODY);
        articlePanel.add(cbArticle);

        articlePanel.add(createLabel("Quantitat:"));
        spQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        spQuantity.setPreferredSize(new Dimension(80, 35));
        spQuantity.setFont(AppleStyler.FONT_BODY);
        articlePanel.add(spQuantity);

        JButton btnAddLine = new JButton("Afegir Linia");
        AppleStyler.styleButtonSecondary(btnAddLine);
        btnAddLine.addActionListener(e -> addLine());
        articlePanel.add(btnAddLine);

        fieldsPanel.add(articlePanel);
        fieldsPanel.add(Box.createVerticalStrut(15));

        card.add(fieldsPanel);

        // Lista de líneas
        JLabel lblLines = createLabel("Linies de la factura:");
        lblLines.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(lblLines);
        card.add(Box.createVerticalStrut(5));

        linesListModel = new DefaultListModel<>();
        linesList = new JList<>(linesListModel);
        linesList.setFont(AppleStyler.FONT_BODY);
        linesList.setBackground(AppleStyler.BG_WHITE);
        linesList.setBorder(BorderFactory.createLineBorder(AppleStyler.BORDER));

        JScrollPane linesScroll = new JScrollPane(linesList);
        linesScroll.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
        linesScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        linesScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(linesScroll);
        card.add(Box.createVerticalStrut(10));

        JPanel lineButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lineButtons.setOpaque(false);
        lineButtons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        lineButtons.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnRemoveLine = new JButton("Eliminar Linia");
        AppleStyler.styleButtonDanger(btnRemoveLine);
        btnRemoveLine.addActionListener(e -> removeLine());
        lineButtons.add(btnRemoveLine);

        JButton btnClear = new JButton("Netejar Tot");
        AppleStyler.styleButtonSecondary(btnClear);
        btnClear.addActionListener(e -> clearForm());
        lineButtons.add(btnClear);

        card.add(lineButtons);
        card.add(Box.createVerticalStrut(20));

        // Botones finales
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnCancel = new JButton("Cancel·lar");
        AppleStyler.styleButtonSecondary(btnCancel);
        btnCancel.addActionListener(e -> hideForm());

        JButton btnSave = new JButton("Desar Factura");
        AppleStyler.styleButtonPrimary(btnSave);
        btnSave.addActionListener(e -> saveInvoice());

        buttons.add(btnCancel);
        buttons.add(btnSave);
        card.add(buttons);

        return card;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppleStyler.FONT_BODY);
        label.setForeground(AppleStyler.TEXT_GRAY);
        return label;
    }

    private void showForm() {
        clearForm();
        refreshArticlesCombo();
        formPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void hideForm() {
        formPanel.setVisible(false);
        revalidate();
        repaint();
    }

    private void clearForm() {
        tfClientDni.setText("");
        currentLines.clear();
        linesListModel.clear();
        spQuantity.setValue(1);
    }

    private void refreshArticlesCombo() {
        cbArticle.removeAllItems();
        List<Article> articles = articleService.listAll();
        for (Article a : articles) {
            cbArticle.addItem(a.getName() + " (" + a.getPrice() + " EUR)");
        }
    }

    private void addLine() {
        if (cbArticle.getItemCount() == 0) {
            updateStatus("No hi ha articles disponibles");
            return;
        }

        String selected = (String) cbArticle.getSelectedItem();
        if (selected == null) return;

        // Extraer nombre del artículo
        String articleName = selected.substring(0, selected.lastIndexOf(" ("));
        int quantity = (Integer) spQuantity.getValue();

        // Buscar el artículo
        Article article = articleService.find(articleName);
        if (article == null) {
            updateStatus("Article no trobat");
            return;
        }

        // Crear línea
        InvoiceLine line = new InvoiceLine("", quantity, article.getName(), article.getPrice());
        currentLines.add(line);

        // Añadir a la lista visual
        double total = quantity * Double.parseDouble(article.getPrice());
        linesListModel.addElement(String.format("%d x %s @ %s EUR = %.2f EUR",
            quantity, article.getName(), article.getPrice(), total));

        updateStatus("Linia afegida");
    }

    private void removeLine() {
        int selected = linesList.getSelectedIndex();
        if (selected >= 0 && selected < currentLines.size()) {
            currentLines.remove(selected);
            linesListModel.remove(selected);
            updateStatus("Linia eliminada");
        } else {
            updateStatus("Selecciona una linia per eliminar");
        }
    }

    private void saveInvoice() {
        String dni = tfClientDni.getText().trim();

        if (dni.isEmpty()) {
            updateStatus("Introdueix el DNI del client");
            return;
        }

        if (currentLines.isEmpty()) {
            updateStatus("Afegeix almenys una linia");
            return;
        }

        if (currentLines.size() > 10) {
            updateStatus("Maxim 10 linies per factura");
            return;
        }

        // Verificar que el cliente existe
        Client client = clientService.find(dni);
        if (client == null) {
            updateStatus("Client amb DNI " + dni + " no existeix");
            return;
        }

        // Crear factura
        LocalDate today = LocalDate.now();
        String dateStr = String.format("%04d-%02d-%02d",
            today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        String iva = configService.getIva();

        try {
            Invoice invoice = invoiceService.createInvoice(dateStr, dni, currentLines, iva);

            if (invoice != null) {
                // Calcular totales para mostrar
                double base = 0;
                for (InvoiceLine line : currentLines) {
                    base += line.getQuantity() * Double.parseDouble(line.getPrice());
                }
                double ivaAmount = base * Double.parseDouble(iva) / 100;
                double total = base + ivaAmount;

                updateStatus(String.format("Factura %s creada. Base: %.2f, IVA: %.2f, Total: %.2f EUR",
                    invoice.getId(), base, ivaAmount, total));

                hideForm();
                refreshInvoicesTable();
            } else {
                updateStatus("Error al crear la factura");
            }
        } catch (Exception ex) {
            updateStatus("Error: " + ex.getMessage());
        }
    }

    private void searchInvoice() {
        String id = tfSearchId.getText().trim();
        if (id.isEmpty()) {
            updateStatus("Introdueix un ID de factura");
            return;
        }

        Invoice invoice = invoiceService.find(id);
        if (invoice == null) {
            updateStatus("Factura no trobada");
            return;
        }

        List<InvoiceLine> lines = invoiceService.findLines(id);

        // Calcular totales
        double base = 0;
        for (InvoiceLine line : lines) {
            base += line.getQuantity() * Double.parseDouble(line.getPrice());
        }
        double ivaAmount = base * Double.parseDouble(invoice.getIva()) / 100;
        double total = base + ivaAmount;

        // Mostrar detalles en un diálogo
        StringBuilder details = new StringBuilder();
        details.append("Factura ID: ").append(invoice.getId()).append("\n");
        details.append("Data: ").append(invoice.getDate()).append("\n");
        details.append("Client DNI: ").append(invoice.getClientDni()).append("\n");
        details.append("IVA: ").append(invoice.getIva()).append("%\n\n");
        details.append("Linies:\n");

        for (InvoiceLine line : lines) {
            double lineTotal = line.getQuantity() * Double.parseDouble(line.getPrice());
            details.append(String.format("  %d x %s @ %s EUR = %.2f EUR\n",
                line.getQuantity(), line.getArticleName(), line.getPrice(), lineTotal));
        }

        details.append(String.format("\nBase imponible: %.2f EUR\n", base));
        details.append(String.format("IVA: %.2f EUR\n", ivaAmount));
        details.append(String.format("TOTAL: %.2f EUR", total));

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Detalls de la Factura", JOptionPane.INFORMATION_MESSAGE);

        updateStatus("Factura " + id + " mostrada");
    }

    private void refreshInvoicesTable() {
        List<Invoice> invoices = invoiceService.listAll();
        invoicesModel.setRowCount(0);

        if (invoices.isEmpty()) {
            invoicesModel.addRow(new Object[]{"(Cap factura)", "", "", "", ""});
        } else {
            for (Invoice inv : invoices) {
                // Calcular total
                List<InvoiceLine> lines = invoiceService.findLines(inv.getId());
                double base = 0;
                for (InvoiceLine line : lines) {
                    base += line.getQuantity() * Double.parseDouble(line.getPrice());
                }
                double total = base * (1 + Double.parseDouble(inv.getIva()) / 100);

                invoicesModel.addRow(new Object[]{
                    inv.getId(),
                    inv.getDate(),
                    inv.getClientDni(),
                    inv.getIva(),
                    String.format("%.2f EUR", total)
                });
            }
        }
    }

    private void updateStatus(String msg) {
        if (statusUpdater != null) statusUpdater.accept(msg);
    }
}

