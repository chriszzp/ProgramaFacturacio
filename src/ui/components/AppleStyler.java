package ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Clase de utilidades para estilos visuales minimalistas
 * Versión 1.4 - Diseño limpio y funcional
 */
public class AppleStyler {

    // === COLORES ===
    public static final Color BLUE = new Color(0, 122, 255);
    public static final Color BLUE_HOVER = new Color(0, 100, 230);
    public static final Color RED = new Color(255, 59, 48);
    public static final Color RED_HOVER = new Color(230, 45, 35);
    public static final Color GRAY = new Color(142, 142, 147);

    public static final Color BG_WHITE = new Color(255, 255, 255);
    public static final Color BG_LIGHT = new Color(248, 248, 250);
    public static final Color BG_SIDEBAR = new Color(250, 250, 252);

    public static final Color TEXT_BLACK = new Color(0, 0, 0);
    public static final Color TEXT_GRAY = new Color(100, 100, 105);
    public static final Color TEXT_LIGHT = new Color(150, 150, 155);

    public static final Color BORDER = new Color(230, 230, 235);

    // === FUENTES ===
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);

    // === MÉTODOS DE ESTILIZACIÓN ===

    public static void styleButtonPrimary(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BLUE_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BLUE);
            }
        });
    }

    public static void styleButtonSecondary(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setForeground(TEXT_BLACK);
        button.setBackground(BG_LIGHT);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(9, 19, 9, 19)
        ));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BG_LIGHT);
            }
        });
    }

    public static void styleButtonDanger(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setForeground(Color.WHITE);
        button.setBackground(RED);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(RED_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(RED);
            }
        });
    }

    public static void styleTextField(JTextField field) {
        field.setFont(FONT_BODY);
        field.setForeground(TEXT_BLACK);
        field.setBackground(BG_WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void styleTable(JTable table) {
        table.setFont(FONT_BODY);
        table.setForeground(TEXT_BLACK);
        table.setBackground(BG_WHITE);
        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0, 122, 255, 30));
        table.setSelectionForeground(TEXT_BLACK);

        table.getTableHeader().setFont(FONT_BODY);
        table.getTableHeader().setForeground(TEXT_GRAY);
        table.getTableHeader().setBackground(BG_LIGHT);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        table.getTableHeader().setReorderingAllowed(false);

        table.setBorder(BorderFactory.createLineBorder(BORDER, 1));
    }

    public static JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(BG_WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }

    public static void applyTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usar predeterminado
        }

        UIManager.put("Button.font", FONT_BUTTON);
        UIManager.put("Label.font", FONT_BODY);
        UIManager.put("TextField.font", FONT_BODY);
        UIManager.put("Table.font", FONT_BODY);
    }
}

