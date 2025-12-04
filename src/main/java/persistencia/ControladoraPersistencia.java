package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Odontologo;
import logica.Paciente;
import logica.Turno;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
    OdontologoJpaController odontologoJPA = null;
    PacienteJpaController pacienteJPA = null;
    TurnoJpaController turnoJPA = null;
    UsuarioJpaController usuarioJPA = null;
    
    public ControladoraPersistencia() {
        this.odontologoJPA = new OdontologoJpaController();
        this.pacienteJPA = new PacienteJpaController();
        this.turnoJPA = new TurnoJpaController();
        this.usuarioJPA = new UsuarioJpaController();
    }
    
    public List<Odontologo> traerOdontologo() {
        return odontologoJPA.findOdontologoEntities();
    }
    
    public void altaOdontologo(Odontologo nuevoOdontologo) {
        odontologoJPA.create(nuevoOdontologo);
    }
    
    public List<Usuario> traerUsuarios() {
        return usuarioJPA.findUsuarioEntities();
    }
    
    public void eliminarOdontologo(int idOdontologo) {
        try {
            odontologoJPA.destroy(idOdontologo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Odontologo verOdontologo(int idOdontologo) {
        return odontologoJPA.findOdontologo(idOdontologo);
    }
    
    public void editarOdontologo(Odontologo odontologoEditar) {
        try {
            odontologoJPA.edit(odontologoEditar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Paciente> traerPacientes() {
        return pacienteJPA.findPacienteEntities();
    }
    
    public void crearPaciente(Paciente nuevoPaciente) {
        
        pacienteJPA.create(nuevoPaciente);
        
    }
    
    public void eliminarPaciente(int idPacienteEliminar) {
        try {
            pacienteJPA.destroy(idPacienteEliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Paciente traerPaciente(int idVerPaciente) {
        return pacienteJPA.findPaciente(idVerPaciente);
    }
    
    public void editarPaciente(Paciente paciente) {
        try {
            pacienteJPA.edit(paciente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Turno> traerTurnos() {
        return turnoJPA.findTurnoEntities();
    }
    
    public Usuario traerUsuario(int idUsuarioEditar) {
        return usuarioJPA.findUsuario(idUsuarioEditar);
    }

    public void crearUsuario(Usuario nuevoUsuario) {
        usuarioJPA.create(nuevoUsuario);
    }

    public void eliminarUsuario(int idEliminarUsuario) {
        try {
            usuarioJPA.destroy(idEliminarUsuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarUsuario(Usuario usuario) {
        try {
            usuarioJPA.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agendarTurno(Turno turno) {
      turnoJPA.create(turno);
    }

    public void eliminarTurno(int idTurno) {
        try {
            turnoJPA.destroy(idTurno);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Turno traerTurno(int idTurno) {
        return turnoJPA.findTurno(idTurno);
    }

    public void editarTurno(Turno turnoEditar) {
        try {
            turnoJPA.edit(turnoEditar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
