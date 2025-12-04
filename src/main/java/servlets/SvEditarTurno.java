package servlets;

import java.io.IOException;
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
import logica.Turno;

@WebServlet(name = "SvEditarTurno", urlPatterns = {"/SvEditarTurno"})
public class SvEditarTurno extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idTurno = Integer.parseInt(request.getParameter("idEditar"));
        List<Paciente> listaPacientes = control.traerPacientes();
        List<Odontologo> listaOdontologos = control.traerOdontologos();

        Turno turno = control.traerTurno(idTurno);

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("turno", turno);
        miSesion.setAttribute("listaPacientes", listaPacientes);
        miSesion.setAttribute("listaOdontologos", listaOdontologos);

        response.sendRedirect("editarTurno.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idTurno = Integer.parseInt(request.getParameter("id_turno"));
        String fecha = request.getParameter("fecha_turno");
        String hora = request.getParameter("hora_turno");
        int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
        int idOdontologo = Integer.parseInt(request.getParameter("id_odontologo"));
        String motivo = request.getParameter("motivo_consulta");
        String observacion = request.getParameter("observaciones_finales");

        control.editarTurno(idTurno, fecha, hora, idPaciente, idOdontologo, motivo, observacion);

        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
