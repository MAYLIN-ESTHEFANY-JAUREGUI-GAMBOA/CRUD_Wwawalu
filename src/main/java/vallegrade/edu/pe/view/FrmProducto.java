package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.ProductoController;
import vallegrade.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrmProducto extends JFrame {
    public JTable tabla;
    private DefaultTableModel modelo;

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnModificar = new JButton("Modificar");
    public JButton btnActualizar = new JButton("Actualizar");

    public FrmProducto() {
        setTitle("Gestión de Productos");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(new String[]{
                "ID",
                "Código",
                "Nombre",
                "Descripción",
                "ID Categoría",
                "Precio",
                "Stock"
        }, 0);
        tabla = new JTable(modelo);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnActualizar);

        add(new JScrollPane(tabla), "Center");
        add(panelBotones, "South");

        // Iniciar controlador
        new ProductoController(this);
    }

    public void mostrarProductos(List<Producto> lista) {
        modelo.setRowCount(0); // limpiar tabla
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
        SwingUtilities.invokeLater(() -> new FrmProducto().setVisible(true));
    }
}
