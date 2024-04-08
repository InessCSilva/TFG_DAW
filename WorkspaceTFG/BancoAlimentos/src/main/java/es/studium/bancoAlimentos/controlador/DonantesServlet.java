package es.studium.bancoAlimentos.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.studium.bancoAlimentos.modelo.ConexionBBDD;
import es.studium.bancoAlimentos.modelo.Donante;

@WebServlet("/donantes")
public class DonantesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Recupera la sesión actual o crea una nueva si no existe
		HttpSession session = request.getSession(true);
		
		// Determinamos que form ha sido enviado
		String operacion = request.getParameter("operacion");
		
		// Si operacion tiene el valor crearDonante, creamos el donante definido en BBDD		
		if (operacion != null && operacion.equals("crearDonante")) {
			// Creamos el donante seleccionado en BBDD
			String nombreDonante = request.getParameter("nombre");
			String tipoDonante = request.getParameter("tipo");
			String identificadorDonante = request.getParameter("identificador");

			// Creamos el donante
			ConexionBBDD.altaDonante(nombreDonante, tipoDonante, identificadorDonante);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor editarDonante, editamos el donante definido en BBDD		
		else if (operacion != null && operacion.equals("editarDonante")) {
			// Editamos el donante seleccionado en BBDD
			String idDonante = request.getParameter("id");
			String nombreDonante = request.getParameter("nombre");
			String tipoDonante = request.getParameter("tipo");
			String identificadorDonante = request.getParameter("identificador");

			// Modificamos el donante
			ConexionBBDD.editarDonante(idDonante, nombreDonante, tipoDonante, identificadorDonante);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);					
		}
		
		// Si operacion tiene el valor borrarDonante, editamos el donante definido en BBDD		
		else if (operacion != null && operacion.equals("borrarDonante")) {
			// Editamos el donante seleccionado en BBDD
			String idDonante = request.getParameter("id");
			// Modificamos el donante
			ConexionBBDD.eliminarDonante(idDonante);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);					
		}
		
		// Generamos el array de donantes para añadirlo en session
		ArrayList<Donante> donantes = ConexionBBDD.obtenerDonantes();
		
		// Guardamos el array de donantes en sesión
		session.setAttribute("donantes", donantes);	
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/donantes.jsp");
		requestDispatcher.forward(request, response);
	}
}
