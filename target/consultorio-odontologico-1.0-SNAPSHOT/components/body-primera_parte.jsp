
<%@page import="logica.Usuario"%>
<%-- Validadcion de sesion  --%>

<%
    HttpSession miSesion = request.getSession();

    // VALIDACION SI EXISTE EL USUARIO
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioEncontrado");
    if (usuario == null) {
        response.sendRedirect("sinLogin.jsp");
        return;
    }

%>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <!-- Icono de Diente SVG para el logo de la Clínica -->
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="icon icon-tabler icons-tabler-outline icon-tabler-dental">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                        <path
                            d="M12 5.5c-1.074 -.586 -2.583 -1.5 -4 -1.5c-2.1 0 -4 1.247 -4 5c0 4.899 1.056 8.41 2.671 10.537c.573 .756 1.97 .521 2.567 -.236c.398 -.505 .819 -1.439 1.262 -2.801c.292 -.771 .892 -1.504 1.5 -1.5c.602 0 1.21 .737 1.5 1.5c.443 1.362 .864 2.295 1.262 2.8c.597 .759 2 .993 2.567 .237c1.615 -2.127 2.671 -5.637 2.671 -10.537c0 -3.74 -1.908 -5 -4 -5c-1.423 0 -2.92 .911 -4 1.5z" />
                        <path d="M12 5.5l3 1.5" />
                    </svg>
                </div>
                <div class="sidebar-brand-text mx-3">Consultorio Odontologico</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

                <!-- Nav Item - Inicio (Dashboard) -->
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">
                        <!-- ICONO: Inicio (Mantengo el estándar del template, que es Dashboard) -->
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Inicio</span>
                    </a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">


                    <!-- Nav Item - Odontólogos -->
                    <li class="nav-item">
                        <a class="nav-link" href="SvOdontologos">
                            <!-- ICONO: Odontólogos (Diente) -->
                            <i class="fas fa-fw fa-tooth"></i>
                            <span>Odontólogos</span>
                        </a>
                    </li>

                    <!-- Nav Item - Pacientes -->
                    <li class="nav-item">
                        <a class="nav-link" href="SvPacientes">
                            <!-- ICONO: Pacientes (Grupo de Usuarios) -->
                            <i class="fas fa-fw fa-users"></i>
                            <span>Pacientes</span>
                        </a>
                    </li>

                    <!-- Nav Item - Turnos -->
                    <li class="nav-item">
                        <a class="nav-link" href="SvTurnos">
                            <!-- ICONO: Turnos (Calendario con Check) -->
                            <i class="fas fa-fw fa-calendar-check"></i>
                            <span>Turnos</span>
                        </a>
                    </li>

                    <!-- Nav Item - Usuarios -->
                    <li class="nav-item">
                        <a class="nav-link" href="SvUsuarios">
                            <!-- ICONO: Usuarios (Perfil) -->
                            <i class="fas fa-fw fa-user-circle"></i>
                            <span>Usuarios</span>
                        </a>
                    </li>


                    <!-- Divider -->
                    <hr class="sidebar-divider">


                        <!-- Divider -->
                        <hr class="sidebar-divider d-none d-md-block">

                            <!-- Sidebar Toggler (Sidebar) -->
                            <div class="text-center d-none d-md-inline">
                                <button class="rounded-circle border-0" id="sidebarToggle"></button>
                            </div>

                            <!-- Sidebar Message -->
                            <div class="sidebar-card d-none d-lg-flex">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none"
                                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                     class="icon icon-tabler icons-tabler-outline icon-tabler-dental">
                                    <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                                    <path
                                        d="M12 5.5c-1.074 -.586 -2.583 -1.5 -4 -1.5c-2.1 0 -4 1.247 -4 5c0 4.899 1.056 8.41 2.671 10.537c.573 .756 1.97 .521 2.567 -.236c.398 -.505 .819 -1.439 1.262 -2.801c.292 -.771 .892 -1.504 1.5 -1.5c.602 0 1.21 .737 1.5 1.5c.443 1.362 .864 2.295 1.262 2.8c.597 .759 2 .993 2.567 .237c1.615 -2.127 2.671 -5.637 2.671 -10.537c0 -3.74 -1.908 -5 -4 -5c-1.423 0 -2.92 .911 -4 1.5z" />
                                    <path d="M12 5.5l3 1.5" />
                                </svg>

                                <p class="text-center mb-2">
                                    <strong>Agenda de Turnos:</strong> 
                                    Revisa las citas de hoy y organiza las tareas para una gestión óptima.
                                </p>
                            </div>

                            </ul>
                            <!-- End of Sidebar -->

                            <!-- Content Wrapper -->
                            <div id="content-wrapper" class="d-flex flex-column">

                                <!-- Main Content -->
                                <div id="content">
                                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                                        <!-- Sidebar Toggle (Topbar) -->
                                        <form class="form-inline">
                                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                                <i class="fa fa-bars"></i>
                                            </button>
                                        </form>


                                        <!-- Topbar Navbar -->
                                        <ul class="navbar-nav ml-auto">

                                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                                            <li class="nav-item dropdown no-arrow d-sm-none">
                                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-search fa-fw"></i>
                                                </a>
                                            </li>


                                            <div class="topbar-divider d-none d-sm-block"></div>

                                            <!-- Nav Item - User Information -->
                                            <li class="nav-item dropdown no-arrow">
                                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small text-bold">¡ Bienvenido/a, <%=  usuario.getNombre_usuario()%> !</span>
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 448 512"><path fill="#000000" d="M224 256a128 128 0 1 0 0-256a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512h388.6c16.4 0 29.7-13.3 29.7-29.7c0-98.5-79.8-178.3-178.3-178.3h-91.4z"/></svg>
                                                </a>
                                                <!-- Dropdown - User Information -->
                                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                                     aria-labelledby="userDropdown">
                                                    <div class="dropdown-divider"></div>
                                                    <a class="dropdown-item" href="SvLogin" data-toggle="modal" data-target="#logoutModal">
                                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                                        Salir
                                                    </a>
                                                </div>
                                            </li>

                                        </ul>

                                    </nav>
                                    <!-- End of Topbar -->

                                    <!-- Begin Page Content -->
                                    <div class="container-fluid">