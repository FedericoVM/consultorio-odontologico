package logica;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Secretario extends Persona {
    
    private String sector;
    
    @OneToOne
    private Usuario usuario = null;
    
    public Secretario(){
        
    }

    public Secretario(String sector, int id, String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac, Usuario usuario) {
        super(nombre, apellido, telefono, dni, fecha_nac);
        this.sector = sector;
        this.usuario = usuario;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSector() {
        return sector;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    
    
}
