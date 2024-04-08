<%-- Página de Donaciones --%>
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


<title>Donaciones</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Donaciones</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String crearDonacion = request.getParameter("crearDonacion");
		
		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");
		
		// Comprobamos si se esta creando una nueva donación
		if (crearDonacion == null) {
			// Recuperamos las donaciones 
			ArrayList<DonacionDonanteAlimento> donaciones = (ArrayList<DonacionDonanteAlimento>) session.getAttribute("donaciones");
		%>
		<h5>
			<strong>Historial de donaciones</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Fecha Donación</th>
					<th>Cantidad Donada</th>
					<th>Nombre donante</th>
					<th>Tipo donante</th>
					<th>Identificador donante</th>
					<th>Nombre alimento</th>
					<th>Tipo alimento</th>
					<th>Marca alimento</th>
					<th>Cantidad alimento</th>
					<th>Alimento caduco</th>
				</tr>
				<%
				// Muestra las donaciones
				for (DonacionDonanteAlimento donacion : donaciones) {
				%>
				<tr>
					<td align="center"><%=donacion.getFechaDonacion()%></td>
					<td align="center"><%=donacion.getCantidadAlimentoDonacion()%></td>
					<td align="center"><%=donacion.getNombreDonante()%></td>
					<td align="center"><%=donacion.getTipoDonante()%></td>
					<td align="center"><%=donacion.getIdentificadorDonante()%></td>
					<td align="center"><%=donacion.getNombreAlimento()%></td>
					<td align="center"><%=donacion.getTipoAlimento()%></td>
					<td align="center"><%=donacion.getMarcaAlimento()%></td>
					<td align="center"><%=donacion.getCantidadAlimento()%></td>

					<%
					// Comprobamos la caducidad del alimento						
					if (donacion.getCaducidadAlimento()) {
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

				<%
				}
				%>
			</table>
			<br> 
			<!-- Pasamos como parametro que queremos crear una donación para mostrar dicho menu-->
			<a href="donaciones.jsp?crearDonacion=si"> <input type="button"
				class="btn btn-success" value="Añadir donación">
			</a>

		</div>
		<br>
		<%
		} else if (crearDonacion.equals("si")) {

		// Cargamos los donantes y los alimentos en sesión
		ArrayList<Donante> donantes = (ArrayList<Donante>) session.getAttribute("donantes");

		ArrayList<Alimento> alimentos = (ArrayList<Alimento>) session.getAttribute("alimentos");
		%>

		<div class="container text-center" id="container7">
			<form name="crearForm" action="donaciones" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearDonacion">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca la información de la donación</h2>
					<tr align="center">

						<td>Donante:</br> <select name="idDonante" required>
								<%
								// Mostramos los donantes en el SELECT, definimos el valor de la opción con el ID del donante.
								for (Donante donante : donantes) {
									out.println("<option value='" + donante.getIdDonante() + "'>");
									out.println("Nombre: " + donante.getNombreDonante() + " | Tipo: " + donante.getTipoDonante() + " | Identificador: "
									+ donante.getIdentificadorDonante());
									out.println("</option>");
								}
								%>
						</select>
						</td>
					<tr>
						<td>Alimento:</br> <select name="idAlimento" required>
								<%
								// Mostramos los alimentos en el SELECT, definimos el valor de la opción con el ID del alimento.
								for (Alimento alimento : alimentos) {
									out.println("<option value='" + alimento.getId() + "'>");
									out.println("Nombre: " + alimento.getNombreAlimento() + " | Tipo: " + alimento.getTipoAlimento() + " | Marca: "
									+ alimento.getMarcaAlimento() + " | Cantidad: " + alimento.getCantidadAlimento());
									out.println("</option>");
								}
								%>

						</select>
						</td>
					</tr>
					<tr>
						<td>Cantidad: </br> <input required type="number" name="cantidad"
							size="10" value="1" /></td>
					</tr>
				</table>
				<br>
				<!-- Botón crear donación-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear"
					value="Crear donación" />
				<!-- Volver al listado-->
				<a href="donaciones.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
				</a>
			</form>
			<br>
		</div>
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