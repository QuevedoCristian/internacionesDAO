package internaciones.controllers;

import internaciones.domain.Conexion;
import internaciones.models.Cama;
import internaciones.models.Habitacion;
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
public class CamaDAO implements ICrud<Cama>{
    
    private Connection connection;
    
    private Statement statement;
    
    private PreparedStatement preparedStatement;
    
    private ResultSet resultSet;
    
    private String sqlString;
    
    private HabitacionDAO habitacionDAO;

    @Override
    public boolean create(Cama entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "INSERT INTO cama (numero, estado, id_habitacion) VALUES (?, ?, ?)";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getNumero());
            preparedStatement.setString(2, entidad.getEstado());
            preparedStatement.setInt(3, entidad.getHabitacion().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
        
    }

    @Override
    public ArrayList<Cama> read() throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            this.statement = connection.createStatement();
            this.sqlString = "SELECT * FROM cama";
            this.resultSet = statement.executeQuery(sqlString);
            connection.close();
            
            ArrayList<Cama> camas = new ArrayList();
            
            while (resultSet.next()) {
                Cama cama = new Cama();
                
                cama.setId(resultSet.getInt("id"));
                cama.setNumero(resultSet.getInt("numero"));
                cama.setEstado(resultSet.getString("estado"));
                cama.setHabitacion(getHabitacion(resultSet.getInt("id_habitacion")));
                
                camas.add(cama);
            }
            return camas;
        } catch (SQLException e) {
            Logger.getLogger(CamaDAO.class.getName()).log(Level.SEVERE, "Error al leer camas", e);
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
    public boolean update(Cama entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "UPDATE cama SET numero = ?, estado = ?, id_habitacion = ? WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getNumero());
            preparedStatement.setString(2, entidad.getEstado());
            preparedStatement.setInt(3, entidad.getHabitacion().getId());
            preparedStatement.setInt(4, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(CamaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Cama entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "DELETE FROM cama WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CamaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Cama extract(int id) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            sqlString = "SELECT * FROM cama WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            Cama cama = new Cama();
            
            if (resultSet.next()) {
                cama.setId(id);
                cama.setNumero(resultSet.getInt("numero"));
                cama.setEstado(resultSet.getString("estado"));
                cama.setHabitacion(getHabitacion(resultSet.getInt("id_habitacion")));
            }
            return cama;
        } catch (SQLException e) {
            Logger.getLogger(CamaDAO.class.getName()).log(Level.SEVERE, "Error al obtener habitacion", e);
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
    
    private Habitacion getHabitacion(Integer id) throws Exception {
        this.habitacionDAO = new HabitacionDAO();
        
        Habitacion habitacion = habitacionDAO.extract(id);
        
        return habitacion;
    }
    
}
