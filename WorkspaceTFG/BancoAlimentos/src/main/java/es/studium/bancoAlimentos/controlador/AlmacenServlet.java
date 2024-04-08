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

import es.studium.bancoAlimentos.modelo.AlimentoAlmacen;
import es.studium.bancoAlimentos.modelo.ConexionBBDD;

@WebServlet("/almacen")
public class AlmacenServlet extends HttpServlet {
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

		// Generamos el array de alimentos en el almacen para añadirlo en session
		ArrayList<AlimentoAlmacen> alimentosAlmacen = ConexionBBDD.obtenerAlimentosAlmacen();

		// Guardamos el array de alimentos en sesión
		session.setAttribute("alimentosAlmacen", alimentosAlmacen);

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/almacen.jsp");
		requestDispatcher.forward(request, response);
	}
}
