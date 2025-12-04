<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>

<h1 class="h3 mb-2 text-gray-800"><i class="fas fa-user-plus me-2"></i> Crear Nuevo Usuario</h1>
<p class="mb-4">Complete los datos para crear una nueva credencial de acceso para personal administrativo.</p>

<div class="d-flex justify-content-start mb-4">
    <a href="SvUsuarios" class="btn btn-secondary shadow-sm px-4">
        <i class="fas fa-arrow-left me-2"></i> Volver a Usuarios
    </a>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Datos del Nuevo Usuario</h6>
    </div>
    <div class="card-body">

        <form action="SvUsuarios" method="POST">

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="nombre_usuario" class="form-label font-weight-bold text-gray-800">Nombre de Usuario:</label>
                    <input type="text" class="form-control" id="nombre_usuario" name="nombre_usuario" 
                           placeholder="Ej: admin.principal" required>
                    <small class="form-text text-muted">Será el nombre utilizado para iniciar sesión.</small>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="contrasenia" class="form-label font-weight-bold text-gray-800">Contraseña:</label>
                    <input type="password" class="form-control" id="contrasenia" name="contrasenia" 
                           placeholder="Ingrese una contraseña segura" required autocomplete="new-password">
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <label for="rol" class="form-label font-weight-bold text-gray-800">Rol:</label>
                    <select class="form-control" id="rol" name="rol" required>
                        <option value="" disabled selected>Seleccione el Rol</option>
                        <option value="Administrador">Administrador</option>
                        <option value="Secretario">Secretario</option>
                    </select>
                    <small class="form-text text-danger">Solo se permite la creación de roles administrativos desde este formulario.</small>
                </div>

                <div class="col-md-6 mb-4">
                    <label class="form-label font-weight-bold text-gray-800">ID de Persona Asociada:</label>
                    <input type="text" class="form-control" value="" disabled placeholder="Se asocia luego desde Odontólogos/Secretarios">
                    <small class="form-text text-muted">Este campo se enlaza manualmente una vez creado el usuario.</small>
                </div>
            </div>

            <hr>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary btn-lg shadow-sm px-5">
                    <i class="fas fa-check me-2"></i> Registrar Usuario
                </button>
            </div>

        </form>

    </div>
</div>

<%@include file="components/body-segunda_parte.jsp"  %>