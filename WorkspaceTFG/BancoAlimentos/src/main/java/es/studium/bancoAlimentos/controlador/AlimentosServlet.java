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
import es.studium.bancoAlimentos.modelo.Alimento;
import es.studium.bancoAlimentos.modelo.ConexionBBDD;

@WebServlet("/alimentos")
public class AlimentosServlet extends HttpServlet {
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
		
		// Si operacion tiene el valor crearAlimento, creamos el alimento definido en BBDD		
		if (operacion != null && operacion.equals("crearAlimento")) {
			// Creamos el alimento seleccionado en BBDD
			String nombreAlimento = request.getParameter("nombre");
			String tipoAlimento = request.getParameter("tipo");
			String marcaAlimento = request.getParameter("marca");
			String cantidadAlimento = request.getParameter("cantidad");
			Boolean caducidadAlimento = false;
			if (request.getParameter("caducidad").equals("true")){
				caducidadAlimento = true;
			}

			// Creamos el alimento
			ConexionBBDD.altaAlimento(nombreAlimento, tipoAlimento, marcaAlimento, cantidadAlimento, caducidadAlimento);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor editarAlimento, editamos el alimento definido en BBDD		
		else if (operacion != null && operacion.equals("editarAlimento")) {
			// Editamos el alimento seleccionado en BBDD
			String idAlimento = request.getParameter("id");
			String nombreAlimento = request.getParameter("nombre");
			String tipoAlimento = request.getParameter("tipo");
			String marcaAlimento = request.getParameter("marca");
			String cantidadAlimento = request.getParameter("cantidad");
			Boolean caducidadAlimento = false;
			if (request.getParameter("caducidad").equals("true")){
				caducidadAlimento = true;
			}

			// Modificamos el alimento
			ConexionBBDD.editarAlimento(idAlimento, nombreAlimento, tipoAlimento, marcaAlimento, cantidadAlimento, caducidadAlimento);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor borrarAlimento, editamos el alimento definido en BBDD		
		else if (operacion != null && operacion.equals("borrarAlimento")) {
			// Editamos el alimento seleccionado en BBDD
			String idAlimento = request.getParameter("id");
			// Eliminamos el alimento
			ConexionBBDD.eliminarAlimento(idAlimento);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Generamos el array de alimentos para añadirlo en session
		ArrayList<Alimento> alimentos = ConexionBBDD.obtenerAlimentos();
		
		// Guardamos el array de alimentos en sesión
		session.setAttribute("alimentos", alimentos);	
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/alimentos.jsp");
		requestDispatcher.forward(request, response);
	}
}
