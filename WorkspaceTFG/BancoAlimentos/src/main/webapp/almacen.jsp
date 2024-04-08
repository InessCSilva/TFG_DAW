<%-- Página de almacen --%>
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


<title>Almacén</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Almacén</h1>
		<hr />
		<br />

		<%
		// Recuperamos los alimentos almacenados
		ArrayList<AlimentoAlmacen> alimentos = (ArrayList<AlimentoAlmacen>) session.getAttribute("alimentosAlmacen");
		%>
		<h5>
			<strong>Alimentos disponibles en el almacén</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Marca</th>
					<th>Cantidad por unidad (KG/G/L)</th>
					<th>Caduca</th>
					<th>Stock en almacén</th>
				</tr>
				<%
				// Muestra los alimentos
				for (AlimentoAlmacen alimento : alimentos) {
				%>
				<tr>
					<td align="center"><%=alimento.getNombreAlimento()%></td>
					<td align="center"><%=alimento.getTipoAlimento()%></td>
					<td align="center"><%=alimento.getMarcaAlimento()%></td>
					<td align="center"><%=alimento.getCantidadAlimento()%></td>
					<%
					// Comprobamos la caducidad del alimento						
					if (alimento.getCaducidadAlimento()) {
					%>
					<td align="center">Si</td>
					<%
					} else {
					%>
					<td align="center">No</td>
					<%
					}
					%>
					<td align="center"><%=alimento.getCantidadTotalAlmacen()%></td>

				</tr>

				<%
				}
				%>
			</table>

		</div>
		<br>

		<!-- Salir al menú principal-->
		<a href="gestionBanco.html"><input type="button"
			class="btn btn-primary" value="Salir al menú"></a>
	</div>
</body>
</html>