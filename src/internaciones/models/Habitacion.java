package internaciones.models;

/**
 *
 * @author quevedox
 */
public class Habitacion {
    
    private Integer id;
    
    private Integer numero;
    
    private Ubicacion ubicacion;

    public Habitacion() {
    }

    public Habitacion(Integer id, Integer numero, Ubicacion ubicacion) {
        this.id = id;
        this.numero = numero;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return numero + ", " + ubicacion;
    }
    
}
