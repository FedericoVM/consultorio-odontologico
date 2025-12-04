package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Turno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_turno;

    private LocalDateTime fecha_hora;

    private String motivo_consulta;
    private String observaciones_finales;

    @ManyToOne
    private Paciente paciente = null;

    @ManyToOne
    private Odontologo odontologo = null;

    public Turno() {
    }

    public Turno(int id_turno, LocalDateTime fecha_hora, String motivo_consulta, String observaciones_finales, Paciente paciente, Odontologo odontologo) {
        this.id_turno = id_turno;
        this.fecha_hora = fecha_hora;
        this.motivo_consulta = motivo_consulta;
        this.observaciones_finales = observaciones_finales;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public void setObservaciones_finales(String observaciones_finales) {
        this.observaciones_finales = observaciones_finales;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getId_turno() {
        return id_turno;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public String getObservaciones_finales() {
        return observaciones_finales;
    }

    public Paciente getPaciente() {
        return paciente;
    }

}
