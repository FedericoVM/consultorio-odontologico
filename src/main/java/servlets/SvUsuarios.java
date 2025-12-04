package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = control.traerUsuarios();

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaUsuarios", listaUsuarios);

        response.sendRedirect("usuarios.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String nombre_usuario = request.getParameter("nombre_usuario");
        String contrasenia = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");

        control.crearUsuario(nombre_usuario, contrasenia, rol);

        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
