package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Esquema Wwawalu: tabla productos
    private static final String SQL_SELECT = "SELECT id_producto, codigo, nombre, descripcion, id_categoria, precio, stock FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos (codigo, nombre, descripcion, id_categoria, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE productos SET codigo = ?, nombre = ?, descripcion = ?, id_categoria = ?, precio = ?, stock = ? WHERE id_producto = ?";

    // Listar todos
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error en listar(): " + e.getMessage());
        }
        return lista;
    }

    public boolean agregar(Producto producto) {
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getIdCategoria());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en agregar(): " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Producto producto) {
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getIdCategoria());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());
            ps.setInt(7, producto.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en actualizar(): " + e.getMessage());
            return false;
        }
    }
}

