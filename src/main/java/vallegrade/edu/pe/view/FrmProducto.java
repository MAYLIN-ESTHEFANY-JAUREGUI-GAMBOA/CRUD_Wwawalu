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
    private JLabel lblContador;

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnModificar = new JButton("Modificar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnVolver = new JButton("Volver");
    public JTextField txtBuscar = new JTextField(20);
    public JButton btnBuscar = new JButton("🔍");

    public FrmProducto() {
        setTitle("Gestión de Productos");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar colores de botones
        btnAgregar.setBackground(new Color(59, 130, 246)); // Azul
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setOpaque(true);

        btnModificar.setBackground(new Color(34, 197, 94)); // Verde
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFocusPainted(false);
        btnModificar.setOpaque(true);

        btnEliminar.setBackground(new Color(239, 68, 68)); // Rojo
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setOpaque(true);

        btnActualizar.setBackground(new Color(250, 204, 21)); // Amarillo
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setOpaque(true);

        btnVolver.setBackground(new Color(107, 114, 128)); // Gris
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setOpaque(true);

        // Configurar campo de búsqueda
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setToolTipText("Ingrese nombre o código para buscar");

        btnBuscar.setBackground(new Color(59, 130, 246));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setOpaque(true);
        btnBuscar.setPreferredSize(new Dimension(40, 30));

        // Configurar tabla profesional
        modelo = new DefaultTableModel(new String[]{
                "ID",
                "Código",
                "Nombre",
                "Descripción",
                "ID Categoría",
                "Precio",
                "Stock"
        }, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                if (columnIndex == 5) return Double.class;
                if (columnIndex == 6) return Integer.class;
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(230, 230, 230));
        
        // Configurar encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(30, 58, 138)); // Azul oscuro
        header.setForeground(Color.WHITE); // Texto blanco
        header.setReorderingAllowed(false);

        // Panel de botones con mejor espaciado
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(250, 250, 250));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Hacer botones más grandes
        btnAgregar.setPreferredSize(new Dimension(100, 35));
        btnModificar.setPreferredSize(new Dimension(100, 35));
        btnEliminar.setPreferredSize(new Dimension(100, 35));
        btnActualizar.setPreferredSize(new Dimension(100, 35));
        btnVolver.setPreferredSize(new Dimension(100, 35));

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        // Panel superior con búsqueda y contador
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(250, 250, 250));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(new Color(250, 250, 250));
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        lblContador = new JLabel("Total: 0 registros");
        lblContador.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblContador.setForeground(new Color(30, 58, 138));

        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(lblContador, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

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
        lblContador.setText("Total: " + lista.size() + " registros");
    }

    public JLabel getLblContador() {
        return lblContador;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmProducto().setVisible(true));
    }
}
