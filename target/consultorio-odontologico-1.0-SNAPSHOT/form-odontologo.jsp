
<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp" %>

<div class="container py-4">
    <div class="text-center mb-5">
        <h1 class="h3 text-gray-900 fw-bold"><i class="fas fa-user-md me-2"></i> Registrar Nuevo Odontólogo</h1>
        <p class="text-muted">Complete todos los campos para dar de alta a un profesional en el sistema.</p>
    </div>


    <form class="user" method="POST" action="SvOdontologos">


        <div class="card shadow-lg mb-5">
            <div class="card-header bg-primary text-white py-3">
                <h5 class="m-0 fw-bold"><i class="fas fa-id-card me-2"></i> Datos Generales</h5>
            </div>
            <div class="card-body p-4 p-md-5">
                <div class="row g-4">

                    <div class="col-md-6">
                        <label for="nombre-odontologo" class="form-label">Nombre</label>
                        <input type="text" class="form-control" name="nombre-odontologo" id="nombre-odontologo" placeholder="Nombre del odontólogo" required>
                    </div>
                    <div class="col-md-6">
                        <label for="apellido-odontologo" class="form-label">Apellido</label>
                        <input type="text" class="form-control" name="apellido-odontologo" id="apellido-odontologo" placeholder="Apellido del odontólogo" required>
                    </div>

                    <div class="col-md-6">
                        <label for="dni-odontologo" class="form-label">DNI</label>
                        <input type="text" class="form-control" name="dni-odontologo" id="dni-odontologo" placeholder="Número de Documento" required>
                    </div>
                    <div class="col-md-6">
                        <label for="fecha_nac-odontologo" class="form-label">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" name="fecha_nac-odontologo" id="fecha_nac-odontologo" required>
                    </div>


                    <div class="col-md-6">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" name="telefono" id="telefono" placeholder="Número de contacto (Ej: +54 9...)" required>
                    </div>

                    <div class="col-md-6">
                        <label for="especialidad" class="form-label">Especialidad</label>
                        <input type="text" class="form-control" name="especialidad" id="especialidad" placeholder="Ej: Odontología General, Endodoncia" required>
                    </div>
                    <div class="col-md-6">
                        <label for="matricula" class="form-label">Matrícula</label>
                        <input type="text" class="form-control" name="matricula" id="matricula" placeholder="Número de Matrícula Profesional" required>
                    </div>

                </div>
            </div>
        </div>


        <div class="card shadow-lg mb-5">
            <div class="card-header bg-secondary text-white py-3">
                <h5 class="m-0 fw-bold"><i class="fas fa-lock me-2"></i> Credenciales de Acceso</h5>
            </div>
            <div class="card-body p-4 p-md-5">
                <div class="row g-4">
                    <div class="col-md-6">
                        <label for="usuario" class="form-label">Nombre de Usuario</label>
                        <input type="text" class="form-control" name="usuario" id="usuario" placeholder="Nombre de usuario para el login" required>
                    </div>
                    <div class="col-md-6">
                        <label for="contrasenia" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" name="contrasenia" id="contrasenia" placeholder="Contraseña de acceso" required>
                    </div>
                </div>
            </div>
        </div>


        <div class="card shadow-lg mb-5">
            <div class="card-header bg-info text-white py-3">
                <h5 class="m-0 fw-bold"><i class="fas fa-clock me-2"></i> Horarios de Atención</h5>
            </div>
            <div class="card-body p-4 p-md-5">

                <%                    // Días de la semana para iterar
                    String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes"};
                %>

                <div class="vstack gap-4">
                    <% for (String dia : dias) {%>
                    <div class="row g-3 align-items-center">
                        <div class="col-12 col-md-3">
                            <label class="fw-bold text-capitalize"><%= dia%></label>
                        </div>
                        <div class="col-12 col-md-9">
                            <div class="input-group">
                                <span class="input-group-text bg-light"><i class="fas fa-calendar-day"></i> Desde:</span>
                                <input type="time" class="form-control" name="<%= dia%>_inicio" aria-label="Hora de inicio de <%= dia%>">
                                <span class="input-group-text"><i class="fas fa-minus"></i></span>
                                <span class="input-group-text bg-light">Hasta:</span>
                                <input type="time" class="form-control" name="<%= dia%>_fin" aria-label="Hora de fin de <%= dia%>">
                            </div>
                        </div>
                    </div>
                    <% }%>
                </div>

            </div>
        </div>

        <div class="d-grid mb-5">
            <button type="submit" class="btn btn-primary btn-user btn-lg shadow-lg">
                <i class="fas fa-save me-2"></i> Registrar Odontólogo
            </button>
        </div>
    </form>
</div>

<%@include file="components/body-segunda_parte.jsp" %>