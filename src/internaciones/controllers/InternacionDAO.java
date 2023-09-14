package internaciones.controllers;

import internaciones.domain.Conexion;
import internaciones.models.Cama;
import java.sql.Date;
import internaciones.models.Internacion;
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
public class InternacionDAO implements ICrud<Internacion> {
    
    private Connection connection;
    
    private Statement statement;
    
    private PreparedStatement preparedStatement;
    
    private ResultSet resultSet;
    
    private String sqlString;
    
    private CamaDAO camaDAO;

    @Override
    public boolean create(Internacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "INSERT INTO internacion (paciente, fecha, diagnostico, id_cama) VALUES (?, ?, ?, ?)";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entidad.getPaciente());
            preparedStatement.setDate(2, new java.sql.Date(entidad.getFecha().getTime()));
            preparedStatement.setString(3, entidad.getDiagnostico());
            preparedStatement.setInt(4, entidad.getCama().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Internacion> read() throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            this.statement = connection.createStatement();
            this.sqlString = "SELECT * FROM internacion";
            this.resultSet = statement.executeQuery(sqlString);
            connection.close();
            
            ArrayList<Internacion> internaciones = new ArrayList();
            
            while (resultSet.next()) {
                Internacion internacion = new Internacion();
                
                internacion.setId(resultSet.getInt("id"));
                internacion.setPaciente(resultSet.getString("paciente"));
                internacion.setFecha(resultSet.getDate("fecha"));
                internacion.setDiagnostico(resultSet.getString("diagnostico"));
                internacion.setCama(getCama(resultSet.getInt("id_cama")));
                
                internaciones.add(internacion);
            }
            return internaciones;
        } catch (SQLException e) {
            Logger.getLogger(InternacionDAO.class.getName()).log(Level.SEVERE, "Error al leer internaciones", e);
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
    public boolean update(Internacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "UPDATE internacion SET paciente = ?, fecha = ?, diagnostico = ?, id_cama = ? WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, entidad.getPaciente());
            preparedStatement.setDate(2, new java.sql.Date(entidad.getFecha().getTime()));
            preparedStatement.setString(3, entidad.getDiagnostico());
            preparedStatement.setInt(4, entidad.getCama().getId());
            preparedStatement.setInt(5, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(InternacionDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Internacion entidad) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        sqlString = "DELETE FROM internacion WHERE id = ?";
        
        try {
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, entidad.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(InternacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Internacion extract(int id) throws SQLException, Exception {
        
        connection = Conexion.getConnection();
        
        try {
            sqlString = "SELECT * FROM internacion WHERE id = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            Internacion internacion = new Internacion();
            
            if (resultSet.next()) {
                internacion.setId(id);
                internacion.setPaciente(resultSet.getString("paciente"));
                internacion.setFecha(resultSet.getDate("fecha"));
                internacion.setDiagnostico(resultSet.getString("diagnostico"));
                internacion.setCama(getCama(resultSet.getInt("id_cama")));
            }
            return internacion;
        } catch (SQLException e) {
            Logger.getLogger(InternacionDAO.class.getName()).log(Level.SEVERE, "Error al obtener habitacion", e);
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
    
    private Cama getCama(Integer id) throws Exception {
        this.camaDAO = new CamaDAO();
        
        Cama cama = camaDAO.extract(id);
        
        return cama;
    }
}
