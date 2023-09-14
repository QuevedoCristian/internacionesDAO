package internaciones.ui.grilla;

import internaciones.models.Ubicacion;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author quevedox
 */
public class GrillaUbicacion extends AbstractTableModel {
    
    private ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
    
    public GrillaUbicacion(ArrayList<Ubicacion> datos) {
        this.ubicaciones = datos;
    }

    @Override
    public int getRowCount() {
        return ubicaciones.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ubicacion c = ubicaciones.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getHospital();
            case 2:
                return c.getNombre();
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
                return "HOSPITAL";
            case 2:
                return "NOMBRE";
            default:
                return "";
        }
    }
    
    public Ubicacion getUbicacionFromRow(int rowIndex) {

        return ubicaciones.get(rowIndex);

    }
    
}
