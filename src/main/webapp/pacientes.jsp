<%@page import="java.util.List"%>
<%@page import="logica.Paciente"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/head.jsp" %>


<%
    List<Paciente> pacientes = (List) request.getSession().getAttribute("listaPacientes");
%>

<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp" %>

<!-- Título y descripción -->
<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-notes-medical me-2"></i> Gestión de Pacientes</h1>
<p class="mb-4">En esta sección se detallan todos los pacientes registrados en el sistema, junto con sus datos de contacto y cobertura.</p>

<!-- Botón Agregar Paciente -->
<div class="d-flex justify-content-end mb-4">
    <a href="cargarPaciente.jsp" class="btn btn-primary shadow-lg px-4">
        <i class="fas fa-user-plus me-2"></i> Agregar Nuevo Paciente
    </a>
</div>


<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Listado de Pacientes</h6>
    </div>
    <div class="card-body">
        <% if (pacientes != null && !pacientes.isEmpty()) { %>
        <div class="table-responsive">

            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr class="bg-primary text-white">
                        <th class="text-center">ID</th>
                        <th>Nombre Completo</th>
                        <th>DNI</th>
                        <th>Teléfono</th>
                        <th>Obra Social</th>
                        <th>Tipo Sangre</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Paciente aux : pacientes) {%>
                    <tr>
                        <td class="text-center"><%= aux.getId()%></td>
                        <td><%= aux.getNombre()%> <%= aux.getApellido()%></td>
                        <td><%= aux.getDni()%></td>
                        <td> <%=  aux.getTelefono() == null ? "No disponible" : aux.getTelefono() %></td>

                        <!-- Manejo de Obra Social: Evita NullPointerException -->
                        <td>
                            <%
                                if (aux.getObraSocial() != null) {
                                    out.print(aux.getObraSocial().getNombreObra());
                                } else {
                                    out.print("- No Asignada -");
                                }
                            %>
                        </td>

                        <td><%= aux.getTipoSangre()%></td>


                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-2">
                                <form name="traerPaciente" method="GET" action="SvTraerPaciente" class="me-1">
                                    <button type="submit" class="btn btn-success btn-sm" title="Ver Detalles">
                                        <i class="fas fa-eye"></i> 
                                        <input type="hidden" name="idVerPaciente" value="<%= aux.getId()%>">
                                    </button>
                                </form>


                                <form name="editarPaciente" method="GET" action="SvEditarPaciente" class="me-1">
                                    <button type="submit" class="btn btn-warning btn-sm text-white" title="Editar">
                                        <i class="fas fa-edit"></i>
                                        <input type="hidden" name="idEditar" value="<%= aux.getId()%>">
                                    </button>
                                </form>


                                <form name="eliminarPaciente" method="POST" action="SvEliminarPaciente" class="me-1">
                                    <button type="submit" class="btn btn-danger btn-sm" title="Eliminar" 
                                            onclick="return confirm('¿Está seguro que desea eliminar a este paciente? Esta acción no se puede deshacer.');">
                                        <i class="fas fa-trash-alt"></i>
                                        <input type="hidden" name="idEliminar" value="<%= aux.getId()%>">
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="alert alert-info text-center shadow-sm" role="alert">
            <i class="fas fa-info-circle me-2"></i> No hay pacientes cargados en el sistema. Utiliza el botón "Agregar Nuevo Paciente" para comenzar.
        </div>
        <% }%>
    </div>
</div>

<%@include file="components/body-segunda_parte.jsp" %>
