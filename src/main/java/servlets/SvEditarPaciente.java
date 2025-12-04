package servlets;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Paciente;

@WebServlet(name = "SvEditarPaciente", urlPatterns = {"/SvEditarPaciente"})
public class SvEditarPaciente extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idEditarPaciente = Integer.parseInt(request.getParameter("idEditar"));

        Paciente pacienteEditar = control.buscarPaciente(idEditarPaciente);

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("pacienteEditar", pacienteEditar);

        response.sendRedirect("editarPaciente.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // Datos paciente
        int idPaciente = Integer.parseInt( request.getParameter("id_paciente") ) ;
        String nombre = request.getParameter("nombre_paciente");
        String apellido = request.getParameter("apellido_paciente");
        String telefono = request.getParameter("telefono_paciente");
        LocalDate fecha_nac = LocalDate.parse(request.getParameter("fecha_nac_paciente"));
        String dni = request.getParameter("dni_paciente");
        String num_afiliado = request.getParameter("num_afiliado");
        String tipo_sangre = request.getParameter("tipo_sangre");
        
        System.out.println("Nombre paciente: " + nombre);

        // Datos obra social
        String nombre_obraSocial = request.getParameter("nombre_obra_social");
        String telefono_obraSocial = request.getParameter("telefono_obra_social");

        // Datos Responsable
        String nombre_resp = request.getParameter("nombre_tutor");
        String apellido_resp = request.getParameter("apellido_tutor");
        String telefono_resp = request.getParameter("telefono_tutor");
        String dni_resp = request.getParameter("dni_tutor");
        LocalDate fecha_nac_resp = LocalDate.parse(request.getParameter("fecha_nac_tutor")) ;
        String tipo_resp = request.getParameter("relacion_tutor");

        control.editarPaciente( idPaciente, nombre, apellido, telefono, fecha_nac, dni, num_afiliado, tipo_sangre, nombre_obraSocial, telefono_obraSocial, nombre_resp, apellido_resp,
                telefono_resp, dni_resp, fecha_nac_resp, tipo_resp);
        
        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
