package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Categoria;
import vallegrade.edu.pe.service.CategoriaService;
import vallegrade.edu.pe.view.FrmCategoria;
import vallegrade.edu.pe.view.FrmPrincipal;

import javax.swing.*;
import java.util.List;

public class CategoriaController {
    private CategoriaService service;
    private FrmCategoria vista;

    public CategoriaController(FrmCategoria vista) {
        this.vista = vista;
        this.service = new CategoriaService();
        cargarCategorias();

        this.vista.btnAgregar.addActionListener(e -> agregarCategoria());
        this.vista.btnModificar.addActionListener(e -> modificarCategoria());
        this.vista.btnEliminar.addActionListener(e -> eliminarCategoria());
        this.vista.btnActualizar.addActionListener(e -> cargarCategorias());
        this.vista.btnVolver.addActionListener(e -> volverAlPrincipal());
        this.vista.btnBuscar.addActionListener(e -> buscarCategoria());
        this.vista.txtBuscar.addActionListener(e -> buscarCategoria());
    }

    private void cargarCategorias() {
        List<Categoria> lista = service.obtenerCategorias();
        vista.mostrarCategorias(lista);
    }

    private void agregarCategoria() {
        String nombre = JOptionPane.showInputDialog("Nombre de la categoría:");

        if (nombre != null && !nombre.trim().isEmpty()) {
            Categoria nuevo = new Categoria(0, nombre.trim());
            if (service.agregarCategoria(nuevo)) {
                JOptionPane.showMessageDialog(vista, "Categoría agregada");
                cargarCategorias();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar (posiblemente ya existe)");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío");
        }
    }

    private void modificarCategoria() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría");
            return;
        }

        int id = (int) vista.tabla.getValueAt(fila, 0);
        String nombre = JOptionPane.showInputDialog("Nuevo nombre:");

        if (nombre != null && !nombre.trim().isEmpty()) {
            Categoria modificado = new Categoria(id, nombre.trim());
            if (service.actualizarCategoria(modificado)) {
                JOptionPane.showMessageDialog(vista, "Categoría actualizada");
                cargarCategorias();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío");
        }
    }

    private void eliminarCategoria() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría");
            return;
        }

        int id = (int) vista.tabla.getValueAt(fila, 0);
        String nombre = (String) vista.tabla.getValueAt(fila, 1);

        int confirm = JOptionPane.showConfirmDialog(
            vista,
            "¿Está seguro de eliminar la categoría: " + nombre + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (service.eliminarCategoria(id)) {
                JOptionPane.showMessageDialog(vista, "Categoría eliminada");
                cargarCategorias();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar (posiblemente tiene productos asociados)");
            }
        }
    }

    private void volverAlPrincipal() {
        vista.dispose();
        new FrmPrincipal().setVisible(true);
    }

    private void buscarCategoria() {
        String texto = vista.txtBuscar.getText().trim().toLowerCase();
        if (texto.isEmpty()) {
            cargarCategorias();
            return;
        }

        List<Categoria> todos = service.obtenerCategorias();
        List<Categoria> filtrados = new java.util.ArrayList<>();

        for (Categoria c : todos) {
            if (c.getNombre().toLowerCase().contains(texto)) {
                filtrados.add(c);
            }
        }

        vista.mostrarCategorias(filtrados);
    }
}
