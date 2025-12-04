package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_usuario;
    
    private String nombre_usuario;
    private String contrasenia;
    private String rol;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String contrasenia, String rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getRol() {
        return rol;
    }
    
    
    
    
    
}
