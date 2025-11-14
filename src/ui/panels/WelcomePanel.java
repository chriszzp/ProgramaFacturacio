package ui.panels;

import ui.components.AppleStyler;
import javax.swing.*;
import java.awt.*;

/**
 * Panel de bienvenida minimalista
 */
public class WelcomePanel extends JPanel {

    private Runnable onNewClient;
    private Runnable onNewInvoice;

    public WelcomePanel() {
        setLayout(new GridBagLayout());
        setBackground(AppleStyler.BG_LIGHT);
        buildUI();
    }

    private void buildUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Icono
        JLabel icon = new JLabel("$");
        icon.setFont(new Font("Segoe UI", Font.BOLD, 80));
        icon.setForeground(AppleStyler.BLUE);
        add(icon, gbc);

        // Título
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        JLabel title = new JLabel("Sistema de Facturació");
        title.setFont(AppleStyler.FONT_TITLE);
        title.setForeground(AppleStyler.TEXT_BLACK);
        add(title, gbc);

        // Subtítulo
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 10, 10);
        JLabel subtitle = new JLabel("Gestió de clients, articles i factures");
        subtitle.setFont(AppleStyler.FONT_SUBTITLE);
        subtitle.setForeground(AppleStyler.TEXT_GRAY);
        add(subtitle, gbc);

        // Versión
        gbc.gridy++;
        gbc.insets = new Insets(30, 10, 30, 10);
        JLabel version = new JLabel("Versió 1.4");
        version.setFont(AppleStyler.FONT_SMALL);
        version.setForeground(AppleStyler.TEXT_LIGHT);
        add(version, gbc);

        // Botones
        gbc.gridy++;
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttons.setOpaque(false);

        JButton btnClient = new JButton("Nou Client");
        AppleStyler.styleButtonPrimary(btnClient);
        btnClient.addActionListener(e -> {
            if (onNewClient != null) onNewClient.run();
        });

        JButton btnInvoice = new JButton("Nova Factura");
        AppleStyler.styleButtonSecondary(btnInvoice);
        btnInvoice.addActionListener(e -> {
            if (onNewInvoice != null) onNewInvoice.run();
        });

        buttons.add(btnClient);
        buttons.add(btnInvoice);
        add(buttons, gbc);
    }

    public void setOnNewClient(Runnable action) {
        this.onNewClient = action;
    }

    public void setOnNewInvoice(Runnable action) {
        this.onNewInvoice = action;
    }
}

