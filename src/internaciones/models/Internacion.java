package internaciones.models;

import java.util.Date;

/**
 *
 * @author quevedox
 */
public class Internacion {
    
    private Integer id;
    
    private String paciente;
    
    private Date fecha;
    
    private String diagnostico;
    
    private Cama cama;

    public Internacion() {
    }

    public Internacion(Integer id, String paciente, Date fecha, String diagnostico, Cama cama) {
        this.id = id;
        this.paciente = paciente;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.cama = cama;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Cama getCama() {
        return cama;
    }

    public void setCama(Cama cama) {
        this.cama = cama;
    }

    @Override
    public String toString() {
        return "Internacion{" + "paciente=" + paciente + ", diagnostico=" + diagnostico + ", cama=" + cama + '}';
    }
    
}
