<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/head.jsp" %>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="row justify-content-center h-100">

            <div class="col-xl-10 col-lg-12 col-md-9 h-100">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="row">

                            <div class="col-lg-6 d-none d-lg-block bg-login-image" 
                                 style="background-image: url('https://images.unsplash.com/photo-1662837625427-970713d74aa6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGNsJUMzJUFEbmljYSUyMGRlbnRhbHxlbnwwfDF8MHx8fDA%3D'); background-size: cover; background-position: center;">
                            </div>

                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Clínica Odontológica</h1>
                                    </div>
                                    <form class="user" action="SvLogin" method="POST">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                   id="nombreUsuario" name="nombreUsuario" aria-describedby="emailHelp"
                                                   placeholder="Nombre de usuario">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="contrasenia" class="form-control form-control-user"
                                                   id="contrasenia" placeholder="Contraseña">
                                        </div>

                                        <hr>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            Ingresar
                                        </button>
                                    </form>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <script src="js/sb-admin-2.min.js"></script>

</body>