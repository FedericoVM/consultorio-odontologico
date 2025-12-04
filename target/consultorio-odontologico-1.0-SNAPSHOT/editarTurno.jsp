<%@page import="java.time.LocalDateTime"%>
<%@page import="logica.Paciente"%>
<%@page import="logica.Odontologo"%>
<%@page import="java.util.List"%>
<%@page import="logica.Turno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<%    
    Turno turno = (Turno) request.getSession().getAttribute("turno");
    List<Paciente> listaPacientes = (List) request.getSession().getAttribute("listaPacientes");
    List<Odontologo> listaOdontologos = (List) request.getSession().getAttribute("listaOdontologos");
    
    LocalDateTime fechaHoraTurno = turno.getFecha_hora() ;
    String fechaHoraString  = String.valueOf(fechaHoraTurno) ;
    String[] separacionFechaHora = fechaHoraString.split("T") ;
    
    
    String fecha = separacionFechaHora[0];
    String hora = separacionFechaHora[1];
    
%>

<form class="user" method="POST" action="SvEditarTurno"> 

    <input type="hidden" name="id_turno" id="id_turno" value="<%= turno.getId_turno()%>"> 

    <hr>

    <h5 class="text-primary mb-3">Detalles de la Cita</h5>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <label for="fecha_turno" class="text-gray-800">Fecha del Turno</label>
            <input type="date" class="form-control form-control-user" name="fecha_turno" id="fecha_turno" 
                   value="<%= fecha %>" required>
        </div>
        <div class="col-sm-6">
            <label for="hora_turno" class="text-gray-800">Hora del Turno</label>
            <input type="time" class="form-control form-control-user" name="hora_turno" id="hora_turno" 
                   value="<%= hora %>" required>
        </div>
    </div>

    <hr class="mt-5">

    <h5 class="text-primary mb-3">Paciente y Profesional</h5>

    <div class="form-group">
        <label for="id_paciente" class="text-gray-800">Seleccionar Paciente</label>
        <select class="form-control" id="id_paciente" name="id_paciente" style="height: 50px;" required>
            <option value="" disabled>-- Seleccione un Paciente --</option>
            <%
                int idPacienteActual = turno.getPaciente().getId();
                if (listaPacientes != null && !listaPacientes.isEmpty()) {
                    for (Paciente pac : listaPacientes) {
            %>
            <option value="<%= pac.getId()%>"
                    <%= idPacienteActual == pac.getId() ? "selected" : ""%>>
                <%= pac.getNombre() + " " + pac.getApellido()%> (DNI: <%= pac.getDni()%>)
            </option>
            <%
                }
            } else {
            %>
            <option value="" disabled>No hay pacientes registrados</option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <label for="id_odontologo" class="text-gray-800">Seleccionar Odontólogo</label>
        <select class="form-control" id="id_odontologo" name="id_odontologo" style="height: 50px;" required>
            <option value="" disabled>-- Seleccione un Odontólogo --</option>
            <%
                // Recupera el ID del odontólogo actual del turno
                int idOdontologoActual = turno.getOdontologo().getId();
                if (listaOdontologos != null && !listaOdontologos.isEmpty()) {
                    for (Odontologo odo : listaOdontologos) {
            %>
            <option value="<%= odo.getId()%>"
                    <%= idOdontologoActual == odo.getId() ? "selected" : ""%>>
                Dr./Dra. <%= odo.getNombre() + " " + odo.getApellido()%> (Matrícula: <%= odo.getNumeroMatricula()%>)
            </option>
            <%
                }
            } else {
            %>
            <option value="" disabled>No hay odontólogos registrados</option>
            <% }%>
        </select>
    </div>

    <hr class="mt-5">

    <h5 class="text-primary mb-3">Motivo de la Cita</h5>
    <div class="form-group">
        <label for="motivo_consulta" class="text-gray-800">Motivo del Turno</label>
        <textarea class="form-control" id="motivo_consulta" name="motivo_consulta" rows="3" placeholder="Ej: Control de rutina, Limpieza, Extracción..."><%= turno.getMotivo_consulta()%></textarea>
    </div>

    <hr class="mt-5">

    <h5 class="text-primary mb-3">Observaciones y Diagnóstico</h5>
    <div class="form-group">
        <label for="observaciones_finales" class="text-gray-800">Observaciones/Diagnóstico Finales</label>
        <textarea class="form-control" id="observaciones_finales" name="observaciones_finales" rows="5" placeholder="Ej: Paciente asiste a control..."><%= turno.getObservaciones_finales()%></textarea>
        <small class="form-text text-muted">Este campo documenta lo sucedido en la cita.</small>
    </div>


    <hr>
    <button type="submit" class="btn btn-primary btn-user btn-block mt-4">
        Guardar Cambios del Turno
    </button>
    <a href="panelTurnos.jsp" class="btn btn-secondary btn-user btn-block mt-2">
        Cancelar Edición
    </a>
</form>


<%@include file="components/body-segunda_parte.jsp"  %>