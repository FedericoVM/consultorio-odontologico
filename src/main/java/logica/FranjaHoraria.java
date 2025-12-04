package logica;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FranjaHoraria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_franja;
    
    private String dia_semana;

    private LocalTime hora_inicio;

    private LocalTime hora_fin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Odontologo odontologo = null;

    public FranjaHoraria() {
    }

    public FranjaHoraria(int id_franja, String dia_semana, LocalTime hora_inicio, LocalTime hora_fin, Odontologo odontologo) {
        this.id_franja = id_franja;
        this.dia_semana = dia_semana;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.odontologo = odontologo;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setId_franja(int id_franja) {
        this.id_franja = id_franja;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getId_franja() {
        return id_franja;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

}
