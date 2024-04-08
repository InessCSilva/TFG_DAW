package es.studium.bancoAlimentos.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.studium.bancoAlimentos.modelo.ConexionBBDD;
import es.studium.bancoAlimentos.modelo.Usuario;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ConexionBBDD modelo = new ConexionBBDD();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<head> <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
					+ "<style type=\"text/css\">\r\n"
					+ "@import url(https://fonts.googleapis.com/css?family=Open+Sans);\r\n"
					+ "html { width: 100%; height:100%; overflow:hidden; }\r\n"
					+ "\r\n"
					+ "body { \r\n"
					+ "	width: 100%;\r\n"
					+ "	height:100%;\r\n"
					+ "	margin-top:150px;\r\n"
					+ "	font-family: 'Open Sans', sans-serif;\r\n"
					+ "	background: #092756;\r\n"
					+ "	background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  "
					+ "rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);\r\n"
					+ "	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), "
					+ "-webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);\r\n"
					+ "	background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), "
					+ "-o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);\r\n"
					+ "	background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), "
					+ "-ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);\r\n"
					+ "	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), "
					+ "linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);\r\n"
					+ "	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "h5, h2 {color:white;\r\n\"}\r\n"
					+ "\r\n"
					+ "</style>");
			out.println("<title>Login</title>");
			out.println("</head>");
			out.println("<br><body class=\"text-center\">");
			out.println("<h2 align=\"center\">Login</h2>");
			// Recuperar los parámetros usuario y password de la petición request
			String nombre = request.getParameter("usuario");
			String password = request.getParameter("password");
			// Validar los parámetros de la petición request
			if (nombre.length() == 0) {
				out.println("<h3 align=\"center\">Debes introducir tu usuario</h3>");
			} else if (password.length() == 0) {
				out.println("<h3 align=\"center\">Debes introducir tu contraseña</h3>");
			} else {
				// Verificar que existe el usuario y su correspondiente clave
				Usuario usuario = modelo.realizarLogin(nombre, password);
				if (usuario == null) {
					// Si el resulset no está vacío
					out.println("<br><br><br><h5 align=\"center\">Nombre de usuario o contraseña incorrectos</h5>");
					out.println("<br><a href='index.html'><input type=\"button\" value=\"Volver a Login\"></a>");
				} else {
					// Si los datos introducidos son correctos
					// Crear una sesión nueva y guardar el usuario como variable de sesión
					// Primero, invalida la sesión si ya existe
					HttpSession session = request.getSession(false);
					if (session != null) {
						session.invalidate();
					}
					session = request.getSession(true);
					synchronized (session) {
						session.setAttribute("usuario", nombre);
					}
					
					String nextPage = "";
					
					if(usuario.isAdmin()) {
						nextPage = "/gestionAdministrador.html";
					}
					
					else {
						nextPage = "/gestionBanco.html";
					}
					
					ServletContext servletContext = getServletContext();
					RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
					requestDispatcher.forward(request, response);
				}
			}
			out.println("</body>");
			out.println("</html>");
		} finally {
			// Cerramos objetos
			out.close();
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}