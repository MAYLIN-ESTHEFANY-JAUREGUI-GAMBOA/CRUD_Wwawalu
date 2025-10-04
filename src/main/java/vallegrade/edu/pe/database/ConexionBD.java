package vallegrade.edu.pe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Conexión a MySQL 8 para la BD Wwawalu
    private static final String URL = "jdbc:mysql://localhost:3306/Wwawalu";
    private static final String USER =  "root";
    private static final String PASS = "root";

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa a la BD Wwawalu");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return con;
    }
}
