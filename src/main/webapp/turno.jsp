<%@page import="logica.Odontologo"%>
<%@page import="logica.Paciente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>
<%    List<Paciente> listaPacientes = (List) request.getSession().getAttribute("listaPacientes");
    List<Odontologo> listaOdontologos = (List) request.getSession().getAttribute("listaOdontologos");
%>

<div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
            <div class="col-lg-11 mx-auto">
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">Agendar Nuevo Turno</h1>
                        <p class="mb-4">Seleccione el paciente, el odontólogo y la fecha y hora de la cita.</p>
                    </div>

                    <form class="user" method="POST" action="SvTurnos">

                        <input type="hidden" name="id_turno" id="id_turno" value=""> 

                        <hr>

                        <h5 class="text-primary mb-3">Detalles de la Cita</h5>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <label for="fecha_turno" class="text-gray-800">Fecha del Turno</label>
                                <input type="date" class="form-control form-control-user" name="fecha_turno" id="fecha_turno" required>
                            </div>
                            <div class="col-sm-6">
                                <label for="hora_turno" class="text-gray-800">Hora del Turno</label>
                                <input type="time" class="form-control form-control-user" name="hora_turno" id="hora_turno" required>
                            </div>
                        </div>

                        <hr class="mt-5">

                        <h5 class="text-primary mb-3">Paciente y Profesional</h5>

                        <div class="form-group">
                            <label for="id_paciente" class="text-gray-800">Seleccionar Paciente</label>
                            <select class="form-control" id="id_paciente" name="id_paciente" style="height: 50px;" required>
                                <option value="" selected disabled>-- Seleccione un Paciente --</option>
                                <%
                                    if (listaPacientes != null && !listaPacientes.isEmpty()) {
                                        for (Paciente pac : listaPacientes) {
                                            // Asumiendo que Paciente tiene getId(), getNombre() y getApellido()
%>
                                <option value="<%= pac.getId()%>">
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
                                <option value="" selected disabled>-- Seleccione un Odontólogo --</option>
                                <%
                                    if (listaOdontologos != null && !listaOdontologos.isEmpty()) {
                                        for (Odontologo odo : listaOdontologos) {
                                            // Asumiendo que Odontologo tiene getId(), getNombre() y getApellido()
%>
                                <option value="<%= odo.getId()%>">
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
                            <textarea class="form-control" id="motivo_consulta" name="motivo_consulta" rows="3" placeholder="Ej: Control de rutina, Limpieza, Extracción..."></textarea>
                        </div>

                        <hr class="mt-5">

                        <h5 class="text-primary mb-3">Observaciones y Diagnóstico (Para completar después de la cita)</h5>
                        <div class="form-group">
                            <label for="observaciones_finales" class="text-gray-800">Observaciones/Diagnóstico Finales</label>
                            <textarea class="form-control" id="observaciones_finales" name="observaciones_finales" rows="5" placeholder="Ej: Paciente asiste a control. Se realiza limpieza y fluorización. Próximo control en 6 meses." ></textarea>
                            <small class="form-text text-muted">Este campo suele completarse *después* de que se realiza el turno.</small>
                        </div>


                        <hr>
                        <button type="submit" class="btn btn-primary btn-user btn-block mt-4">
                            Agendar/Guardar Turno
                        </button>
                        <a href="panelTurnos.jsp" class="btn btn-secondary btn-user btn-block mt-2">
                            Cancelar
                        </a>
                    </form>
                </div>
            </div>
        </div>


    </div>

</div>



<%@include file="components/body-segunda_parte.jsp"  %>