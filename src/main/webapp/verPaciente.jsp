<%@page import="logica.Paciente"%>

<%
    Paciente pacienteEncontrado = (Paciente) request.getSession().getAttribute("pacienteEncontrado");
%>

<%@include file="components/head.jsp" %>
<%@include file="components/body-primera_parte.jsp" %>


<style>
    .detail-label {
        font-weight: 500;
        color: #4e73df; /* Color primario de Bootstrap/SB Admin */
        font-size: 0.9rem;
        margin-bottom: 0.2rem;
        display: block;
    }
    .detail-value {
        font-weight: 700;
        color: #333;
        font-size: 1.1rem;
        padding-bottom: 1rem;
        border-bottom: 1px dashed #e3e6f0; /* Separador sutil */
        margin-bottom: 1rem;
    }
    .section-title {
        border-bottom: 3px solid #4e73df;
        padding-bottom: 0.5rem;
        margin-bottom: 2rem !important;
        font-weight: 700;
    }
    .data-card {
        border-left: 0.25rem solid #4e73df; /* Borde de color primario */
    }
</style>

<div class="container my-5">
    <div class="card o-hidden border-0 shadow-lg data-card">
        <div class="card-body p-0">
            <div class="row">
                <div class="col-lg-12">
                    <div class="p-5">
                        <h1 class="h3 text-gray-900 section-title">
                            <i class="fas fa-user-injured me-2 text-primary"></i> Expediente del Paciente
                        </h1>

                        <% if (pacienteEncontrado != null) {%>


                        <h4 class="mb-4 text-primary"><i class="fas fa-id-card me-2"></i> Datos Generales</h4>

                        <div class="row">

                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Nombre Completo:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getNombre()%> <%= pacienteEncontrado.getApellido()%>
                                    </div>
                                </div>

                                <div class="data-group">
                                    <span class="detail-label">DNI:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getDni()%>
                                    </div>
                                </div>

                                <div class="data-group">
                                    <span class="detail-label">Tipo de Sangre:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getTipoSangre()%>
                                    </div>
                                </div>
                            </div>


                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Fecha de Nacimiento:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getFecha_nac()%>
                                    </div>
                                </div>

                                <div class="data-group">
                                    <span class="detail-label">Teléfono:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getTelefono()%>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <hr class="my-5">


                        <h4 class="mb-4 text-primary"><i class="fas fa-medkit me-2"></i> Información de Obra Social</h4>

                        <% if (pacienteEncontrado.getObraSocial() != null) {%>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Nombre de Obra Social:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getObraSocial().getNombreObra()%>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">N° de Afiliado:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getNumAfiliado()%>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Teléfono de Contacto:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getObraSocial().getTelefono()%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } else { %>
                        <div class="alert alert-warning text-center shadow-sm" role="alert">
                            <i class="fas fa-exclamation-triangle me-2"></i> El paciente no tiene Obra Social asignada.
                        </div>
                        <% } %>

                        <hr class="my-5">


                        <h4 class="mb-4 text-primary"><i class="fas fa-user-shield me-2"></i> Responsable / Tutor</h4>

                        <% if (pacienteEncontrado.getResponsable() != null) {%>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Nombre del Tutor:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getResponsable().getNombre()%> <%= pacienteEncontrado.getResponsable().getApellido()%>
                                    </div>
                                </div>

                                <div class="data-group">
                                    <span class="detail-label">DNI del Tutor:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getResponsable().getDni()%>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="data-group">
                                    <span class="detail-label">Relación con el Paciente:</span>
                                    <div class="detail-value">
                                        <%= pacienteEncontrado.getResponsable().getTipo_responsable()%>
                                    </div>
                                </div>

                                <div class="data-group">
                                    <span class="detail-label">Teléfono del Tutor:</span>
                                    <div class="detail-value">
                                        <%=  pacienteEncontrado.getResponsable().getTelefono()%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } else { %>
                        <div class="alert alert-info text-center shadow-sm" role="alert">
                            <i class="fas fa-info-circle me-2"></i> El paciente es mayor de edad. No se requiere o no se ha registrado un responsable.
                        </div>
                        <% }%>

                        <% } else { %>
                        <div class="alert alert-danger text-center" role="alert">
                            No se pudo cargar la información del paciente.
                        </div>
                        <% }%>

                        <hr class="mt-5">
                        <div class="d-flex justify-content-between pt-3">
                            <!-- Botón Volver -->
                            <a href="pacientes.jsp" class="btn btn-secondary shadow-sm px-4 align-self-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-chevrons-left me-1"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M11 7l-5 5l5 5" /><path d="M17 7l-5 5l5 5" /></svg>
                                Volver a Pacientes
                            </a>

                            <form name="editarPaciente" method="GET" action="SvEditarPaciente">
                                <button type="submit" class="btn btn-primary shadow-sm px-4 text-white">
                                    <i class="fas fa-edit me-1"></i> Editar Paciente
                                    <input type="hidden" name="idEditar" value="<%= pacienteEncontrado != null ? pacienteEncontrado.getId() : ""%>">
                                </button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="components/body-segunda_parte.jsp" %>
