package logica;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class   Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String dni;
    
    protected LocalDate fecha_nac;

    public Persona() {
    }
    
    

    public Persona( String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }
    
    
    
    
}
