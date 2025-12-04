package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.FranjaHoraria;
import logica.Odontologo;

@WebServlet(name = "SvOdontologos", urlPatterns = {"/SvOdontologos"})
public class SvOdontologos extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Odontologo> listaOdontologos = new ArrayList<Odontologo>();
        listaOdontologos = control.traerOdontologos();

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaOdontologos", listaOdontologos);
        response.sendRedirect("odontologos.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

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

        List<FranjaHoraria> listaHorarios = new ArrayList<>();

        FranjaHoraria horaLunes = new FranjaHoraria();
        horaLunes.setDia_semana("Lunes");
        horaLunes.setHora_inicio(LocalTime.parse(request.getParameter("lunes_inicio")));
        horaLunes.setHora_fin(LocalTime.parse(request.getParameter("lunes_fin")));
        listaHorarios.add(horaLunes);

        FranjaHoraria horaMartes = new FranjaHoraria();
        horaMartes.setDia_semana("Martes");
        horaMartes.setHora_inicio(LocalTime.parse(request.getParameter("martes_inicio")));
        horaMartes.setHora_fin(LocalTime.parse(request.getParameter("martes_fin")));
        listaHorarios.add(horaMartes);

        FranjaHoraria horaMiercoles = new FranjaHoraria();
        horaMiercoles.setDia_semana("Miercoles");
        horaMiercoles.setHora_inicio(LocalTime.parse(request.getParameter("miercoles_inicio")));
        horaMiercoles.setHora_fin(LocalTime.parse(request.getParameter("miercoles_fin")));
        listaHorarios.add(horaMiercoles);

        FranjaHoraria horaJueves = new FranjaHoraria();
        horaJueves.setDia_semana("Jueves");
        horaJueves.setHora_inicio(LocalTime.parse(request.getParameter("jueves_inicio")));
        horaJueves.setHora_fin(LocalTime.parse(request.getParameter("jueves_fin")));
        listaHorarios.add(horaJueves);

        FranjaHoraria horaViernes = new FranjaHoraria();
        horaViernes.setDia_semana("Viernes");
        horaViernes.setHora_inicio(LocalTime.parse(request.getParameter("viernes_inicio")));
        horaViernes.setHora_fin(LocalTime.parse(request.getParameter("viernes_fin")));
        listaHorarios.add(horaViernes);

        control.altaOdontologo(nombre, apellido, telefono, dni, fecha_nac_odontologo, especialidad, matricula, email, usuario, contrasenia, listaHorarios);

        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
