package ui.panels;

import model.Client;
import service.ClientService;
import ui.components.AppleStyler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Panel de gestión de clientes
 */
public class ClientsPanel extends JPanel {

    private final ClientService service;
    private final Consumer<String> statusUpdater;

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField tfDni, tfName, tfAddress, tfCity, tfCP, tfProvince, tfPhone;

    public ClientsPanel(ClientService service, Consumer<String> statusUpdater) {
        this.service = service;
        this.statusUpdater = statusUpdater;

        setLayout(new BorderLayout());
        setBackground(AppleStyler.BG_LIGHT);

        buildUI();
        refreshTable();
    }

    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout(15, 0));
        header.setBackground(AppleStyler.BG_WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, AppleStyler.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel title = new JLabel("Clients");
        title.setFont(AppleStyler.FONT_TITLE);
        title.setForeground(AppleStyler.TEXT_BLACK);
        header.add(title, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);

        JButton btnNew = new JButton("Nou");
        AppleStyler.styleButtonPrimary(btnNew);
        btnNew.addActionListener(e -> showForm());

        JButton btnDelete = new JButton("Eliminar");
        AppleStyler.styleButtonDanger(btnDelete);
        btnDelete.addActionListener(e -> deleteSelected());

        JButton btnRefresh = new JButton("Actualitzar");
        AppleStyler.styleButtonSecondary(btnRefresh);
        btnRefresh.addActionListener(e -> { refreshTable(); updateStatus("Llista actualitzada"); });

        actions.add(btnNew);
        actions.add(btnDelete);
        actions.add(btnRefresh);
        header.add(actions, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Contenido
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(AppleStyler.BG_LIGHT);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tabla
        JPanel tablePanel = AppleStyler.createCard();
        tablePanel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
            new String[]{"DNI", "Nom", "Adreça", "CP", "Població", "Província", "Telèfon"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        table = new JTable(tableModel);
        AppleStyler.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(null);
        tablePanel.add(scroll, BorderLayout.CENTER);

        content.add(tablePanel, BorderLayout.CENTER);

        // Formulario
        formPanel = createForm();
        formPanel.setVisible(false);
        content.add(formPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createForm() {
        JPanel card = AppleStyler.createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel formTitle = new JLabel("Dades del Client");
        formTitle.setFont(AppleStyler.FONT_TITLE);
        formTitle.setForeground(AppleStyler.TEXT_BLACK);
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(formTitle);
        card.add(Box.createVerticalStrut(20));

        JPanel fields = new JPanel(new GridLayout(7, 2, 15, 15));
        fields.setOpaque(false);
        fields.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        fields.setAlignmentX(Component.LEFT_ALIGNMENT);

        tfDni = new JTextField();
        tfName = new JTextField();
        tfAddress = new JTextField();
        tfCity = new JTextField();
        tfCP = new JTextField();
        tfProvince = new JTextField();
        tfPhone = new JTextField();

        AppleStyler.styleTextField(tfDni);
        AppleStyler.styleTextField(tfName);
        AppleStyler.styleTextField(tfAddress);
        AppleStyler.styleTextField(tfCity);
        AppleStyler.styleTextField(tfCP);
        AppleStyler.styleTextField(tfProvince);
        AppleStyler.styleTextField(tfPhone);

        fields.add(createLabel("DNI")); fields.add(tfDni);
        fields.add(createLabel("Nom")); fields.add(tfName);
        fields.add(createLabel("Adreça")); fields.add(tfAddress);
        fields.add(createLabel("Població")); fields.add(tfCity);
        fields.add(createLabel("Codi Postal")); fields.add(tfCP);
        fields.add(createLabel("Província")); fields.add(tfProvince);
        fields.add(createLabel("Telèfon")); fields.add(tfPhone);

        card.add(fields);
        card.add(Box.createVerticalStrut(20));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnCancel = new JButton("Cancel·lar");
        AppleStyler.styleButtonSecondary(btnCancel);
        btnCancel.addActionListener(e -> hideForm());

        JButton btnSave = new JButton("Desar");
        AppleStyler.styleButtonPrimary(btnSave);
        btnSave.addActionListener(e -> saveClient());

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
        tfDni.setText("");
        tfName.setText("");
        tfAddress.setText("");
        tfCity.setText("");
        tfCP.setText("");
        tfProvince.setText("");
        tfPhone.setText("");
    }

    private void saveClient() {
        try {
            Client client = new Client(
                tfDni.getText().trim(),
                tfName.getText().trim(),
                tfAddress.getText().trim(),
                tfCity.getText().trim(),
                tfCP.getText().trim(),
                tfProvince.getText().trim(),
                tfPhone.getText().trim()
            );

            boolean ok = service.addClient(client);
            updateStatus(ok ? "✓ Client desat" : "✗ No s'ha pogut desar");
            if (ok) {
                hideForm();
                refreshTable();
            }
        } catch (Exception ex) {
            updateStatus("✗ Error: " + ex.getMessage());
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            updateStatus("Selecciona un client");
            return;
        }

        String dni = (String) tableModel.getValueAt(row, 0);
        if (dni.equals("(Cap client)")) return;

        int confirm = JOptionPane.showConfirmDialog(this,
            "Eliminar client amb DNI: " + dni + "?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = service.deleteClient(dni);
            updateStatus(ok ? "✓ Client eliminat" : "✗ Error");
            if (ok) refreshTable();
        }
    }

    private void refreshTable() {
        List<Client> clients = service.listAll();
        tableModel.setRowCount(0);

        if (clients.isEmpty()) {
            tableModel.addRow(new Object[]{"(Cap client)", "", "", "", "", "", ""});
        } else {
            for (Client c : clients) {
                tableModel.addRow(new Object[]{
                    c.getDni(), c.getName(), c.getAddress(),
                    c.getPostalCode(), c.getCity(), c.getProvince(), c.getPhone()
                });
            }
        }
    }

    private void updateStatus(String msg) {
        if (statusUpdater != null) statusUpdater.accept(msg);
    }
}

