package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends Persona implements Serializable {

    private String tipoSangre;
    private String numAfiliado;
    private boolean mayorEdad;

    @OneToOne(cascade = CascadeType.ALL)
    private ObraSocial obraSocial = null;

    @ManyToOne(cascade = CascadeType.ALL)
    private Responsable responsable = null;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Turno> turnos = null;

    public Paciente() {
        this.turnos = new ArrayList<>();
    }

    public Paciente(String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac, String tipoSangre, String numAfiliado, boolean mayorEdad, ObraSocial obraSocial, Responsable responsable) {
        super(nombre, apellido, telefono, dni, fecha_nac);
        this.tipoSangre = tipoSangre;
        this.numAfiliado = numAfiliado;
        this.mayorEdad = mayorEdad;
        this.obraSocial = obraSocial;
        this.responsable = responsable;
        this.turnos = new ArrayList<>();
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setNumAfiliado(String numAfiliado) {
        this.numAfiliado = numAfiliado;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getNumAfiliado() {
        return numAfiliado;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setMayorEdad(boolean mayorEdad) {

        if (mayorEdad) {
            this.responsable = null;
            this.mayorEdad = mayorEdad;
        } else {
            this.mayorEdad = mayorEdad;
        }

    }

    public boolean getMayorEdad() {
        return mayorEdad;
    }

}
