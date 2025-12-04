package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Responsable extends Persona {

    private String tipo_responsable;
    
    @OneToMany(mappedBy = "responsable")
    private List<Paciente> pacientes;

    public Responsable() {
        this.pacientes = new ArrayList<>();
    }

    public Responsable(String tipo_responsable, int id, String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac) {
        super(nombre, apellido, telefono, dni, fecha_nac);
        this.tipo_responsable = tipo_responsable;
        this.pacientes = new ArrayList<>();
    }

    public void setTipo_responsable(String tipo_responsable) {
        this.tipo_responsable = tipo_responsable;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public String getTipo_responsable() {
        return tipo_responsable;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

}
