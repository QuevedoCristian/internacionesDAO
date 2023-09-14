package internaciones.ui.grilla;

import internaciones.models.Internacion;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author quevedox
 */
public class GrillaInternacion extends AbstractTableModel {
    
    private ArrayList<Internacion> internaciones = new ArrayList<>();
    
    public GrillaInternacion(ArrayList<Internacion> datos) {
        this.internaciones = datos;
    }

    @Override
    public int getRowCount() {
        return internaciones.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Internacion c = internaciones.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getPaciente();
            case 2:
                return c.getFecha();
            case 3:
                return c.getDiagnostico();
            case 4:
                return c.getCama();
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
                return "PACIENTE";
            case 2:
                return "FECHA";
            case 3:
                return "DIAGNOSTICO";
            case 4:
                return "CAMA";
            default:
                return "";
        }
    }
    
    public Internacion getInternacionFromRow(int rowIndex) {
        
        return internaciones.get(rowIndex);
    }
    
}
