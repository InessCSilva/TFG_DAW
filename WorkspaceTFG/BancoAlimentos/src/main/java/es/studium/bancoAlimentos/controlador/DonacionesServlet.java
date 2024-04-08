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
import es.studium.bancoAlimentos.modelo.DonacionDonanteAlimento;
import es.studium.bancoAlimentos.modelo.Donante;

@WebServlet("/donaciones")
public class DonacionesServlet extends HttpServlet {
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
		
		// Si operacion tiene el valor crearDonacion, creamos la donación definida en BBDD		
		if (operacion != null && operacion.equals("crearDonacion")) {
			// Creamosla donancion seleccionada en BBDD
			String cantidadAlimentoDonacion = request.getParameter("cantidad");
			String idAlimentoFK = request.getParameter("idAlimento");
			String idDonanteFK = request.getParameter("idDonante");

			// Creamos la donación
			ConexionBBDD.crearDonacion(cantidadAlimentoDonacion, idAlimentoFK, idDonanteFK);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}

		// Generamos el array de alimentos en el almacen para añadirlo en session
		ArrayList<DonacionDonanteAlimento> donaciones = ConexionBBDD.obtenerDonaciones();
		
		// Generamos el array de alimentos añadirlo en session
		ArrayList<Alimento> alimentos = ConexionBBDD.obtenerAlimentos();
		
		// Generamos el array de donantes para añadirlo en session
		ArrayList<Donante> donantes = ConexionBBDD.obtenerDonantes();

		// Guardamos los arrays en session
		session.setAttribute("donaciones", donaciones);
		session.setAttribute("alimentos", alimentos);
		session.setAttribute("donantes", donantes);
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/donaciones.jsp");
		requestDispatcher.forward(request, response);
	}
}
