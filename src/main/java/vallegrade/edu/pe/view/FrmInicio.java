package vallegrade.edu.pe.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FrmInicio extends JFrame {

    private JButton btnProductos;

    public FrmInicio() {

        setTitle("Sistema Wawalu");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // COLORES
        Color azul = new Color(0, 102, 204);
        Color azulOscuro = new Color(0, 51, 153);

        // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setLayout(new BorderLayout());

        // PANEL CONTENIDO
        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(Color.WHITE);
        panelContenido.setBorder(new LineBorder(Color.BLACK, 3));
        panelContenido.setLayout(null);

        // TITULO
        JLabel lblTitulo = new JLabel("SISTEMA DE PRODUCTOS WAWALU");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(azulOscuro);
        lblTitulo.setBounds(55, 25, 390, 30);

        // BIENVENIDA
        JLabel lblBienvenida = new JLabel(
                "<html><center>" +
                        "Bienvenido al sistema desarrollado con<br>" +
                        "Java Swing y arquitectura MVC." +
                        "</center></html>"
        );

        lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBienvenida.setBounds(90, 70, 320, 50);

        // DESCRIPCION
        JLabel lblDescripcion = new JLabel(
                "<html><center>" +
                        "Este sistema permite administrar y visualizar<br>" +
                        "los productos de la empresa de manera rápida,<br>" +
                        "organizada y eficiente." +
                        "</center></html>"
        );

        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescripcion.setBounds(70, 130, 360, 60);

        // TEXTO MODULO
        JLabel lblModulo = new JLabel("Seleccione un módulo para continuar:");
        lblModulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblModulo.setBounds(90, 220, 320, 30);

        // BOTON PRODUCTOS
        btnProductos = new JButton("Ir a Productos");
        btnProductos.setFont(new Font("Arial", Font.BOLD, 16));
        btnProductos.setBounds(145, 280, 180, 50);

        // COLOR BOTON
        btnProductos.setBackground(azul);
        btnProductos.setForeground(Color.WHITE);

        btnProductos.setFocusPainted(false);
        btnProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnProductos.setBorder(new LineBorder(azulOscuro, 3));

        // EFECTO HOVER
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProductos.setBackground(azulOscuro);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProductos.setBackground(azul);
            }
        });

        // EVENTO BOTON
        btnProductos.addActionListener(e -> {

            FrmProducto frmProducto = new FrmProducto();
            frmProducto.setVisible(true);

            dispose();
        });

        // FOOTER
        JLabel lblFooter = new JLabel("Maylin Jauregui - POO MVC", SwingConstants.CENTER);

        lblFooter.setFont(new Font("Arial", Font.BOLD, 16));

        // COLOR NOMBRE
        lblFooter.setForeground(azulOscuro);

        lblFooter.setBorder(new LineBorder(Color.BLACK, 2));
        lblFooter.setBounds(0, 390, 480, 40);

        // AGREGAR COMPONENTES
        panelContenido.add(lblTitulo);
        panelContenido.add(lblBienvenida);
        panelContenido.add(lblDescripcion);
        panelContenido.add(lblModulo);
        panelContenido.add(btnProductos);
        panelContenido.add(lblFooter);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new FrmInicio().setVisible(true);

        });
    }
}