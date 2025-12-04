<%@page import="logica.Usuario"%>
<%@page import="logica.Odontologo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>


<%  List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");%>

<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-users-cog me-2"></i> Gestión de Usuarios</h1>
<p class="mb-4">En esta sección se detallan todos los usuarios registrados en el sistema y sus roles.</p>

<div class="d-flex justify-content-end mb-4">
    <a href="crearUsuario.jsp" class="btn btn-primary shadow-lg px-4">
        <i class="fas fa-user-plus me-2"></i> Agregar Nuevo Usuario
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Listado de Usuarios</h6>
    </div>
    <div class="card-body">
        <% if (listaUsuarios != null && !listaUsuarios.isEmpty()) { %>
        <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr class="bg-primary text-white">
                        <th class="text-center">ID</th>
                        <th>Usuario</th>
                        <th>Rol</th>
                        <th>Contraseña</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Usuario aux : listaUsuarios) {%>
                    <tr>
                        <td class="text-center"> <%= aux.getId_usuario()%> </td>
                        <td> <%= aux.getNombre_usuario()%> </td>
                        <td> <%= aux.getRol()%> </td>
                        <td> <%= aux.getContrasenia()%> </td> 

                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-2">

                                <form name="editarUsuario" method="GET" action="SvEditarUsuario" class="me-1">
                                    <button type="submit" class="btn btn-warning btn-sm text-white" title="Editar">
                                        <i class="fas fa-edit"></i>
                                        <input type="hidden" name="idEditar" value="<%= aux.getId_usuario()%>">
                                    </button>
                                </form>

                                <form name="eliminarUsuario" method="POST" action="SvEliminarUsuario" class="me-1">
                                    <button type="submit" class="btn btn-danger btn-sm" title="Eliminar"
                                            onclick="return confirm('¿Está seguro que desea eliminar a este usuario? Esta acción no se puede deshacer.');">
                                        <i class="fas fa-trash-alt"></i>
                                        <input type="hidden" name="idEliminar" value="<%= aux.getId_usuario()%>">
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
            <i class="fas fa-info-circle me-2"></i> No hay usuarios cargados en el sistema. Utiliza el botón "Agregar Nuevo Usuario" para comenzar.
        </div>
        <% }%>
    </div>
</div>

<%@include file="components/body-segunda_parte.jsp"  %>


