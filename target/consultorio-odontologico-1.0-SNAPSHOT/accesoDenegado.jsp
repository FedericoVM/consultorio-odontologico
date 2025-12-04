<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp"  %>
<div class="container-fluid">

    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 text-center">

            <h1 class="display-1 font-weight-bold text-danger mt-5">
                <i class="fas fa-lock-open fa-3x"></i> 
                Acceso Denegado
            </h1>

            <p class="lead text-gray-800 mb-5 mt-4">
                Usted no tiene permisos para ver este recurso.
            </p>
            
            <p class="text-gray-600 mb-4">
                El recurso al que intenta acceder requiere un **rol de usuario** diferente. 
                Por favor, regrese a su panel de control o contacte al administrador del sistema si cree que esto es un error.
            </p>

            <div class="alert alert-warning d-inline-block p-2">
                <i class="fas fa-user-tag mr-2"></i> Su rol actual es: 
                <span class="font-weight-bold"><%= usuario.getRol() %></span>
            </div>
            
            <div class="mt-4">
                <a href="index.jsp" class="btn btn-primary btn-lg shadow-sm">
                    <i class="fas fa-arrow-left fa-sm fa-fw mr-2"></i> 
                    Volver a mi Panel de Control
                </a>
            </div>
            
        </div>
    </div>
</div>

