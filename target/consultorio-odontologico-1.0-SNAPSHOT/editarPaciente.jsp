<%@page import="logica.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<% Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteEditar");%>
<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp" %>

<div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
            <div class="col-lg-11 mx-auto">
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-1">Editar Paciente</h1>
                        <p class="mb-4">Modifica los campos necesarios para actualizar el perfil del paciente: **<%= paciente.getNombre() + " " + paciente.getApellido()%>**</p>
                    </div>

                    <form class="user" method="POST" action="SvEditarPaciente">

                        <input type="hidden" name="id_paciente" value="<%= paciente.getId()%>">

                        <div class="form-group text-center mb-4">
                            <label class="mr-3 font-weight-bold text-gray-800">¿El paciente es?:</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="tipo_edad" id="radioMayor" value="mayor" 
                                       <%= paciente.getMayorEdad() ? "checked" : ""%> onchange="toggleMenor()">
                                <label class="form-check-label" for="radioMayor">Mayor de edad (+18)</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="tipo_edad" id="radioMenor" value="menor" 
                                       <%= !paciente.getMayorEdad() ? "checked" : ""%> onchange="toggleMenor()">
                                <label class="form-check-label" for="radioMenor">Menor de edad (-18)</label>
                            </div>
                        </div>

                        <hr>

                        <h5 class="text-gray-800 mb-3">Datos del Paciente</h5>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <div class="form-group">
                                    <label for="nombre_paciente">Nombre</label>
                                    <input type="text" class="form-control form-control-user" name="nombre_paciente" id="nombre_paciente" placeholder="Nombre" 
                                           value="<%= paciente.getNombre()%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="apellido_paciente">Apellido</label>
                                    <input type="text" class="form-control form-control-user" name="apellido_paciente" id="apellido_paciente" placeholder="Apellido" 
                                           value="<%= paciente.getApellido()%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="telefono-paciente">Teléfono Personal</label>
                                    <input type="text" class="form-control form-control-user" name="telefono_paciente" id="telefono-paciente" placeholder="Teléfono Personal" 
                                           value="<%= paciente.getTelefono()%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="dni_paciente">DNI</label>
                                    <input type="text" class="form-control form-control-user" name="dni_paciente" id="dni_paciente" placeholder="DNI" 
                                           value="<%= paciente.getDni()%>">
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="fecha_nac_paciente">Fecha de Nacimiento</label>
                                    <input type="date" class="form-control form-control-user" name="fecha_nac_paciente" id="fecha_nac_paciente" 
                                           value="<%= paciente.getFecha_nac()%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="tipo_sangre">Tipo de Sangre</label>
                                    <input type="text" class="form-control form-control-user" name="tipo_sangre" id="tipo_sangre" placeholder="Tipo de Sangre"
                                           value="<%= paciente.getTipoSangre()%>">
                                </div>
                            </div>
                        </div>

                        <hr>

                        <h5 class="text-gray-800 mb-3">Datos de Obra Social</h5>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <div class="form-group">
                                    <label for="nombre_obra_social">Obra Social</label>
                                    <input type="text" class="form-control form-control-user" name="nombre_obra_social" id="nombre_obra_social" placeholder="Obra Social" 
                                           value="<%= paciente.getObraSocial().getNombreObra()%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="num_afiliado">N° de Afiliado</label>
                                    <input type="text" class="form-control form-control-user" name="num_afiliado" id="num_afiliado" placeholder="N° de afiliado" 
                                           value="<%= paciente.getNumAfiliado() != null ? paciente.getNumAfiliado() : ""%>">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="telefono_obra_social">Teléfono Obra Social</label>
                                    <input type="text" class="form-control form-control-user" name="telefono_obra_social" id="telefono_obra_social" placeholder="Teléfono Obra social" 
                                           value="<%= paciente.getObraSocial().getTelefono() != null ? paciente.getObraSocial().getTelefono() : ""%>">
                                </div>
                            </div>
                        </div>

                        <hr class="mt-5">

                        <% if (paciente.getResponsable() != null) {%>
                        <div id="seccion-menor-cargo" class="<%= !paciente.getMayorEdad() ? "" : "d-none"%>">
                            <h4 class="text-primary">Datos del Responsable / Tutor</h4>
                            <p class="mb-4">Complete únicamente si el paciente es menor de edad.</p>

                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <div class="form-group">
                                        <label for="nombre-tutor">Nombre del Tutor</label>
                                        <input type="text" class="form-control form-control-user" name="nombre_tutor" id="nombre-tutor" placeholder="Nombre del Tutor" 
                                               value="<%= paciente.getResponsable().getNombre()%>">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="apellido-tutor">Apellido del Tutor</label>
                                        <input type="text" class="form-control form-control-user" name="apellido_tutor" id="apellido-tutor" placeholder="Apellido del Tutor" 
                                               value="<%= paciente.getResponsable().getApellido()%>">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="dni-tutor">DNI del Tutor</label>
                                        <input type="text" class="form-control form-control-user" name="dni_tutor" id="dni-tutor" placeholder="DNI del Tutor" 
                                               value="<%= paciente.getResponsable().getDni()%>">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="fecha_nac-tutor">Fecha Nac. del Tutor</label>
                                        <input type="date" class="form-control form-control-user" name="fecha_nac_tutor" id="fecha_nac-tutor" 
                                               value="<%= paciente.getResponsable().getFecha_nac()%>">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="telefono-tutor">Teléfono del Tutor</label>
                                        <input type="text" class="form-control form-control-user" name="telefono_tutor" id="telefono-tutor" placeholder="Teléfono del Tutor" 
                                               value="<%= paciente.getResponsable().getTelefono()%>">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="relacion-tutor">Relación con el menor</label>
                                        <input type="text" class="form-control form-control-user" name="relacion_tutor" id="relacion-tutor" placeholder="Relación con el menor (Padre/Madre/Tutor)" 
                                               value="<%= paciente.getResponsable().getTipo_responsable() != null ? paciente.getResponsable().getTipo_responsable() : ""%>">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }%>

                        <hr>
                        <button type="submit" class="btn btn-primary btn-user btn-block mt-3">
                            Guardar Cambios
                        </button>
                        <a href="SvPacientes" class="btn btn-secondary btn-user btn-block mt-2">
                            Cancelar
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Script para manejar la visibilidad de la sección del Responsable -->
<script>
    function toggleMenor() {
        var radioMenor = document.getElementById('radioMenor');
        var seccionMenor = document.getElementById('seccion-menor-cargo');

        if (radioMenor.checked) {
            seccionMenor.classList.remove('d-none');
        } else {
            seccionMenor.classList.add('d-none');
        }
    }

    document.addEventListener('DOMContentLoaded', function () {
        toggleMenor();
    });
</script>


<%@include file="components/body-segunda_parte.jsp" %>