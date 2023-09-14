package internaciones.controllers;

import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author quevedox
 * @param <T>
 */
public interface ICrud<T> {
    
    public boolean create (T entidad) throws SQLException, Exception;
    
    public List<T> read() throws SQLException, Exception;
    
    public boolean update (T entidad) throws SQLException, Exception;
    
    public boolean delete (T entidad) throws SQLException, Exception;
    
    public T extract (int id) throws SQLException, Exception;
    
}
