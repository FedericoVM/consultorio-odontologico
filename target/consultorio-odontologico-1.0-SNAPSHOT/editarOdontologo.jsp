<%@page import="logica.Odontologo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<%
    // Recuperar el objeto Odontologo de la sesión para pre-cargar los datos
    Odontologo odontologo = (Odontologo) request.getSession().getAttribute("odontologoEncontradoEditar");
%>

<div class="container">

    <h1 class="h3 mb-2 text-gray-800"><i class="fas fa-user-edit me-2"></i> Editar Odontólogo</h1>
    <p class="mb-4">Modifique los datos personales y profesionales del odontólogo. Las credenciales de acceso se editan por separado.</p>

    <div class="d-flex justify-content-start mb-4">
        <a href="SvOdontologos" class="btn btn-secondary shadow-sm px-4">
            <i class="fas fa-arrow-left me-2"></i> Volver a Odontólogos
        </a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Formulario de Edición para ID: <%= odontologo.getId()%></h6>
        </div>
        <div class="card-body">

            <form action="SvEditarOdontologo" method="POST">

                <input type="hidden" name="id_odontologo" value="<%= odontologo.getId()%>">

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="nombre" class="form-label font-weight-bold text-gray-800">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" 
                               value="<%= odontologo.getNombre()%>" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="apellido" class="form-label font-weight-bold text-gray-800">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" 
                               value="<%= odontologo.getApellido()%>" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="dni" class="form-label font-weight-bold text-gray-800">DNI:</label>
                        <input type="text" class="form-control" id="dni" name="dni" 
                               value="<%= odontologo.getDni()%>" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="telefono" class="form-label font-weight-bold text-gray-800">Teléfono:</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" 
                               value="<%= odontologo.getTelefono()%>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="matricula" class="form-label font-weight-bold text-gray-800">Matrícula:</label>
                        <input type="text" class="form-control" id="matricula" name="matricula" 
                               value="<%= odontologo.getNumeroMatricula()%>" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="especialidad" class="form-label font-weight-bold text-gray-800">Especialidad:</label>
                        <input type="text" class="form-control" id="especialidad" name="especialidad" 
                               value="<%= odontologo.getEspecialidad()%>">
                    </div>
                </div>

                <hr class="my-4">

                <div class="row bg-light p-3 rounded shadow-sm mb-4">
                    <div class="col-12">
                        <h5 class="text-primary"><i class="fas fa-lock me-2"></i> Gestión de Credenciales de Acceso</h5>
                    </div>
                    <div class="col-md-8">
                        <% if (odontologo.getUsuario() != null) {%>
                        <p class="mb-0">
                            El usuario de acceso asociado es: 
                            <strong class="text-dark"><%= odontologo.getUsuario().getNombre_usuario()%></strong> 
                            (ID Usuario: <%= odontologo.getUsuario().getId_usuario()%>).
                        </p>
                        <small class="text-danger">Para mantener la seguridad, las credenciales (Usuario/Contraseña/Rol) se editan por separado.</small>
                        <% } else { %>
                        <p class="mb-0 text-danger">Este odontólogo no tiene un usuario de acceso asociado.</p>
                        <% } %>
                    </div>
                    <div class="col-md-4 text-end d-flex align-items-center justify-content-end">
                        <% if (odontologo.getUsuario() != null) {%>
                        <a href="SvEditarUsuario?idEditar=<%= odontologo.getUsuario().getId_usuario()%>" 
                           class="btn btn-warning shadow-sm px-4">
                            <i class="fas fa-user-lock me-2"></i> Editar Credenciales
                        </a>
                        <% } else { %>
                        <a href="cargarUsuario.jsp" class="btn btn-outline-success shadow-sm px-4" title="Crea un nuevo usuario y asócialo manualmente después.">
                            <i class="fas fa-user-plus me-2"></i> Crear Usuario
                        </a>
                        <% }%>
                    </div>
                </div>

                <hr>

                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-success btn-lg shadow-sm px-5">
                        <i class="fas fa-save me-2"></i> Guardar Datos del Odontólogo
                    </button>
                </div>

            </form>

        </div>
    </div>
</div>

<%@include file="components/body-segunda_parte.jsp"  %>
