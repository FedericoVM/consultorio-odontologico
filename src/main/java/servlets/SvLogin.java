package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession miSesion = request.getSession(false);

        if (miSesion != null) {
            miSesion.invalidate();
        }

        response.sendRedirect("login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String nombre_usuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuarioEncontrado = control.login(nombre_usuario, contrasenia);

        if (usuarioEncontrado != null) {
            HttpSession miSesion = request.getSession(true);
            miSesion.setAttribute("usuarioEncontrado", usuarioEncontrado);
            miSesion.setAttribute("rolUsuario", usuarioEncontrado.getRol());
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("errorLogin", "Usuario o Contraseña incorrectos.");
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
