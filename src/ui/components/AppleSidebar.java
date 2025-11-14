package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Componente de barra lateral para navegación
 * Versión 1.4 - Diseño minimalista
 */
public class AppleSidebar extends JPanel {

    private JButton selectedButton;

    public AppleSidebar() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(AppleStyler.BG_SIDEBAR);
        setPreferredSize(new Dimension(200, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, AppleStyler.BORDER));
    }

    /**
     * Añade un logo/título en la parte superior
     */
    public void addLogo(String text) {
        JLabel logo = new JLabel(text);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setForeground(AppleStyler.TEXT_BLACK);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(logo);
    }

    /**
     * Añade un botón de navegación
     */
    public JButton addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(AppleStyler.FONT_BODY);
        button.setForeground(AppleStyler.TEXT_BLACK);
        button.setBackground(AppleStyler.BG_SIDEBAR);
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != selectedButton) {
                    button.setBackground(new Color(240, 240, 245));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != selectedButton) {
                    button.setBackground(AppleStyler.BG_SIDEBAR);
                }
            }
        });

        button.addActionListener(e -> {
            if (selectedButton != null) {
                selectedButton.setBackground(AppleStyler.BG_SIDEBAR);
                selectedButton.setForeground(AppleStyler.TEXT_BLACK);
            }
            button.setBackground(AppleStyler.BLUE);
            button.setForeground(Color.WHITE);
            selectedButton = button;

            if (action != null) {
                action.actionPerformed(e);
            }
        });

        add(button);
        return button;
    }

    /**
     * Añade un espacio fijo
     */
    public void addSpace(int height) {
        add(Box.createVerticalStrut(height));
    }

    /**
     * Añade un espacio flexible (empuja elementos hacia abajo)
     */
    public void addFlexibleSpace() {
        add(Box.createVerticalGlue());
    }

    /**
     * Selecciona un botón programáticamente
     */
    public void selectButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setBackground(AppleStyler.BG_SIDEBAR);
            selectedButton.setForeground(AppleStyler.TEXT_BLACK);
        }
        button.setBackground(AppleStyler.BLUE);
        button.setForeground(Color.WHITE);
        selectedButton = button;
    }
}

