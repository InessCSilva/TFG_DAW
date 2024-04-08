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
import es.studium.bancoAlimentos.modelo.Beneficiario;

@WebServlet("/beneficiarios")
public class BeneficiariosServlet extends HttpServlet {
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
		
		// Si operacion tiene el valor crearBeneficiario, creamos el beneficiario definido en BBDD		
		if (operacion != null && operacion.equals("crearBeneficiario")) {
			// Creamos el beneficiario seleccionado en BBDD
			String nombreBeneficiario = request.getParameter("nombre");
			String apellidosBeneficiario = request.getParameter("apellidos");
			String dniBeneficiario = request.getParameter("dni");

			// Creamos el beneficiario
			ConexionBBDD.altaBeneficiario(nombreBeneficiario, apellidosBeneficiario, dniBeneficiario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);							
		}
		
		// Si operacion tiene el valor editarBeneficiario, editamos el beneficiario definido en BBDD		
		else if (operacion != null && operacion.equals("editarBeneficiario")) {
			// Editamos el beneficiario seleccionado en BBDD
			String idBeneficiario = request.getParameter("id");
			String nombreBeneficiario = request.getParameter("nombre");
			String apellidosBeneficiario = request.getParameter("apellidos");
			String dniBeneficiario = request.getParameter("dni");

			// Modificamos el beneficiario
			ConexionBBDD.editarBeneficiario(idBeneficiario, nombreBeneficiario, apellidosBeneficiario, dniBeneficiario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor borrarBeneficiario, editamos el beneficiario definido en BBDD		
		else if (operacion != null && operacion.equals("borrarBeneficiario")) {
			// Editamos el beneficiario seleccionado en BBDD
			String idBeneficiario = request.getParameter("id");
			// Modificamos el beneficiario
			ConexionBBDD.eliminarBeneficiario(idBeneficiario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Generamos el array de beneficiarios para añadirlo en session
		ArrayList<Beneficiario> beneficiarios = ConexionBBDD.obtenerBeneficiarios();
		
		// Guardamos el array de beneficiarios en sesión
		session.setAttribute("beneficiarios", beneficiarios);	
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/beneficiarios.jsp");
		requestDispatcher.forward(request, response);	
	}
}
