package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Odontologo extends Persona implements Serializable {

    private String especialidad;
    private String numeroMatricula;
    private String email;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    @OneToMany(mappedBy = "odontologo")
    private List<Turno> turnos;
    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    private List<FranjaHoraria> horarios;

    public Odontologo() {
        this.turnos = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Odontologo(int id, String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac, String especialidad, String numeroMatricula, String email, Usuario usuario) {
        super(nombre, apellido, telefono, dni, fecha_nac);
        this.especialidad = especialidad;
        this.numeroMatricula = numeroMatricula;
        this.usuario = usuario;
        this.turnos = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public void setHorarios(List<FranjaHoraria> horarios) {
        this.horarios = horarios;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public List<FranjaHoraria> getHorarios() {
        return horarios;
    }

}
