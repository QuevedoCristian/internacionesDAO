package internaciones.models;

/**
 *
 * @author quevedox
 */
public class Cama {
    
    private Integer id;
    
    private Integer numero;
    
    private String estado;
    
    private Habitacion habitacion;

    public Cama() {
    }

    public Cama(Integer id, Integer numero, String estado, Habitacion habitacion) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.habitacion = habitacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return numero + ", " + estado;
    }
    
}
