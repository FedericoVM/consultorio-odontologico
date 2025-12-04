package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ObraSocial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_obra_social;

    private String nombreObra;
    private String telefono;

    public ObraSocial() {

    }

    public ObraSocial(int id_obra_social, String nombreObra, String telefono, Paciente paciente) {
        this.id_obra_social = id_obra_social;
        this.nombreObra = nombreObra;
        this.telefono = telefono;
    }

    public void setId_obra_social(int id_obra_social) {
        this.id_obra_social = id_obra_social;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_obra_social() {
        return id_obra_social;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public String getTelefono() {
        return telefono;
    }

}
