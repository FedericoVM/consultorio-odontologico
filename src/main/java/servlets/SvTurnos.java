package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Turno;

@WebServlet(name = "SvTurnos", urlPatterns = {"/SvTurnos"})
public class SvTurnos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Turno> listaTurnos = new ArrayList<Turno>();
        listaTurnos = control.traerTurnos();

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaTurnos", listaTurnos);

        response.sendRedirect("panelTurnos.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String fecha_turno = request.getParameter("fecha_turno");
        String hora_turno = request.getParameter("hora_turno");
        String fechaHoraCompleta = fecha_turno + "T" + hora_turno;

        LocalDateTime fechaHoraTurno = LocalDateTime.parse(fechaHoraCompleta);

        int idPaciente = Integer.parseInt(request.getParameter("id_paciente"));
        int idOdontologo = Integer.parseInt(request.getParameter("id_odontologo"));
        String motivo = request.getParameter("motivo_consulta");
        String observaciones = request.getParameter("observaciones_finales");

        control.agendarTurno(fechaHoraTurno, idPaciente, idOdontologo, motivo, observaciones);
        
        response.sendRedirect("index.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
