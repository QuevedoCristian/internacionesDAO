package internaciones.controllers;

import java.util.logging.*;
import internaciones.domain.Conexion;
import internaciones.models.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quevedox
 */
public class UbicacionDAO implements ICrud<Ubicacion> {
    
    private Connection connection;
    
    private Statement statement;
    
    private PreparedStatement preparedStatement;
    
    private ResultSet resultSet;
    
    private String sqlString;

    @Override
    public boolean create(Ubicacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "INSERT INTO ubicacion (hospital, nombre) VALUES (?, ?)";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entidad.getHospital());
            preparedStatement.setString(2, entidad.getNombre());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
        
    }

    @Override
    public ArrayList<Ubicacion> read() throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            this.statement = connection.createStatement();
            this.sqlString = "SELECT * FROM ubicacion";
            this.resultSet = statement.executeQuery(sqlString);
            connection.close();
            
            ArrayList<Ubicacion> ubicaciones = new ArrayList();
            
            while (resultSet.next()) {
                Ubicacion ubicacion = new Ubicacion();
                
                ubicacion.setId(resultSet.getInt("id"));
                ubicacion.setHospital(resultSet.getString("hospital"));
                ubicacion.setNombre(resultSet.getString("nombre"));
                
                ubicaciones.add(ubicacion);
            }
            return ubicaciones;
        } catch (SQLException e) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, "Error al leer ubicaciones", e);
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean update(Ubicacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "UPDATE ubicacion SET hospital = ?, nombre = ? WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entidad.getHospital());
            preparedStatement.setString(2, entidad.getNombre());
            preparedStatement.setInt(3, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Ubicacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "DELETE FROM ubicacion WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Ubicacion extract(int id) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            sqlString = "SELECT * FROM ubicacion WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            Ubicacion ubicacion = new Ubicacion();
            
            if (resultSet.next()) {
                ubicacion.setId(id);
                ubicacion.setHospital(resultSet.getString("hospital"));
                ubicacion.setNombre(resultSet.getString("nombre"));
            }
            return ubicacion;
        } catch (SQLException e) {
            Logger.getLogger(UbicacionDAO.class.getName()).log(Level.SEVERE, "Error al obtener ubicacion", e);
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
