<%-- Página de donantes --%>
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


<title>Gestión de donantes</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Donantes</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String idDonanteEditar = request.getParameter("editarDonanteID");
		String crearDonante = request.getParameter("crearDonante");
		String idDonanteBorrar = request.getParameter("borrarDonanteID");

		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");

		if (idDonanteEditar == null && crearDonante == null && idDonanteBorrar == null) {
			// Recuperamos los donantes
			ArrayList<Donante> donantes = (ArrayList<Donante>) session.getAttribute("donantes");
		%>

		<h5>
			<strong>Listado de donantes</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Identificador</th>
					<th>&nbsp;</th>
				</tr>
				<%
				// Muestra los donantes
				for (Donante donante : donantes) {
				%>
				<tr>
					<td align="center"><%=donante.getNombreDonante()%></td>
					<td align="center"><%=donante.getTipoDonante()%></td>
					<td align="center"><%=donante.getIdentificadorDonante()%></td>

					<td align="center">
						<!-- Pasamos el id como parametros y recargamos la pagina--> <a
						href="donantes.jsp?editarDonanteID=<%=donante.getIdDonante()%>">
							<input type="button" class="btn btn-info" value="Editar">
					</a> <a href="donantes.jsp?borrarDonanteID=<%=donante.getIdDonante()%>">
							<input type="button" class="btn btn-danger" value="Borrar">
					</a>
					</td>
				</tr>

				<%
				}
				%>
			</table>
			<br> 
			<!-- Pasamos como parametro que queremos crear un Donante para mostrar dicho menu-->
			<a href="donantes.jsp?crearDonante=si"> <input type="button"
				class="btn btn-success" value="Crear Donante">
			</a>
		</div>
		<br>
		<%
		}
		// Comprueba si existe un identificador de Donante definido para editar
		else if (crearDonante != null && crearDonante.equals("si")) {
		%>
		<div class="container text-center" id="container7">
			<form name="crearForm" action="donantes" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearDonante">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos del Donante</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre" /></td>

						<td>Tipo:</br> <input required type="text" name="tipo" /></td>

						<td>Identificador:</br> <input required type="text"
							name="identificador" /></td>

					</tr>
				</table>
				<br>
				<!-- Botón crear Donante-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear"
					value="Crear donante" />
				<!-- Volver al listado-->
				<a href="donantes.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		// Comprueba si existe un identificador de Donante definido para editar
		else if (idDonanteEditar != null) {

		// Recuperamos los donantes
		ArrayList<Donante> donantes = (ArrayList<Donante>) session.getAttribute("donantes");
		Donante donanteseleccionado = null;

		// Recorremos los donantes y nos quedamos con el que tiene el identificador definido para editar
		for (Donante donante : donantes) {
			if (donante.getIdDonante().toString().equals(idDonanteEditar)) {
				donanteseleccionado = donante;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="editarForm" action="donantes" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="editarDonante"> <input align="center" type="hidden"
					name="id"
					value="<%out.print(donanteseleccionado.getIdDonante());%>">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos a modificar</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre"
							value="<%out.println(donanteseleccionado.getNombreDonante());%>" /></td>

						<td>Tipo:</br> <input required type="text" name="tipo"
							value="<%out.println(donanteseleccionado.getTipoDonante());%>" /></td>

						<td>Identificador:</br> <input required type="text"
							name="identificador"
							value="<%out.println(donanteseleccionado.getIdentificadorDonante());%>" /></td>

						</select>
					</tr>
				</table>
				<br>
				<!-- Botón Editar donante-->
				</a> <input type="submit" class="btn btn-success" name="ejecutarEditar"
					value="Editar donante" />
				<!-- Volver al listado-->
				<a href="donantes.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<br>
		<%
		}
		// Comprueba si existe un identificador de Donante definido para borrar
		else if (idDonanteBorrar != null) {

		// Recuperamos los donantes
		ArrayList<Donante> donantes = (ArrayList<Donante>) session.getAttribute("donantes");
		Donante donanteseleccionado = null;

		// Recorremos los donantes y nos quedamos con el que tiene el identificador definido para borrar
		for (Donante donante : donantes) {
			if (donante.getIdDonante().toString().equals(idDonanteBorrar)) {
				donanteseleccionado = donante;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="borrarForm" action="donantes" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="borrarDonante"> <input align="center" type="hidden"
					name="id" value="<%out.print(donanteseleccionado.getIdDonante());%>">
				<table align="center" class="table table-bordered" border="1">
					<tr align="center">
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Identificador</th>
					</tr>
					<tr>
					<td align="center"><%=donanteseleccionado.getNombreDonante()%></td>
					<td align="center"><%=donanteseleccionado.getTipoDonante()%></td>
					<td align="center"><%=donanteseleccionado.getIdentificadorDonante()%></td>

					</tr>
				</table>
				<!-- Volver al listado-->
				<a href="donantes.jsp"> <input type="button"
					class="btn btn-danger" value="Cancelar borrado">
				</a> <input type="submit" class="btn btn-success" name="ejecutarBorrar"
					value="Confirmar borrado" />
			</form>
		<br>
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
			<!-- Salir al menú principal-->
			<a href="gestionBanco.html"><input type="button"
				class="btn btn-primary" value="Salir al menú"></a>
		</div>
</body>
</html>