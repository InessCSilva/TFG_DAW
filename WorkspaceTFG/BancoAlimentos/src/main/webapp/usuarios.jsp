<%-- Página de usuarios --%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"
	import="java.util.*, es.studium.bancoAlimentos.modelo.*, es.studium.bancoAlimentos.controlador.* "%>
<!DOCTYPE html>
<html lang=”es”>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>


<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

table {
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 17px;
	width: 480px;
	border-collapse: collapse;
}

th {
	font-size: 20px;
	font-weight: normal;
	padding: 8px;
	background: #b9c9fe;
	border-top: 4px solid #aabcfe;
	border-bottom: 1px solid #fff;
	color: #039;
}

td {
	padding: 8px;
	background: #e8edff;
	border-bottom: 1px solid #fff;
	color: #669;
	border-top: 1px solid transparent;
}

tr:hover td {
	background: #d0dafd;
	color: #339;
}
</style>


<title>Gestión de usuarios</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Usuarios</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String idUsuarioEditar = request.getParameter("editarUsuarioID");
		String crearUsuario = request.getParameter("crearUsuario");
		String idUsuarioBorrar = request.getParameter("borrarUsuarioID");

		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");

		if (idUsuarioEditar == null && crearUsuario == null && idUsuarioBorrar == null) {
			// Recuperamos los usuarios
			ArrayList<Usuario> usuarios = (ArrayList<Usuario>) session.getAttribute("usuarios");
		%>

		<h5>
			<strong>Listado de usuarios</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Nombre</th>
					<th>Tipo</th>
					<th>&nbsp;</th>
				</tr>
				<%
				// Muestra los usuarios
				for (Usuario usuario : usuarios) {
				%>
				<tr>
					<td align="center"><%=usuario.getNombre()%></td>
					<%
					// Comprobamos si el usuario es administrador						
					if (usuario.isAdmin()) {
					%>
					<td align="center">Administrador</td>
					<%
					} else {
					%>
					<td align="center">Usuario</td>
					<%
					}
					%>

					<td align="center">
						<!-- Pasamos el id como parametros y recargamos la pagina--> <a
						href="usuarios.jsp?editarUsuarioID=<%=usuario.getId()%>"> <input
							type="button" class="btn btn-info" value="Editar">
					</a> <a href="usuarios.jsp?borrarUsuarioID=<%=usuario.getId()%>"> <input
							type="button" class="btn btn-danger" value="Borrar">
					</a>
					</td>
				</tr>

				<%
				}
				%>
			</table>
			<br> <br>
			<!-- Pasamos como parametro que queremos crear un Usuario para mostrar dicho menu-->
			<a href="usuarios.jsp?crearUsuario=si"> <input type="button"
				class="btn btn-success" value="Crear Usuario">
			</a>
		</div>
		<br>
		<%
		}
		
		// Comprueba si existe un identificador de Usuario definido para crear
		else if (crearUsuario != null && crearUsuario.equals("si")) {
		%>
		<div class="container text-center" id="container7">
			<form name="crearForm" action="usuarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearUsuario">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos del Usuario</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre" /></td>

						<td>Contraseña:</br> <input required type="text" name="contraseña" /></td>

						<td>Administrador:</br> <select name="administrador">
								<option selected value="false">No</option>
								<option value="true">Si</option>
						</select>
					</tr>
				</table>
				<br>
				<!-- Botón crear Usuario-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear"
					value="Crear usuario" />
				<!-- Volver al listado-->
				<a href="usuarios.jsp"><input type="button"
					class="btn btn-danger" value="Volver al listado">
				</a> 	
			</form>
		</div>
		<%
		}
		
		// Comprueba si existe un identificador de Usuario definido para editar
		else if (idUsuarioEditar != null) {

		// Recuperamos los usuarios
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) session.getAttribute("usuarios");
		Usuario usuarioseleccionado = null;

		// Recorremos los usuarios y nos quedamos con el que tiene el identificador definido para editar
		for (Usuario usuario : usuarios) {
			if (usuario.getId().toString().equals(idUsuarioEditar)) {
				usuarioseleccionado = usuario;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="editarForm" action="usuarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="editarUsuario"> <input align="center" type="hidden"
					name="id" value="<%out.print(usuarioseleccionado.getId());%>">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos a modificar</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre"
							value="<%out.println(usuarioseleccionado.getNombre());%>" /></td>

						<td>Contraseña:</br> <input required type="text" name="contraseña" /></td>

						<td>Administrador:</br> <select name="administrador">
								<%
								// Comprobamos la caducidad del Usuario						
								if (usuarioseleccionado.isAdmin()) {
									out.println("<option selected value='" + true + "'>Si</option>");
									out.println("<option value='" + false + "'>No</option>");
								} else {
									out.println("<option selected value='" + false + "'>No</option>");
									out.println("<option value='" + true + "'>Si</option>");
								}
								%>
						</select>
					</tr>
				</table>
				<br> <br>
				<!-- Botón Editar usuario-->
				</a> <input type="submit" class="btn btn-success" name="ejecutarEditar"
					value="Editar usuario" />
				<!-- Volver al listado-->
				<a href="usuarios.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		
		// Comprueba si existe un identificador de Usuario definido para borrar
		else if (idUsuarioBorrar != null) {

		// Recuperamos los usuarios
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) session.getAttribute("usuarios");
		Usuario usuarioseleccionado = null;

		// Recorremos los usuarios y nos quedamos con el que tiene el identificador definido para borrar
		for (Usuario usuario : usuarios) {
			if (usuario.getId().toString().equals(idUsuarioBorrar)) {
				usuarioseleccionado = usuario;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="borrarForm" action="usuarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="borrarUsuario"> <input align="center" type="hidden"
					name="id" value="<%out.print(usuarioseleccionado.getId());%>">
				<table align="center" class="table table-bordered" border="1">
					<tr align="center">
						<th>Nombre</th>
						<th>Administrador</th>
					</tr>
					<tr>
						<td align="center"><%=usuarioseleccionado.getNombre()%></td>

						<%
						// Comprobamos la caducidad del usuario						
						if (usuarioseleccionado.isAdmin()) {
						%>
						<td align="center">Si</td>
						<%
						} else {
						%>
						<td align="center">No</td>
						<%
						}
						%>
					</tr>
				</table>
				<!-- Volver al listado-->
				<a href="usuarios.jsp"> <input type="button"
					class="btn btn-danger" value="Cancelar borrado">
				</a> <input type="submit" class="btn btn-success" name="ejecutarBorrar"
					value="Confirmar borrado" />
			</form>

			<%
			}
			if (operacionOK != null && operacionOK) {
			%>
			<div class="alert alert-success">
				<h2>Operación realizada correctamente</h2>
			</div>
			<%
			session.removeAttribute("operacionOK");
			}
			%>
			<br>
			<!-- Salir al menú principal-->
			<a href="gestionAdministrador.html"><input type="button"
				class="btn btn-primary" value="Salir al menú"></a>
		</div>
	</div>
</body>
</html>