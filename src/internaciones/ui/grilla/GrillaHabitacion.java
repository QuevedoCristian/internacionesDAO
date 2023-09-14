package internaciones.ui.grilla;

import internaciones.models.Habitacion;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author quevedox
 */
public class GrillaHabitacion extends AbstractTableModel {
    
    private ArrayList<Habitacion> habitaciones = new ArrayList<>();
    
    public GrillaHabitacion(ArrayList<Habitacion> datos) {
        this.habitaciones = datos;
    }

    @Override
    public int getRowCount() {
        return habitaciones.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Habitacion c = habitaciones.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNumero();
            case 2:
                return c.getUbicacion();
            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "NUMERO";
            case 2:
                return "UBICACION";
            default:
                return "";
        }
    }
    
    public Habitacion getHabitacionFromRow(int rowIndex) {
        
        return habitaciones.get(rowIndex);
    }
    
}
