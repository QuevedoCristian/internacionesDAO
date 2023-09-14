package internaciones.models;

/**
 *
 * @author quevedox
 */
public class Ubicacion {
    
    private Integer id;
    
    private String hospital;
    
    private String nombre;

    public Ubicacion() {
    }

    public Ubicacion(Integer id, String hospital, String nombre) {
        this.id = id;
        this.hospital = hospital;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
