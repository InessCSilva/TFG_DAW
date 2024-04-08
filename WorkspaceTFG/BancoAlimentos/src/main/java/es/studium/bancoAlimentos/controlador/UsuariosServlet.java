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
import es.studium.bancoAlimentos.modelo.Usuario;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {
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
		
		// Si operacion tiene el valor crearUsuario, creamos el usuario definido en BBDD		
		if (operacion != null && operacion.equals("crearUsuario")) {
			// Creamos el usuario seleccionado en BBDD
			String nombreUsuario = request.getParameter("nombre");
			String claveUsuario = request.getParameter("contraseña");
			Boolean tipoUsuario = false;
			if (request.getParameter("administrador").equals("true")){
				tipoUsuario = true;
			}

			// Creamos el usuario
			ConexionBBDD.altaUsuario(nombreUsuario, claveUsuario, tipoUsuario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor editarUsuario, editamos el usuario definido en BBDD		
		else if (operacion != null && operacion.equals("editarUsuario")) {
			// Editamos el usuario seleccionado en BBDD
			String idUsuario = request.getParameter("id");
			String nombreUsuario = request.getParameter("nombre");
			String claveUsuario = request.getParameter("contraseña");
			Boolean tipoUsuario = false;
			if (request.getParameter("administrador").equals("true")){
				tipoUsuario = true;
			}

			// Modificamos el usuario
			ConexionBBDD.editarUsuario(idUsuario, nombreUsuario, claveUsuario, tipoUsuario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);						
		}
		
		// Si operacion tiene el valor borrarUsuario, editamos el usuario definido en BBDD		
		else if (operacion != null && operacion.equals("borrarUsuario")) {
			// Editamos el usuario seleccionado en BBDD
			String idUsuario = request.getParameter("id");
			// Modificamos el usuario
			ConexionBBDD.eliminarUsuario(idUsuario);
			// Guardamos en sesión que se ha realizado la operación correctamente
			session.setAttribute("operacionOK", true);					
		}
		
		// Generamos el array de usuarios para añadirlo en session
		ArrayList<Usuario> usuarios = ConexionBBDD.obtenerUsuarios();
		
		// Guardamos el array de usuarios en sesión
		session.setAttribute("usuarios", usuarios);	
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/usuarios.jsp");
		requestDispatcher.forward(request, response);
	}
}
