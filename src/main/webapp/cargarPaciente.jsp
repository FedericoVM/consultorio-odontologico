<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp" %>
<div class="container">
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0 ">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-11">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-1">Registro de paciente</h1>
                            <p class="mb-4">Completa los campos para crear un nuevo perfil de paciente</p>
                        </div>
                        <form class="user" method="POST" action="SvPacientes">

                            <div class="form-group text-center mb-4">
                                <label class="mr-3 font-weight-bold text-gray-800">¿El paciente es?:</label>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="tipo_edad" id="radioMayor" value="mayor" checked onchange="toggleMenor()">
                                    <label class="form-check-label" for="radioMayor">Mayor de edad (+18)</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="tipo_edad" id="radioMenor" value="menor" onchange="toggleMenor()">
                                    <label class="form-check-label" for="radioMenor">Menor de edad (-18)</label>
                                </div>
                            </div>

                            <h5 class="text-gray-800 mb-3">Datos del Paciente</h5>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <div class="form-group">
                                        <label for="nombre_paciente">Nombre</label>
                                        <input type="text" class="form-control form-control-user" name="nombre_paciente" id="nombre_paciente" placeholder="Escriba el nombre">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="apellido_paciente">Apellido</label>
                                        <input type="text" class="form-control form-control-user" name="apellido_paciente" id="apellido_paciente" placeholder="Escriba el apellido" required>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="telefono-paciente">Teléfono Personal</label>
                                        <input type="text" class="form-control form-control-user" name="telefono_paciente" id="telefono-paciente" placeholder="Ej: 3815xxxxxx" required>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="fecha_nac_paciente">Fecha de Nacimiento</label>
                                        <input type="date" class="form-control form-control-user" name="fecha_nac_paciente" id="fecha_nac_paciente" required>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <div class="form-group">
                                        <label for="dni_paciente">DNI</label>
                                        <input type="text" class="form-control form-control-user" name="dni_paciente" id="dni_paciente" placeholder="Ej: 30123456" required>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="tipo_sangre">Tipo de Sangre</label>
                                        <input type="text" class="form-control form-control-user" name="tipo_sangre" id="tipo_sangre" placeholder="Ej: A+, 0-, etc.">
                                    </div>
                                </div>
                            </div>

                            <h5 class="text-gray-800 mb-3">Datos de Obra Social</h5>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <div class="form-group">
                                        <label for="nombre_obra_social">Obra Social</label>
                                        <input type="text" class="form-control form-control-user" name="nombre_obra_social" id="nombre_obra_social" placeholder="Nombre de la Obra Social" required>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="num_afiliado">N° de Afiliado</label>
                                        <input type="text" class="form-control form-control-user" name="num_afiliado" id="num_afiliado" placeholder="Número de Afiliado" required>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="telefono_obra_social">Teléfono de la Obra Social</label>
                                        <input type="text" class="form-control form-control-user" name="telefono_obra_social" id="telefono_obra_social" placeholder="Teléfono de contacto de la OS" required>
                                    </div>
                                </div>
                            </div>

                            <hr class="mt-5">

                            <div id="seccion-menor-cargo" class="d-none">
                                <h4 class="text-primary">Datos del Responsable / Tutor</h4>
                                <p class="mb-4">Complete únicamente si el paciente es menor de edad.</p>

                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <div class="form-group">
                                            <label for="nombre-tutor">Nombre del Tutor</label>
                                            <input type="text" class="form-control form-control-user" name="nombre_tutor" id="nombre-tutor" placeholder="Nombre del Tutor/Responsable">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="apellido-tutor">Apellido del Tutor</label>
                                            <input type="text" class="form-control form-control-user" name="apellido_tutor" id="apellido-tutor" placeholder="Apellido del Tutor/Responsable">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="dni-tutor">DNI del Tutor</label>
                                            <input type="text" class="form-control form-control-user" name="dni_tutor" id="dni-tutor" placeholder="DNI del Tutor/Responsable">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="fecha_nac-tutor">Fecha Nac. del Tutor</label>
                                            <input type="date" class="form-control form-control-user" name="fecha_nac_tutor" id="fecha_nac-tutor">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="telefono-tutor">Teléfono del Tutor</label>
                                            <input type="text" class="form-control form-control-user" name="telefono_tutor" id="telefono-tutor" placeholder="Teléfono del Tutor/Responsable">
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="relacion-tutor">Relación con el menor</label>
                                            <input type="text" class="form-control form-control-user" name="relacion_tutor" id="relacion-tutor" placeholder="Ej: Padre, Madre, Tutor Legal">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <button type="submit" class="btn btn-primary btn-user btn-block mt-3">
                                Registrar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="components/body-segunda_parte.jsp" %>

