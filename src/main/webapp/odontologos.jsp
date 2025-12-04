<%@page import="logica.Odontologo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>




<%@include file="components/head.jsp" %>

<%  List<Odontologo> listaOdontologos = (List) request.getSession().getAttribute("listaOdontologos");%>

<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-user-md me-2"></i> Gestión de Odontólogos</h1>
<p class="mb-4">En esta sección se detallan todos los profesionales registrados en el sistema, junto con sus datos de contacto y especialidad.</p>

<div class="d-flex justify-content-end mb-4">
    <a href="form-odontologo.jsp" class="btn btn-primary shadow-lg px-4">
        <i class="fas fa-user-plus me-2"></i> Agregar Nuevo Odontólogo
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Listado de Profesionales</h6>
    </div>
    <div class="card-body">
        <% if (listaOdontologos != null && !listaOdontologos.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr class="bg-primary text-white">
                        <th class="text-center">ID</th>
                        <th>Nombre Completo</th>
                        <th>DNI</th>
                        <th>Matrícula</th>
                        <th>Especialidad</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Odontologo aux : listaOdontologos) {
                    %>
                    <tr>
                        <td class="text-center"> <%= aux.getId()%> </td>
                        <td> <%= aux.getNombre()%> <%= aux.getApellido()%> </td>
                        <td> <%= aux.getDni()%> </td>
                        <td> <%= aux.getNumeroMatricula()%> </td>
                        <td> <%= aux.getEspecialidad()%> </td>

                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-2">

                                <form name="traerOdontologo" method="GET" action="SvTraerOdontologo" class="me-1">
                                    <button type="submit" class="btn btn-success btn-sm" title="Ver Detalles">
                                        <i class="fas fa-eye"></i> 
                                        <input type="hidden" name="idVerOdontologo" value = "<%= aux.getId()%>" >  
                                    </button>
                                </form>

                                <form name="editarOdontologo" method="GET" action="SvEditarOdontologo" class="me-1">
                                    <button type="submit" class="btn btn-warning btn-sm text-white" title="Editar">
                                        <i class="fas fa-edit"></i>
                                        <input type="hidden" name="idEditar" value = "<%= aux.getId()%>" >  
                                    </button>
                                </form>

                                <form name="eliminarOdontologo" method="POST" action="SvEliminarOdontologo" class="me-1">
                                    <button type="submit" class="btn btn-danger btn-sm" title="Eliminar" 
                                            onclick="return confirm('¿Está seguro que desea eliminar a este profesional? Esta acción no se puede deshacer.');">
                                        <i class="fas fa-trash-alt" ></i>
                                        <input type="hidden" name="id" value = "<%= aux.getId()%>" >  
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
            <i class="fas fa-info-circle me-2"></i> No hay odontólogos cargados en el sistema. Utiliza el botón "Agregar Nuevo Odontólogo" para comenzar.
        </div>
        <% }%>
    </div>
</div>

<%@include file="components/body-segunda_parte.jsp"  %>



