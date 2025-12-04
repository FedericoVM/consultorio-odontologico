package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import persistencia.ControladoraPersistencia;

public class Controladora {
    
    ControladoraPersistencia controladoraPersistencia = null;
    
    public Controladora() {
        controladoraPersistencia = new ControladoraPersistencia();
    }
    
    public List<Odontologo> traerOdontologos() {
        return controladoraPersistencia.traerOdontologo();
    }
    
    public void altaOdontologo(String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac_odontologo, String especialidad, String matricula, String email, String usuario, String contrasenia, List<FranjaHoraria> listaHorarios) {
        
        Odontologo nuevoOdontologo = new Odontologo();
        nuevoOdontologo.setNombre(nombre);
        nuevoOdontologo.setApellido(apellido);
        nuevoOdontologo.setTelefono(telefono);
        nuevoOdontologo.setDni(dni);
        nuevoOdontologo.setFecha_nac(fecha_nac_odontologo);
        nuevoOdontologo.setEspecialidad(especialidad);
        nuevoOdontologo.setNumeroMatricula(matricula);
        nuevoOdontologo.setHorarios(null);
        nuevoOdontologo.setEmail(email);
        
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre_usuario(usuario);
        nuevoUsuario.setContrasenia(contrasenia);
        nuevoUsuario.setRol("Odontólogo");
        
        nuevoOdontologo.setUsuario(nuevoUsuario);
        
        nuevoOdontologo.setHorarios(listaHorarios);
        
        if (listaHorarios != null) {
            for (FranjaHoraria hor : listaHorarios) {
                hor.setOdontologo(nuevoOdontologo);
            }
        }
        
        controladoraPersistencia.altaOdontologo(nuevoOdontologo);
        
    }
    
    public List<Usuario> traerUsuarios() {
        return controladoraPersistencia.traerUsuarios();
    }
    
    public void eliminarOdontologo(int idOdontologo) {
        controladoraPersistencia.eliminarOdontologo(idOdontologo);
    }
    
    public Odontologo verOdontologo(int idOdontologo) {
        return controladoraPersistencia.verOdontologo(idOdontologo);
    }
    
    public void editarOdontologo(int idEditar, String nombre, String apellido, String telefono, String dni, LocalDate fecha_nac_odontologo, String especialidad,
            String matricula, String email, String usuario, String contrasenia, LocalTime lunes_inicio,
            LocalTime lunes_fin, LocalTime martes_inicio, LocalTime martes_fin, LocalTime miercoles_inicio,
            LocalTime miercoles_fin, LocalTime jueves_inicio, LocalTime jueves_fin, LocalTime viernes_inicio,
            LocalTime viernes_fin) {
        
        Odontologo odontologoEditar = controladoraPersistencia.verOdontologo(idEditar);
        odontologoEditar.setNombre(nombre);
        odontologoEditar.setApellido(apellido);
        odontologoEditar.setTelefono(telefono);
        odontologoEditar.setDni(dni);
        odontologoEditar.setFecha_nac(fecha_nac_odontologo);
        odontologoEditar.setEspecialidad(especialidad);
        odontologoEditar.setNumeroMatricula(matricula);
        odontologoEditar.setEmail(email);
        
        odontologoEditar.getUsuario().setNombre_usuario(usuario);
        odontologoEditar.getUsuario().setContrasenia(contrasenia);
        
        odontologoEditar.getHorarios().get(0).setHora_inicio(lunes_inicio);
        odontologoEditar.getHorarios().get(0).setHora_fin(lunes_fin);
        
        odontologoEditar.getHorarios().get(1).setHora_inicio(martes_inicio);
        odontologoEditar.getHorarios().get(1).setHora_fin(martes_fin);
        
        odontologoEditar.getHorarios().get(2).setHora_inicio(miercoles_inicio);
        odontologoEditar.getHorarios().get(2).setHora_fin(miercoles_fin);
        
        odontologoEditar.getHorarios().get(3).setHora_inicio(jueves_inicio);
        odontologoEditar.getHorarios().get(3).setHora_fin(jueves_fin);
        
        odontologoEditar.getHorarios().get(4).setHora_inicio(viernes_inicio);
        odontologoEditar.getHorarios().get(4).setHora_fin(viernes_fin);
        
        controladoraPersistencia.editarOdontologo(odontologoEditar);
        
    }
    
    public List<Paciente> traerPacientes() {
        return controladoraPersistencia.traerPacientes();
    }
    
    public void crearPaciente(String nombre_paciente, String apellido_paciente, String telefono_paciente, LocalDate fecha_nac_paciente,
            String dni_paciente, String nombre_obra_social, String num_afiliado, String telefono_obra_social, String tipo_sangre,
            String nombre_tutor, String apellido_tutor, String dni_tutor, LocalDate fecha_nac_tutor, String telefono_tutor, String relacion_tutor) {

        // 1. Obtener la fecha actual.
        LocalDate fecha_actual = LocalDate.now();

        // 2. Calcular el Period (diferencia de tiempo) entre la fecha de nacimiento y la fecha actual.
        Period periodo = Period.between(fecha_nac_paciente, fecha_actual);

        // 3. Extraer los años del Period.
        int edad = periodo.getYears();
        System.out.println("Es mayor de edad? " + edad);
        
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setNombre(nombre_paciente);
        nuevoPaciente.setApellido(apellido_paciente);
        nuevoPaciente.setTelefono(telefono_paciente);
        nuevoPaciente.setFecha_nac(fecha_nac_paciente);
        nuevoPaciente.setDni(dni_paciente);
        nuevoPaciente.setNumAfiliado(num_afiliado);
        nuevoPaciente.setTipoSangre(tipo_sangre);
        
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setNombreObra(nombre_obra_social);
        obraSocial.setTelefono(telefono_obra_social);
        nuevoPaciente.setObraSocial(obraSocial);
        
        Responsable responsable = new Responsable();
        responsable.setNombre(nombre_tutor);
        responsable.setApellido(apellido_tutor);
        responsable.setDni(dni_tutor);
        responsable.setFecha_nac(fecha_nac_paciente);
        responsable.setTelefono(telefono_tutor);
        responsable.setTipo_responsable(relacion_tutor);
        if (edad > 17) {
            nuevoPaciente.setMayorEdad(true);
            
        } else {
            nuevoPaciente.setMayorEdad(false);
            nuevoPaciente.setResponsable(responsable);
        }
        
        controladoraPersistencia.crearPaciente(nuevoPaciente);
        
    }
    
    public void eliminarPaciente(int idPacienteEliminar) {
        controladoraPersistencia.eliminarPaciente(idPacienteEliminar);
    }
    
    public Paciente buscarPaciente(int idVerPaciente) {
        return controladoraPersistencia.traerPaciente(idVerPaciente);
    }
    
    public void editarPaciente(int idPaciente, String nombre, String apellido, String telefono, LocalDate fecha_nac, String dni, String num_afiliado,
            String tipo_sangre, String nombre_obraSocial, String telefono_obraSocial, String nombre_resp, String apellido_resp,
            String telefono_resp, String dni_resp, LocalDate fecha_nac_resp, String tipo_resp) {
        
        Paciente paciente = controladoraPersistencia.traerPaciente(idPaciente);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setTelefono(telefono);
        paciente.setFecha_nac(fecha_nac);
        paciente.setDni(dni);
        paciente.setNumAfiliado(num_afiliado);
        paciente.setTipoSangre(tipo_sangre);

        // ObraSocial
        paciente.getObraSocial().setNombreObra(nombre_obraSocial);
        paciente.getObraSocial().setTelefono(telefono);

        // CORRECCIÓN: Usamos la fecha de nacimiento para determinar si es mayor de edad
        LocalDate fecha_actual = LocalDate.now();
        Period periodo = Period.between(fecha_nac, fecha_actual);
        boolean esMayorEdad = periodo.getYears() >= 18;

        // 1. Actualizar el flag mayorEdad (esto también anulará el Responsable si esMayorEdad=true)
        paciente.setMayorEdad(esMayorEdad);

        // 2. Manejo del Responsable (SOLO si no es mayor de edad)
        if (!esMayorEdad) {
            
            Responsable responsable;

            // CORRECCIÓN: Si el Responsable es NULL, hay que CREAR uno nuevo e inyectarlo.
            if (paciente.getResponsable() == null) {
                responsable = new Responsable();
                // Enlaza el nuevo Responsable. El cascade=ALL lo persistirá.
                paciente.setResponsable(responsable);
            } else {
                // Si existe, usa el existente para actualizar sus datos.
                responsable = paciente.getResponsable();
            }

            // Actualiza los datos del Responsable (ya sea el nuevo o el existente)
            responsable.setNombre(nombre_resp);
            responsable.setApellido(apellido_resp);
            responsable.setTelefono(telefono_resp);
            responsable.setDni(dni_resp);
            responsable.setFecha_nac(fecha_nac_resp);
            responsable.setTipo_responsable(tipo_resp);
            
        }
        // Si es mayor de edad (else), el setMayorEdad(true) ya puso a null el responsable,
        // desenlazándolo.

        controladoraPersistencia.editarPaciente(paciente);
        
    }
    
    public List<Turno> traerTurnos() {
        return controladoraPersistencia.traerTurnos();
    }
    
    public Usuario traerUsuario(int idUsuarioEditar) {
        return controladoraPersistencia.traerUsuario(idUsuarioEditar);
    }
    
    public void crearUsuario(String nombre_usuario, String contrasenia, String rol) {
        
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre_usuario(nombre_usuario);
        nuevoUsuario.setContrasenia(contrasenia);
        nuevoUsuario.setRol(rol);
        
        controladoraPersistencia.crearUsuario(nuevoUsuario);
        
    }
    
    public void eliminarUsuario(int idEliminarUsuario) {
        controladoraPersistencia.eliminarUsuario(idEliminarUsuario);
    }
    
    public void editarUsuario(int id_usuario, String nombre_usuario, String contrasenia_usuario, String rol) {
        Usuario usuario = controladoraPersistencia.traerUsuario(id_usuario);
        usuario.setNombre_usuario(nombre_usuario);
        if (!contrasenia_usuario.isEmpty()) {
            usuario.setContrasenia(contrasenia_usuario);
        }
        usuario.setRol(rol);
        
        controladoraPersistencia.editarUsuario(usuario);
        
    }
    
    public Usuario login(String nombre_usuario, String contrasenia) {
        
        Usuario usuario = null;
        
        List<Usuario> listaUsuarios = controladoraPersistencia.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombre_usuario().equals(nombre_usuario)) {
                
                if (usu.getContrasenia().equals(contrasenia)) {
                    usuario = usu;
                    break;
                } else {
                    usuario = null;
                }
                
            }
            
        }
        
        return usuario;
        
    }
    
    public void agendarTurno(LocalDateTime fechaHoraTurno, int idPaciente, int idOdontologo, String motivo, String observaciones) {
        
        Paciente paciente = controladoraPersistencia.traerPaciente(idPaciente);
        Odontologo odontologo = controladoraPersistencia.verOdontologo(idOdontologo);
        
        Turno turno = new Turno();
        turno.setFecha_hora(fechaHoraTurno);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setMotivo_consulta(motivo);
        turno.setObservaciones_finales(observaciones);
        
        controladoraPersistencia.agendarTurno(turno);
        
    }
    
    public void eliminarTurno(int idTurno) {
        controladoraPersistencia.eliminarTurno(idTurno);
    }
    
    public Turno traerTurno(int idTurno) {
        return controladoraPersistencia.traerTurno(idTurno);
    }
    
    public void editarTurno(int idTurno, String fecha, String hora, int idPaciente, int idOdontologo, String motivo, String observacion) {
        Turno turnoEditar = controladoraPersistencia.traerTurno(idTurno);
        Paciente paciente = controladoraPersistencia.traerPaciente(idPaciente);
        Odontologo odontologo = controladoraPersistencia.verOdontologo(idOdontologo);
        
        
        String fechaHoraCompleta = fecha + "T" + hora;
        LocalDateTime fechaHoraTurno = LocalDateTime.parse(fechaHoraCompleta);
        
        turnoEditar.setFecha_hora(fechaHoraTurno);
        turnoEditar.setPaciente(paciente);
        turnoEditar.setOdontologo(odontologo);
        turnoEditar.setMotivo_consulta(motivo);
        turnoEditar.setObservaciones_finales(observacion);
        
        controladoraPersistencia.editarTurno(turnoEditar);
        
        
    }
    
}
