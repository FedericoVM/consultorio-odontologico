package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Odontologo;

@WebServlet(name = "SvEditarOdontologo", urlPatterns = {"/SvEditarOdontologo"})
public class SvEditarOdontologo extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idOdontologoEditar = Integer.parseInt(request.getParameter("idEditar"));
        Odontologo odontologoEncontradoEditar = control.verOdontologo(idOdontologoEditar);

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("odontologoEncontradoEditar", odontologoEncontradoEditar);

        response.sendRedirect("editarOdontologo.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idEditar = Integer.parseInt(request.getParameter("idEditar"));
        String nombre = request.getParameter("nombre-odontologo");
        String apellido = request.getParameter("apellido-odontologo");
        String telefono = request.getParameter("telefono");
        String dni = request.getParameter("dni-odontologo");
        LocalDate fecha_nac_odontologo = LocalDate.parse(request.getParameter("fecha_nac-odontologo"));
        String especialidad = request.getParameter("especialidad");
        String matricula = request.getParameter("matricula");
        String email = request.getParameter("email");

        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");

        LocalTime lunes_inicio = LocalTime.parse(request.getParameter("lunes_inicio"));
        LocalTime lunes_fin = LocalTime.parse(request.getParameter("lunes_fin"));

        LocalTime martes_inicio = LocalTime.parse(request.getParameter("martes_inicio"));
        LocalTime martes_fin = LocalTime.parse(request.getParameter("martes_fin"));

        LocalTime miercoles_inicio = LocalTime.parse(request.getParameter("miercoles_inicio"));
        LocalTime miercoles_fin = LocalTime.parse(request.getParameter("miercoles_fin"));

        LocalTime jueves_inicio = LocalTime.parse(request.getParameter("jueves_inicio"));
        LocalTime jueves_fin = LocalTime.parse(request.getParameter("jueves_fin"));

        LocalTime viernes_inicio = LocalTime.parse(request.getParameter("viernes_inicio"));
        LocalTime viernes_fin = LocalTime.parse(request.getParameter("viernes_fin"));

        control.editarOdontologo(idEditar, nombre, apellido, telefono, dni, fecha_nac_odontologo, especialidad, matricula, email, usuario, contrasenia, lunes_inicio, lunes_fin, martes_inicio, martes_fin, miercoles_inicio, miercoles_fin, jueves_inicio, jueves_fin, viernes_inicio, viernes_fin);

        response.sendRedirect("SvOdontologos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
