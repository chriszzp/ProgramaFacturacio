package ui.panels;

import service.ConfigService;
import ui.components.AppleStyler;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel de configuración del sistema
 */
public class ConfigPanel extends JPanel {

    private final ConfigService service;
    private final Consumer<String> statusUpdater;

    private JTextField tfIVA;

    public ConfigPanel(ConfigService service, Consumer<String> statusUpdater) {
        this.service = service;
        this.statusUpdater = statusUpdater;

        setLayout(new BorderLayout());
        setBackground(AppleStyler.BG_LIGHT);

        buildUI();
    }

    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout(15, 0));
        header.setBackground(AppleStyler.BG_WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, AppleStyler.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel title = new JLabel("Configuració");
        title.setFont(AppleStyler.FONT_TITLE);
        title.setForeground(AppleStyler.TEXT_BLACK);
        header.add(title, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // Contenido centrado
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(AppleStyler.BG_LIGHT);

        JPanel card = AppleStyler.createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(400, 200));

        JLabel cardTitle = new JLabel("IVA (%)");
        cardTitle.setFont(AppleStyler.FONT_TITLE);
        cardTitle.setForeground(AppleStyler.TEXT_BLACK);
        cardTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(cardTitle);
        card.add(Box.createVerticalStrut(10));

        JLabel subtitle = new JLabel("Percentatge d'IVA aplicat a les factures");
        subtitle.setFont(AppleStyler.FONT_BODY);
        subtitle.setForeground(AppleStyler.TEXT_GRAY);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));

        tfIVA = new JTextField();
        tfIVA.setText(service.getIva());
        AppleStyler.styleTextField(tfIVA);
        tfIVA.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        tfIVA.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(tfIVA);
        card.add(Box.createVerticalStrut(20));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnSave = new JButton("Desar");
        AppleStyler.styleButtonPrimary(btnSave);
        btnSave.addActionListener(e -> saveConfig());

        buttons.add(btnSave);
        card.add(buttons);

        centerPanel.add(card);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void saveConfig() {
        String iva = tfIVA.getText().trim();
        boolean ok = service.setIva(iva);
        updateStatus(ok ? "✓ Configuració desada" : "✗ Error: valor invàlid");
    }

    private void updateStatus(String msg) {
        if (statusUpdater != null) statusUpdater.accept(msg);
    }
}

