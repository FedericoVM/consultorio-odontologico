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

@WebServlet(name = "SvEditarUsuario", urlPatterns = {"/SvEditarUsuario"})
public class SvEditarUsuario extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idUsuarioEditar = Integer.parseInt(request.getParameter("idEditar"));
        Usuario usuarioEditar = control.traerUsuario(idUsuarioEditar);

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("usuarioEditar", usuarioEditar);

        response.sendRedirect("editarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        String nombre_usuario = request.getParameter("nombre_usuario");
        String contrasenia_usuario = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");

        control.editarUsuario(id_usuario, nombre_usuario, contrasenia_usuario, rol);

        response.sendRedirect("usuarios.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
