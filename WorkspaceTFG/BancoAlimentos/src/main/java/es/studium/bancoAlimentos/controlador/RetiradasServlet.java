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
import es.studium.bancoAlimentos.modelo.Beneficiario;
import es.studium.bancoAlimentos.modelo.ConexionBBDD;
import es.studium.bancoAlimentos.modelo.RetiradaBeneficiarioAlimento;

@WebServlet("/retiradas")
public class RetiradasServlet extends HttpServlet {
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
				
		// Si operacion tiene el valor crearRetirada, creamos la retirada definida en BBDD		
		if (operacion != null && operacion.equals("crearRetirada")) {
			// Creamos la donancion seleccionada en BBDD
			String cantidadAlimentoRetirada = request.getParameter("cantidad");
			
			// Como el campo recuperado tiene varios valores, hemos de hacer split al string (Los valores estan separados por -)
			// el primer valor antes de - es el idAlmacen
			String arrayParametrosAlmacen = request.getParameter("arrayParametrosAlmacen");
			String[] arrayParametros = arrayParametrosAlmacen.split("-");
			String idAlmacen = arrayParametros[0];
			String idAlimentoFK = arrayParametros[2];
			String idBeneficiarioFK = request.getParameter("idBeneficiarioFK");

			// Creamos la donación
			ConexionBBDD.crearRetirada(cantidadAlimentoRetirada, idAlmacen, idBeneficiarioFK, idAlimentoFK);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Generamos el array de la retirada para añadirlo en session
		ArrayList<RetiradaBeneficiarioAlimento> retiradas = ConexionBBDD.obtenerRetiradas();
		
		// Generamos el array de alimentos en el almacen para añadirlo en session
		ArrayList<AlimentoAlmacen> alimentosAlmacen = ConexionBBDD.obtenerAlimentosAlmacen();
		
		// Generamos el array de beneficiarios en el almacen para añadirlo en session
		ArrayList<Beneficiario> beneficiarios = ConexionBBDD.obtenerBeneficiarios();

		// Guardamos el array de retiradas en sesión
		session.setAttribute("retiradas", retiradas);
		session.setAttribute("alimentosAlmacen", alimentosAlmacen);
		session.setAttribute("beneficiarios", beneficiarios);		
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/retiradas.jsp");
		requestDispatcher.forward(request, response);
	}
}
