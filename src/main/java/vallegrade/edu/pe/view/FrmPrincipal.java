package vallegrade.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class FrmPrincipal extends JFrame {
    private JButton btnProductos;
    private JButton btnCategorias;
    private JButton btnReportes;
    private JButton btnSalir;

    public FrmPrincipal() {
        setTitle("Sistema Wwawalu");
        setSize(800, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                Color color1 = new Color(30, 58, 138); // Azul oscuro
                Color color2 = new Color(59, 130, 246); // Azul claro
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panelPrincipal.setLayout(new BorderLayout());

        // Panel de contenido
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Título
        JLabel lblTitulo = new JLabel("Sistema Wwawalu", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblTitulo);
        panelContenido.add(Box.createVerticalStrut(15));

        // Descripción
        JLabel lblDescripcion = new JLabel("<html><div style='text-align: center;'>"
                + "Sistema de gestión para administrar productos y categorías.<br>"
                + "Seleccione un módulo para comenzar.</div></html>", SwingConstants.CENTER);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDescripcion.setForeground(new Color(220, 220, 220));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(lblDescripcion);
        panelContenido.add(Box.createVerticalStrut(40));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 0, 20));
        panelBotones.setOpaque(false);

        // Botón Productos
        btnProductos = crearBoton("Gestión de Productos", new Color(59, 130, 246));
        ImageIcon iconoProducto = new ImageIcon(getClass().getResource("/images/Product.png"));
        btnProductos.setIcon(iconoProducto);
        btnProductos.addActionListener(e -> abrirProductos());
        panelBotones.add(btnProductos);

        // Botón Categorías
        btnCategorias = crearBoton("Gestión de Categorías", new Color(34, 197, 94));
        ImageIcon iconoCategoria = new ImageIcon(getClass().getResource("/images/Category.png"));
        btnCategorias.setIcon(iconoCategoria);
        btnCategorias.addActionListener(e -> abrirCategorias());
        panelBotones.add(btnCategorias);

        // Botón Reportes
        btnReportes = crearBoton("Reportes de Productos", new Color(236, 72, 153));
        btnReportes.addActionListener(e -> abrirReportes());
        panelBotones.add(btnReportes);

        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(panelBotones);
        panelContenido.add(Box.createVerticalStrut(25));

        // Botón Salir
        btnSalir = crearBoton("Salir", new Color(239, 68, 68));
        btnSalir.addActionListener(e -> System.exit(0));
        btnSalir.setMaximumSize(new Dimension(300, 50));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(btnSalir);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
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
        btn.setPreferredSize(new Dimension(300, 50));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void abrirProductos() {
        this.dispose();
        new FrmProducto().setVisible(true);
    }

    private void abrirCategorias() {
        this.dispose();
        new FrmCategoria().setVisible(true);
    }

    private void abrirReportes() {
        this.dispose();
        ReportesView reportesView = new ReportesView();
        new vallegrade.edu.pe.controller.ReportesController(reportesView);
        reportesView.setVisible(true);
    }
}
