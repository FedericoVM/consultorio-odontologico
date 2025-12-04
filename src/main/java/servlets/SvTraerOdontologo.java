package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Odontologo;

@WebServlet(name = "SvTraerOdontologo", urlPatterns = {"/SvTraerOdontologo"})
public class SvTraerOdontologo extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int idOdontologo = Integer.parseInt(request.getParameter("idVerOdontologo"));
        Odontologo odontologoEncontrado = control.verOdontologo(idOdontologo);

        System.out.println("Odontologo encontrado: " + odontologoEncontrado.getNombre());

        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("odontologoEncontrado", odontologoEncontrado);

        response.sendRedirect("verOdontologo.jsp");

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
