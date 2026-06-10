package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class ReportesView extends JFrame {

    public JButton btnVerReporte;
    public JButton btnExportarPDF;
    public JButton btnCerrar;

    public ReportesView() {

        setTitle("Gestión de Reportes - Wwawalu");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con gradiente
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(30, 58, 138);
                Color color2 = new Color(59, 130, 246);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        // Panel para el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false);
        JLabel titulo = new JLabel("MÓDULO DE REPORTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 20));
        panelBotones.setOpaque(false);

        btnVerReporte = crearBoton("📊 Ver Reporte", new Color(59, 130, 246));
        panelBotones.add(btnVerReporte);

        btnExportarPDF = crearBoton("📄 Exportar PDF", new Color(34, 197, 94));
        panelBotones.add(btnExportarPDF);

        btnCerrar = crearBoton("❌ Cerrar", new Color(239, 68, 68));
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
