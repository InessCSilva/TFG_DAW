<%-- Página de Retiradas --%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"
	import="java.util.*, es.studium.bancoAlimentos.modelo.*, es.studium.bancoAlimentos.controlador.* "%>
<%@ page trimDirectiveWhitespaces="true"%>
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


<title>Retiradas</title>
</head>

<body>
	<%-- Script para definir la cantidad maxima de alimentos que se pueden retirar --%>
	<script type="text/javascript">
		function cambiarCantidadLimite(cantidadAlimentoAlmacen) {
			const myArray = cantidadAlimentoAlmacen.split("-");
			let cantidadRestanteAlmacen = myArray[1];
			var inputCantidad = document.getElementById("cantidad");
			var inputCantidadDisponible = document.getElementById("cantidadDisponible");
			inputCantidad.setAttribute("max", cantidadRestanteAlmacen);
			inputCantidad.value = 1;
			inputCantidadDisponible.value = cantidadRestanteAlmacen;

		}
	</script>

	<div class="container text-center" id="container6">
		<h1>Retiradas</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String crearRetirada = request.getParameter("crearRetirada");

		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");

		// Comprobamos si se esta creando una nueva retirada
		if (crearRetirada == null) {

			// Recuperamos las retiradas 
			ArrayList<RetiradaBeneficiarioAlimento> retiradas = (ArrayList<RetiradaBeneficiarioAlimento>) session
			.getAttribute("retiradas");
		%>
		<h5>
			<strong>Historial de retiradas</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Fecha</th>
					<th>Cantidad Retirada</th>
					<th>Nombre beneficiario</th>
					<th>Apellidos beneficiario</th>
					<th>DNI beneficiario</th>
					<th>Nombre alimento</th>
					<th>Tipo alimento</th>
					<th>Marca alimento</th>
					<th>Cantidad alimento</th>
					<th>Alimento caduco</th>
				</tr>
				<%
				// Muestra las retiradas
				for (RetiradaBeneficiarioAlimento retirada : retiradas) {
				%>
				<tr>
					<td align="center"><%=retirada.getFechaRetirada()%></td>
					<td align="center"><%=retirada.getCantidadAlimentoRetirada()%></td>
					<td align="center"><%=retirada.getNombreBeneficiario()%></td>
					<td align="center"><%=retirada.getApellidosBeneficiario()%></td>
					<td align="center"><%=retirada.getDNIBeneficiario()%></td>
					<td align="center"><%=retirada.getNombreAlimento()%></td>
					<td align="center"><%=retirada.getApellidosAlimento()%></td>
					<td align="center"><%=retirada.getMarcaAlimento()%></td>
					<td align="center"><%=retirada.getCantidadAlimento()%></td>

					<%
					// Comprobamos la caducidad del alimento						
					if (retirada.getCaducidadAlimento()) {
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
			<!-- Pasamos como parametro que queremos crear una retirada para mostrar dicho menu-->
			<a href="retiradas.jsp?crearRetirada=si"> <input type="button"
				class="btn btn-success" value="Añadir retirada">
			</a>
		</div>
		<br>
		<%
		} else if (crearRetirada.equals("si")) {

		// Cargamos los beneficiarios y los alimentos en sesión
		ArrayList<Beneficiario> beneficiarios = (ArrayList<Beneficiario>) session.getAttribute("beneficiarios");

		ArrayList<AlimentoAlmacen> alimentos = (ArrayList<AlimentoAlmacen>) session.getAttribute("alimentosAlmacen");
		%>

		<div class="container text-center" id="container7">
			<form name="crearForm" action="retiradas" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearRetirada">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca la información de la retirada</h2>
					<tr align="center">

						<td colspan="2">Beneficiario:</br> <select name="idBeneficiarioFK" required>
								<%
								// Mostramos los beneficiarios en el SELECT, definimos el valor de la opción con el ID del donante.
								for (Beneficiario beneficiario : beneficiarios) {
									out.println("<option value='" + beneficiario.getIdBeneficiario() + "'>");
									out.println("Nombre: " + beneficiario.getNombreBeneficiario() + " " + beneficiario.getApellidosBeneficiario()
									+ " | DNI: " + beneficiario.getDniBeneficiario());
									out.println("</option>");
								}
								%>
						</select>
						</td>
						
					<tr>
						<td colspan="2">Alimento:</br> <select name="arrayParametrosAlmacen" required
							onChange="cambiarCantidadLimite(this.options[this.selectedIndex].value)">
								<%
								// Mostramos los alimentos en el SELECT, definimos el valor de la opción con el ID del alimento.
								for (AlimentoAlmacen alimento : alimentos) {
									out.println("<option value='" + alimento.getIdAlmacen() + "-" + alimento.getCantidadTotalAlmacen() + "-" + alimento.getIdAlimento()+"'>");
									out.println("Nombre: " + alimento.getNombreAlimento() + " | Tipo: " + alimento.getTipoAlimento() + " | Marca: "
									+ alimento.getMarcaAlimento() + " | Cantidad: " + alimento.getCantidadAlimento());
									out.println("</option>");
								}
								%>

						</select>
						</td>
					</tr>
					
					<tr>
						<td>Cantidad disponible: <br> <input 
							type="text" disabled name="cantidadDisponible" id="cantidadDisponible" size="10" value=<%out.println(alimentos.get(0).getCantidadTotalAlmacen());%> /></td>
						<!-- Definimos como valor maximo de la cantidad extraible la del primer alimento del select, hasta que se seleccione otro y se cambia via javascript -->
						<td>Cantidad a retirar: <br> <input required
							type="number" name="cantidad" id="cantidad" size="10" value="1"
							min="1"
							<%out.println("max=" + '"' + alimentos.get(0).getCantidadTotalAlmacen() + '"');%> /></td>
					</tr>
				</table>
				
				<br>
				<!-- Botón crear retirada-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear"
					value="Crear retirada" />
				<!-- Volver al listado-->
				<a href="retiradas.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
				</a>
			</form>
		</div>
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