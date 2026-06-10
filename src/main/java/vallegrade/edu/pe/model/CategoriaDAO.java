package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final String SQL_SELECT = "SELECT id_categoria, nombre FROM categorias";
    private static final String SQL_INSERT = "INSERT INTO categorias (nombre) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
    private static final String SQL_DELETE = "DELETE FROM categorias WHERE id_categoria = ?";

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error en listar(): " + e.getMessage());
        }
        return lista;
    }

    public boolean agregar(Categoria categoria) {
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, categoria.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en agregar(): " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Categoria categoria) {
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en actualizar(): " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en eliminar(): " + e.getMessage());
            return false;
        }
    }
}
