package internaciones.controllers;

import internaciones.domain.Conexion;
import internaciones.models.Habitacion;
import internaciones.models.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quevedox
 */
public class HabitacionDAO implements ICrud<Habitacion> {
    
    private Connection connection;
    
    private Statement statement;
    
    private PreparedStatement preparedStatement;
    
    private ResultSet resultSet;
    
    private String sqlString;
    
    private UbicacionDAO ubicacionDAO;

    @Override
    public boolean create(Habitacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "INSERT INTO habitacion (numero, id_ubicacion) VALUES (?, ?)";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getNumero());
            preparedStatement.setInt(2, entidad.getUbicacion().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Habitacion> read() throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            this.statement = connection.createStatement();
            this.sqlString = "SELECT * FROM habitacion";
            this.resultSet = statement.executeQuery(sqlString);
            connection.close();
            
            ArrayList<Habitacion> habitaciones = new ArrayList();
            
            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion();
                
                habitacion.setId(resultSet.getInt("id"));
                habitacion.setNumero(resultSet.getInt("numero"));
                habitacion.setUbicacion(getUbicacion(resultSet.getInt("id_ubicacion")));
                
                habitaciones.add(habitacion);
            }
            return habitaciones;
        } catch (SQLException e) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, "Error al leer habitaciones", e);
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
    public boolean update(Habitacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "UPDATE habitacion SET numero = ?, id_ubicacion = ? WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getNumero());
            preparedStatement.setInt(2, entidad.getUbicacion().getId());
            preparedStatement.setInt(3, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Habitacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "DELETE FROM habitacion WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Habitacion extract(int id) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            sqlString = "SELECT * FROM habitacion WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            Habitacion habitacion = new Habitacion();
            
            if (resultSet.next()) {
                habitacion.setId(id);
                habitacion.setNumero(resultSet.getInt("numero"));
                habitacion.setUbicacion(getUbicacion(resultSet.getInt("id_ubicacion")));
            }
            return habitacion;
        } catch (SQLException e) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, "Error al obtener habitacion", e);
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
    
    private Ubicacion getUbicacion(Integer id) throws Exception {
        this.ubicacionDAO = new UbicacionDAO();
        
        Ubicacion ubicacion = ubicacionDAO.extract(id);
        
        return ubicacion;
    }
    
}
