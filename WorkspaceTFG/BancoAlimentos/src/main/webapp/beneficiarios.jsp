<%-- Página de beneficiarios --%>
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


<title>Gestión de beneficiarios</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Beneficiarios</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String idBeneficiarioEditar = request.getParameter("editarBeneficiarioID");
		String crearBeneficiario = request.getParameter("crearBeneficiario");
		String idBeneficiarioBorrar = request.getParameter("borrarBeneficiarioID");

		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");

		if (idBeneficiarioEditar == null && crearBeneficiario == null && idBeneficiarioBorrar == null) {
			// Recuperamos los beneficiarios
			ArrayList<Beneficiario> beneficiarios = (ArrayList<Beneficiario>) session.getAttribute("beneficiarios");
		%>

		<h5>
			<strong>Listado de beneficiarios</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
					<th>&nbsp;</th>
				</tr>
				<%
				// Muestra los beneficiarios
				for (Beneficiario beneficiario : beneficiarios) {
				%>
				<tr>
					<td align="center"><%=beneficiario.getNombreBeneficiario()%></td>
					<td align="center"><%=beneficiario.getApellidosBeneficiario()%></td>
					<td align="center"><%=beneficiario.getDniBeneficiario()%></td>

					<td align="center">
						<!-- Pasamos el id como parametros y recargamos la pagina--> <a
						href="beneficiarios.jsp?editarBeneficiarioID=<%=beneficiario.getIdBeneficiario()%>">
							<input type="button" class="btn btn-info" value="Editar">
					</a> <a href="beneficiarios.jsp?borrarBeneficiarioID=<%=beneficiario.getIdBeneficiario()%>">
							<input type="button" class="btn btn-danger" value="Borrar">
					</a>
					</td>
				</tr>

				<%
				}
				%>
			</table>
			<br>
			<!-- Pasamos como parametro que queremos crear un Beneficiario para mostrar dicho menu-->
			<a href="beneficiarios.jsp?crearBeneficiario=si"> <input type="button"
				class="btn btn-success" value="Crear Beneficiario">
			</a>
		</div>
		<br>
		<%
		}
		// Comprueba si existe un identificador de Beneficiario definido para editar
		else if (crearBeneficiario != null && crearBeneficiario.equals("si")) {
		%>
		<div class="container text-center" id="container7">
			<form name="crearForm" action="beneficiarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearBeneficiario">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos del Beneficiario</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre" /></td>

						<td>Apellidos:</br> <input required type="text" name="apellidos" /></td>

						<td>DNI:</br> <input required type="text"
							name="dni" /></td>

					</tr>
				</table>
				<br>
				<!-- Botón crear Beneficiario-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear"
					value="Crear beneficiario" />
				<!-- Volver al listado-->
				<a href="beneficiarios.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		// Comprueba si existe un identificador de Beneficiario definido para editar
		else if (idBeneficiarioEditar != null) {

		// Recuperamos los beneficiarios
		ArrayList<Beneficiario> beneficiarios = (ArrayList<Beneficiario>) session.getAttribute("beneficiarios");
		Beneficiario beneficiarioseleccionado = null;

		// Recorremos los beneficiarios y nos quedamos con el que tiene el identificador definido para editar
		for (Beneficiario beneficiario : beneficiarios) {
			if (beneficiario.getIdBeneficiario().toString().equals(idBeneficiarioEditar)) {
				beneficiarioseleccionado = beneficiario;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="editarForm" action="beneficiarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="editarBeneficiario"> <input align="center" type="hidden"
					name="id"
					value="<%out.print(beneficiarioseleccionado.getIdBeneficiario());%>">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos a modificar</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre"
							value="<%out.println(beneficiarioseleccionado.getNombreBeneficiario());%>" /></td>

						<td>Apellidos:</br> <input required type="text" name="apellidos"
							value="<%out.println(beneficiarioseleccionado.getApellidosBeneficiario());%>" /></td>

						<td>DNI:</br> <input required type="text"
							name="dni"
							value="<%out.println(beneficiarioseleccionado.getDniBeneficiario());%>" /></td>

						</select>
					</tr>
				</table>
				<br> <br>
				<!-- Botón Editar beneficiario-->
				</a> <input type="submit" class="btn btn-success" name="ejecutarEditar"
					value="Editar beneficiario" />
				<!-- Volver al listado-->
				<a href="beneficiarios.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		// Comprueba si existe un identificador de Beneficiario definido para borrar
		else if (idBeneficiarioBorrar != null) {

		// Recuperamos los beneficiarios
		ArrayList<Beneficiario> beneficiarios = (ArrayList<Beneficiario>) session.getAttribute("beneficiarios");
		Beneficiario beneficiarioseleccionado = null;

		// Recorremos los beneficiarios y nos quedamos con el que tiene el identificador definido para borrar
		for (Beneficiario beneficiario : beneficiarios) {
			if (beneficiario.getIdBeneficiario().toString().equals(idBeneficiarioBorrar)) {
				beneficiarioseleccionado = beneficiario;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="borrarForm" action="beneficiarios" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="borrarBeneficiario"> <input align="center" type="hidden"
					name="id" value="<%out.print(beneficiarioseleccionado.getIdBeneficiario());%>">
				<table align="center" class="table table-bordered" border="1">
					<tr align="center">
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>DNI</th>
					</tr>
					<tr>
					<td align="center"><%=beneficiarioseleccionado.getNombreBeneficiario()%></td>
					<td align="center"><%=beneficiarioseleccionado.getApellidosBeneficiario()%></td>
					<td align="center"><%=beneficiarioseleccionado.getDniBeneficiario()%></td>

					</tr>
				</table>
				<!-- Volver al listado-->
				<a href="beneficiarios.jsp"> <input type="button"
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
			<a href="gestionBanco.html"><input type="button"
				class="btn btn-primary" value="Salir al menú"></a>
		</div>
</body>
</html>