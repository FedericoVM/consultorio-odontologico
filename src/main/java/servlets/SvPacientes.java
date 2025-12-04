package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Paciente;

@WebServlet(name = "SvPacientes", urlPatterns = {"/SvPacientes"})
public class SvPacientes extends HttpServlet {

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

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaPacientes", listaPacientes);

        response.sendRedirect("pacientes.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        LocalDate fecha_nac_tutor = null;

        String nombre_paciente = request.getParameter("nombre_paciente");
        String apellido_paciente = request.getParameter("apellido_paciente");
        String telefono_paciente = request.getParameter("telefono_paciente");
        LocalDate fecha_nac_paciente = LocalDate.parse(request.getParameter("fecha_nac_paciente"));
        String dni_paciente = request.getParameter("dni_paciente");
        String nombre_obra_social = request.getParameter("nombre_obra_social");
        String num_afiliado = request.getParameter("num_afiliado");
        String telefono_obra_social = request.getParameter("telefono_obra_social");
        String tipo_sangre = request.getParameter("tipo_sangre");

        String nombre_tutor = request.getParameter("nombre_tutor");
        String apellido_tutor = request.getParameter("apellido_tutor");
        String dni_tutor = request.getParameter("dni_tutor");
        String fechaTutorStr = request.getParameter("fecha_nac_tutor");
        System.out.println(" fecha_nac_tutor " + fecha_nac_tutor);
        String telefono_tutor = request.getParameter("telefono_tutor");
        String relacion_tutor = request.getParameter("relacion_tutor");

        if (nombre_tutor != null && nombre_tutor.isEmpty()) {
            nombre_tutor = null;
        }
        if (apellido_tutor != null && apellido_tutor.isEmpty()) {
            apellido_tutor = null;
        }
        if (dni_tutor != null && dni_tutor.isEmpty()) {
            dni_tutor = null;
        }
        if (telefono_tutor != null && telefono_tutor.isEmpty()) {
            telefono_tutor = null;
        }
        if (relacion_tutor != null && relacion_tutor.isEmpty()) {
            relacion_tutor = null;
        }

        if (fechaTutorStr != null && !fechaTutorStr.isEmpty()) {
            fecha_nac_tutor = LocalDate.parse(request.getParameter("fecha_nac_tutor"));
        }

        control.crearPaciente(nombre_paciente, apellido_paciente, telefono_paciente, fecha_nac_paciente, dni_paciente, nombre_obra_social, num_afiliado, telefono_obra_social,
                tipo_sangre, nombre_tutor, apellido_tutor, dni_tutor, fecha_nac_tutor, telefono_tutor, relacion_tutor);

        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
