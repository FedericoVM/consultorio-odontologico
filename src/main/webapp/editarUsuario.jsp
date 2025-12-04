<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<% Usuario usuarioEncontrado = (Usuario) request.getSession().getAttribute("usuarioEditar");%>

<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-user-edit me-2"></i> Editar Usuario</h1>
<p class="mb-4">Modifique las credenciales y el rol de acceso del usuario seleccionado.</p>

<div class="d-flex justify-content-start mb-4">
    <a href="SvUsuarios" class="btn btn-secondary shadow-sm px-4">
        <i class="fas fa-arrow-left me-2"></i> Volver a Usuarios
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Formulario de Edición para ID: <%= usuarioEncontrado.getId_usuario()%></h6>
    </div>
    <div class="card-body">

        <form action="SvEditarUsuario" method="POST">

            <input type="hidden" name="id_usuario" value="<%= usuarioEncontrado.getId_usuario()%>">

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="nombre_usuario" class="form-label font-weight-bold text-gray-800">Nombre de Usuario:</label>
                    <input type="text" class="form-control" id="nombre_usuario" name="nombre_usuario" 
                           value="<%= usuarioEncontrado.getNombre_usuario()%>" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="contrasenia" class="form-label font-weight-bold text-gray-800">Nueva Contraseña:</label>
                    <input type="password" class="form-control" id="contrasenia" name="contrasenia" 
                           placeholder="Dejar vacío para no cambiar" autocomplete="new-password">
                    <small class="form-text text-muted">Solo ingrese una contraseña si desea cambiarla.</small>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <label for="rol" class="form-label font-weight-bold text-gray-800">Rol:</label>
                    <select class="form-control" id="rol" name="rol" required>
                        <option value="Administrador" <%= "Administrador".equals(usuario.getRol()) ? "selected" : ""%>>Administrador</option>
                        <option value="Secretario" <%= "Secretario".equals(usuario.getRol()) ? "selected" : ""%>>Secretario</option>
                        <option value="Odontologo" <%= "Odontologo".equals(usuario.getRol()) ? "selected" : ""%>>Odontólogo</option>
                    </select>
                </div>
            </div>

            <hr>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary btn-lg shadow-sm px-5">
                    <i class="fas fa-save me-2"></i> Guardar Cambios
                </button>
            </div>

        </form>

    </div>
</div>


<%@include file="components/body-segunda_parte.jsp"  %>