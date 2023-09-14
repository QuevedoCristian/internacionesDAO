package internaciones.ui.grilla;

import internaciones.models.Cama;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author quevedox
 */
public class GrillaCama extends AbstractTableModel {
    
    private ArrayList<Cama> camas = new ArrayList<>();
    
    public GrillaCama(ArrayList<Cama> datos) {
        this.camas = datos;
    }

    @Override
    public int getRowCount() {
        return camas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cama c = camas.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNumero();
            case 2:
                return c.getEstado();
            case 3:
                return c.getHabitacion();
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
                return "ESTADO";
            case 3:
                return "HABITACION";
            default:
                return "";
        }
    }
    
    public Cama getCamaFromRow(int rowIndex) {
        
        return camas.get(rowIndex);
    }
    
}
