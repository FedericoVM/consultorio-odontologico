<%@page import="java.util.List"%>
<%@page import="logica.FranjaHoraria"%>
<%@page import="logica.Odontologo"%>

    <% Odontologo odontologo = (Odontologo) request.getSession().getAttribute("odontologoEncontrado");%>

    <%@include file="components/head.jsp" %>
    <%@include file="components/body-primera_parte.jsp" %>


<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex justify-content-between align-items-center">
        <h5 class="m-0 font-weight-bold text-primary">
            <i class="fas fa-user-md mr-2"></i> Detalle del Profesional
        </h5>
        <h6 class="m-0 text-muted">ID #<%= odontologo != null ? odontologo.getId() : "N/A"%></h6>
    </div>

    <div class="card-body">

        <% if (odontologo != null) {%>

        <h2 class="h5 text-gray-900 mb-4 border-bottom pb-2">Información Personal y Contacto</h2>

        <div class="row">

            <div class="col-md-6 mb-3">
                <label class="small text-uppercase text-muted">Nombre Completo:</label>
                <p class="h6 text-gray-800">
                    <%= odontologo.getNombre()%> <%= odontologo.getApellido()%>
                </p>
            </div>


            <div class="col-md-3 mb-3">
                <label class="small text-uppercase text-muted">DNI:</label>
                <p class="h6 text-gray-800"><%= odontologo.getDni()%></p>
            </div>
            <div class="col-md-3 mb-3">
                <label class="small text-uppercase text-muted">Fecha Nacimiento:</label>
                <p class="h6 text-gray-800"><%= odontologo.getFecha_nac()%></p>
            </div>


            <div class="col-md-6 mb-3">
                <label class="small text-uppercase text-muted">Correo Electrónico:</label>
                <p class="h6 text-gray-800"><%= odontologo.getEmail()%></p>
            </div>
            <div class="col-md-6 mb-3">
                <label class="small text-uppercase text-muted">Teléfono:</label>
                <p class="h6 text-gray-800"><%= odontologo.getTelefono()%></p>
            </div>

        </div>


        <hr class="my-4">
        <h2 class="h5 text-gray-900 mb-4 border-bottom pb-2">Datos Profesionales y Credenciales</h2>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="small text-uppercase text-muted">Especialidad:</label>
                <p class="h6 text-gray-800"><%= odontologo.getEspecialidad()%></p>
            </div>
            <div class="col-md-4 mb-3">
                <label class="small text-uppercase text-muted">Matrícula:</label>
                <p class="h6 text-gray-800"><%= odontologo.getNumeroMatricula()%></p>
            </div>
            <div class="col-md-4 mb-3">
                <label class="small text-uppercase text-muted">Usuario Asociado:</label>
                <p class="h6 text-gray-800">
                    <i class="fas fa-user-circle mr-1"></i>
                    <%= odontologo.getUsuario().getNombre_usuario()%>
                </p>
            </div>
        </div>



        <hr class="my-4">
        <h2 class="h5 text-gray-900 mb-4 border-bottom pb-2">Horarios de Atención</h2>

        <div class="row">
            <%
                List<FranjaHoraria> horarios = odontologo.getHorarios();
                if (horarios != null && !horarios.isEmpty()) {
                    for (FranjaHoraria horario : horarios) {
            %>
            <!-- Uso de Tarjeta para cada horario (funciona bien) -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                    <i class="fas fa-calendar-day mr-1"></i>
                                    <%= horario.getDia_semana()%>
                                </div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                    <%= horario.getHora_inicio()%> - <%= horario.getHora_fin()%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% }
            } else { %>
            <div class="col-12">
                <p class="text-muted">El odontólogo no tiene horarios de atención definidos.</p>
            </div>
            <% } %>
        </div>

        <% } else { %>
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Error de Carga</h4>
            <p>No se pudo cargar la información del odontólogo. Por favor, asegúrese de que el ID es correcto.</p>
        </div>
        <% }%>


        <div class="d-flex justify-content-center mt-5">

            <a href="odontologos.jsp" class="btn btn-secondary shadow-sm mx-2">
                <i class="fas fa-arrow-left"></i> Volver a Odontólogos
            </a>

            <form name="editarOdontologo" method="GET" action="SvEditarOdontologo" class="mx-2">
                <input type="hidden" name="idEditar" value = "<%= odontologo != null ? odontologo.getId() : ""%>" > 
                <button type="submit" class="btn btn-warning shadow-sm">
                    <i class="fas fa-edit"></i> Editar
                </button>
            </form>
        </div>

    </div>
</div>

        <%@include file="components/body-segunda_parte.jsp" %>

