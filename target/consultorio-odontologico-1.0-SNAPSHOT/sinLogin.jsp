<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/head.jsp" %>

<div class="container-fluid">

    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 text-center">
            
            <div class="error mx-auto mt-5" data-text="Acceso Denegado">Acceso Denegado</div>
            
            <p class="lead text-gray-800 mb-5">
                Su sesión ha expirado o no ha iniciado sesión.
            </p>
            
            <p class="text-gray-500 mb-4">
                Para acceder a este recurso, por favor, ingrese sus credenciales.
                La seguridad de su información es nuestra prioridad.
            </p>
            
            <a href="login.jsp" class="btn btn-primary btn-lg shadow-sm">
                <i class="fas fa-sign-in-alt fa-sm fa-fw mr-2"></i> 
                Volver a Iniciar Sesión
            </a>
            
        </div>
    </div>
</div>