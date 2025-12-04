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
import logica.Odontologo;
import logica.Paciente;

@WebServlet(name = "SvSolicitarTurno", urlPatterns = {"/SvSolicitarTurno"})
public class SvSolicitarTurno extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Paciente> listaPacientes = new ArrayList<Paciente>();
        listaPacientes = control.traerPacientes();

        List<Odontologo> listaOdontologos = new ArrayList<Odontologo>();
        listaOdontologos = control.traerOdontologos();

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaPacientes", listaPacientes);
        miSesion.setAttribute("listaOdontologos", listaOdontologos);

        response.sendRedirect("turno.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
