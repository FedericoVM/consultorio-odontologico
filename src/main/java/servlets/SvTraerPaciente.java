package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Paciente;

@WebServlet(name = "SvTraerPaciente", urlPatterns = {"/SvTraerPaciente"})
public class SvTraerPaciente extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idVerPaciente = Integer.parseInt(request.getParameter("idVerPaciente"));

        Paciente pacienteEncontrado = control.buscarPaciente(idVerPaciente);

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("pacienteEncontrado", pacienteEncontrado);

        response.sendRedirect("verPaciente.jsp");

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
