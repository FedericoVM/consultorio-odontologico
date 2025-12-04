<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@include file="components/head.jsp"  %>
<%@include file="components/body-primera_parte.jsp"  %>



<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">🏥 Panel de Administración Clínica</h1>
</div>

<div class="row">

    <div class="col-xl-12 col-lg-12">
        <div class="card shadow mb-4">

            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">¡Bienvenido(a) al Sistema de Gestión de Turnos!</h6>
            </div>

            <div class="card-body text-center">

                <h4 class="text-gray-900 mb-4">Gestión Odontológica Eficiente</h4>

                <div class="text-center mb-4">

                </div>

                <p class="mb-5">
                    Utiliza el menú de navegación a la izquierda para acceder a las funciones principales: 
                    **Turnos**, **Pacientes** y **Odontólogos**.
                    Aquí podrás agendar nuevas citas, editar registros y mantener toda la información clínica organizada.
                </p>

                <hr>

                <h5 class="text-gray-800 mb-3">Acciones Rápidas</h5>
                <div class="row justify-content-center">

                    <div class="col-md-4 mb-2">
                        <a href="turno.jsp" class="btn btn-primary btn-icon-split btn-lg w-100">
                            <span class="icon text-white-50"><i class="fas fa-calendar-plus"></i></span>
                            <span class="text">Agendar Nuevo Turno</span>
                        </a>
                    </div>

                    <div class="col-md-4 mb-2">
                        <a href="SvTurnos" class="btn btn-info btn-icon-split btn-lg w-100">
                            <span class="icon text-white-50"><i class="fas fa-list-alt"></i></span>
                            <span class="text">Ver Lista de Turnos</span>
                        </a>
                    </div>

                </div>

                <hr class="mt-5">

            </div>
        </div>
    </div>



    <%@include file="components/body-segunda_parte.jsp"  %>

