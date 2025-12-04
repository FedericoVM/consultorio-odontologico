<%@page import="java.time.LocalDateTime"%>
<%@page import="logica.Turno"%>
<%@page import="logica.Odontologo"%>
<%@page import="logica.Paciente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<%    List<Turno> listaTurnos = (List) request.getSession().getAttribute("listaTurnos");
%>

<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-calendar-check me-2"></i> Gestión de Turnos</h1>
<p class="mb-4">En esta sección se detalla la agenda completa de citas, permitiendo ver, editar y eliminar turnos.</p>

<div class="d-flex justify-content-end mb-4">
    <a href="SvSolicitarTurno" class="btn btn-primary shadow-lg px-4">
        <i class="fas fa-plus me-2"></i> Crear Nuevo Turno
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Listado Completo de Citas</h6>
    </div>
    <div class="card-body">
        <% if (listaTurnos != null && !listaTurnos.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr class="bg-primary text-white">
                        <th class="text-center">ID</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Paciente</th>
                        <th>Odontólogo</th>
                        <th>Motivo</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Turno turno : listaTurnos) {
                            LocalDateTime fechaHoraTurno = turno.getFecha_hora();
                            String fechaHoraString = String.valueOf(fechaHoraTurno);
                            String[] separacionFechaHora = fechaHoraString.split("T");

                            String fecha = separacionFechaHora[0];
                            String hora = separacionFechaHora[1];
                    %>
                    <tr>
                        <td class="text-center"><%= turno.getId_turno()%></td>
                        <td><%= fecha %></td> 
                        <td><%= hora %></td> 
                        <td><%= turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido()%></td>
                        <td><%= turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellido()%></td>
                        <td><%= turno.getMotivo_consulta() != null ? turno.getMotivo_consulta() : "N/A"%></td>

                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-2">

                                <form name="editarTurno"  action="SvEditarTurno" method="GET" class="me-1">
                                    <input type="hidden" name="idEditar" value="<%= turno.getId_turno()%>">
                                    <button type="submit" class="btn btn-warning btn-sm text-white" title="Editar Turno">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form>

                                <form action="SvEliminarTurno" method="POST" class="me-1">
                                    <input type="hidden" name="idEliminar" value="<%= turno.getId_turno()%>">
                                    <button type="submit" class="btn btn-danger btn-sm" title="Eliminar Turno"
                                            onclick="return confirm('¿Está seguro que desea cancelar este turno? Esta acción no se puede deshacer.');">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="alert alert-info text-center shadow-sm" role="alert">
            <i class="fas fa-info-circle me-2"></i> No hay turnos registrados en la agenda. Utiliza el botón "Crear Nuevo Turno" para comenzar.
        </div>
        <% }%>
    </div>
</div>
<%@include file="components/body-segunda_parte.jsp"  %>


<script>
    $(document).ready(function () {
        $('#dataTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json"
            }
        });
    });
</script>