package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.ProductoController;
import vallegrade.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class FrmProducto extends JFrame {

    public JTable tabla;
    private DefaultTableModel modelo;

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnModificar = new JButton("Modificar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnSalir = new JButton("Salir");

    public FrmProducto() {

        setTitle("Gestión de Productos");
        setSize(900, 500);

        // IMPORTANTE
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        // COLORES
        Color azul = new Color(0, 102, 204);
        Color azulOscuro = new Color(0, 51, 153);
        Color rojo = new Color(220, 53, 69);
        Color fondo = new Color(245, 245, 245);

        // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(fondo);

        // MODELO TABLA
        modelo = new DefaultTableModel(new String[]{
                "ID",
                "Código",
                "Nombre",
                "Descripción",
                "ID Categoría",
                "Precio",
                "Stock"
        }, 0);

        // TABLA
        tabla = new JTable(modelo);

        tabla.setRowHeight(25);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        // ENCABEZADO TABLA
        JTableHeader header = tabla.getTableHeader();

        header.setBackground(azulOscuro);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // SCROLL
        JScrollPane scroll = new JScrollPane(tabla);

        // PANEL BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);

        // ESTILO BOTONES
        JButton[] botones = {
                btnAgregar,
                btnModificar,
                btnActualizar
        };

        for (JButton boton : botones) {

            boton.setBackground(azul);
            boton.setForeground(Color.WHITE);

            boton.setFont(new Font("Arial", Font.BOLD, 14));

            boton.setFocusPainted(false);

            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            boton.setPreferredSize(new Dimension(130, 40));
        }

        // ESTILO BOTON SALIR
        btnSalir.setBackground(rojo);
        btnSalir.setForeground(Color.WHITE);

        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));

        btnSalir.setFocusPainted(false);

        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSalir.setPreferredSize(new Dimension(130, 40));

        // EVENTO BOTON SALIR
        btnSalir.addActionListener(e -> {

            FrmInicio inicio = new FrmInicio();
            inicio.setVisible(true);

            dispose();
        });

        // AGREGAR BOTONES
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnSalir);

        // AGREGAR COMPONENTES
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // INICIAR CONTROLADOR
        new ProductoController(this);
    }

    public void mostrarProductos(List<Producto> lista) {

        modelo.setRowCount(0);

        for (Producto p : lista) {

            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getCodigo(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getIdCategoria(),
                    p.getPrecio(),
                    p.getStock()
            });
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new FrmProducto().setVisible(true)
        );
    }
}