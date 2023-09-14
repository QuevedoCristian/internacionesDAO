package internaciones.domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author quevedox
 */
public class Conexion {
    
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/internaciones";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
