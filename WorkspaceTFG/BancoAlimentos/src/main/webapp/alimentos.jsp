<%-- Página de alimentos --%>
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
body {font-family: Arial, Helvetica, sans-serif;}

table {     font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
    font-size: 17px;    width: 480px;   border-collapse: collapse; }

th {     font-size: 20px;     font-weight: normal;     padding: 8px;     background: #b9c9fe;
    border-top: 4px solid #aabcfe;    border-bottom: 1px solid #fff; color: #039; }

td {    padding: 8px;     background: #e8edff;     border-bottom: 1px solid #fff;
    color: #669;    border-top: 1px solid transparent; }

tr:hover td { background: #d0dafd; color: #339; }
</style>


<title>Alimentos</title>
</head>

<br>
<br>

<body>
	<div class="container text-center" id="container6">
		<h1>Alimentos</h1>
		<hr />
		<br />

		<%
		// Comprobamos si hay alguna operacion, en caso contrario mostramos el listado
		String idAlimentoEditar = request.getParameter("editarAlimentoID");
		String crearAlimento = request.getParameter("crearAlimento");
		String idAlimentoBorrar = request.getParameter("borrarAlimentoID");

		// Tambien recuperamos si, en caso de haberse realizado una operacion, esta ha sido realizada correctamente para 
		// añadir el mensaje al final
		Boolean operacionOK = (Boolean) session.getAttribute("operacionOK");

		if (idAlimentoEditar == null && crearAlimento == null && idAlimentoBorrar == null) {
			// Recuperamos los alimentos
			ArrayList<Alimento> alimentos = (ArrayList<Alimento>) session.getAttribute("alimentos");
		%>

		<h5>
			<strong>Listado de alimentos</strong>
		</h5>

		<div class="container text-center" id="container7">
			<table align="center" class="table table-bordered" border="1">
				<tr align="center">
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Marca</th>
					<th>Cantidad (KG/G/L)</th>
					<th>Caduca</th>
					<th>&nbsp;</th>
				</tr>
				<%
				// Muestra los alimentos
				for (Alimento alimento : alimentos) {
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
					
					<td align="center">
						<!-- Pasamos el id como parametros y recargamos la pagina--> 
						<a href="alimentos.jsp?editarAlimentoID=<%=alimento.getId()%>"> 
						<input type="button" class="btn btn-info" value="Editar">
						</a>
						<a href="alimentos.jsp?borrarAlimentoID=<%=alimento.getId()%>">
							<input type="button" class="btn btn-danger" value="Borrar">
						</a>
					</td>
				</tr>

				<%
				}
				%>
			</table>
			<br>
			<br>
			<!-- Pasamos como parametro que queremos crear un alimento para mostrar dicho menu-->
			<a href="alimentos.jsp?crearAlimento=si"> 
			<input type="button" class="btn btn-success" value="Crear Alimento">
			</a>
		</div>
		<%
		}
		
		// Comprueba si se ha elegido crear un alimento
		else if (crearAlimento != null && crearAlimento.equals("si")) {
		%>
		<div class="container text-center" id="container7">
			<form name="crearForm" action="alimentos" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="crearAlimento">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos del alimento</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre" /></td>

						<td>Tipo:</br> <input required type="text" name="tipo" /></td>

						<td>Marca:</br> <input required type="text" name="marca" /></td>

						<td>Cantidad:</br> <input required
							name="cantidad" id="cantidad" size="10"  /></td>

						<td>Caducidad:</br> <select name="caducidad">
								<option selected value="false">No</option>
								<option value="true">Si</option>
						</select>
					</tr>
				</table>
				<br>
				<!-- Botón crear alimento-->
				<input type="submit" class="btn btn-success" name="ejecutarCrear" value="Crear Alimento" />
				<!-- Volver al listado-->
			<a href="alimentos.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		
		// Comprueba si existe un identificador de alimento definido para editar
		else if (idAlimentoEditar != null) {

		// Recuperamos los alimentos
		ArrayList<Alimento> alimentos = (ArrayList<Alimento>) session.getAttribute("alimentos");
		Alimento alimentoSeleccionado = null;

		// Recorremos los alimentos y nos quedamos con el que tiene el identificador definido para editar
		for (Alimento alimento : alimentos) {
			if (alimento.getId().toString().equals(idAlimentoEditar)) {
				alimentoSeleccionado = alimento;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="editarForm" action="alimentos" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="editarAlimento"> <input align="center" type="hidden"
					name="id" value="<%out.print(alimentoSeleccionado.getId());%>">
				<table align="center" class="table table-bordered" border="1">
					<h2 align="center">Introduzca los datos a modificar</h2>
					<tr align="center">

						<td>Nombre:</br> <input required type="text" name="nombre"
							value="<%out.println(alimentoSeleccionado.getNombreAlimento());%>" /></td>

						<td>Tipo:</br> <input required type="text" name="tipo"
							value="<%out.println(alimentoSeleccionado.getTipoAlimento());%>" /></td>

						<td>Marca:</br> <input required type="text" name="marca"
							value="<%out.println(alimentoSeleccionado.getMarcaAlimento());%>" /></td>

						<td>Cantidad:</br> <input required
							type="number" name="cantidad" id="cantidad" size="10" value="1"
							min="1"<%out.println(alimentoSeleccionado.getCantidadAlimento());%>" /></td>

						<td>Caducidad:</br> <select name="caducidad">
								<%
								// Comprobamos la caducidad del alimento						
								if (alimentoSeleccionado.getCaducidadAlimento()) {
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
				<br>
				<!-- Botón Editar alimento-->
				</a> <input type="submit" class="btn btn-success" name="ejecutarEditar"
					value="Editar Alimento" />
					<!-- Volver al listado-->
			<a href="alimentos.jsp"> <input type="button"
					class="btn btn-danger" value="Volver al listado">
			</form>
		</div>
		<%
		}
		
		// Comprueba si existe un identificador de alimento definido para borrar
		else if (idAlimentoBorrar != null) {

		// Recuperamos los alimentos
		ArrayList<Alimento> alimentos = (ArrayList<Alimento>) session.getAttribute("alimentos");
		Alimento alimentoSeleccionado = null;

		// Recorremos los alimentos y nos quedamos con el que tiene el identificador definido para borrar
		for (Alimento alimento : alimentos) {
			if (alimento.getId().toString().equals(idAlimentoBorrar)) {
				alimentoSeleccionado = alimento;
				break;
			}
		}
		%>
		<div class="container text-center" id="container7">
			<form name="borrarForm" action="alimentos" method="POST">
				<input align="center" type="hidden" name="operacion"
					value="borrarAlimento"> <input align="center" type="hidden"
					name="id" value="<%out.print(alimentoSeleccionado.getId());%>">
				<table align="center" class="table table-bordered" border="1">
					<tr align="center">
						<th>Nombre</th>
						<th>Tipo</th>
						<th>Marca</th>
						<th>Cantidad</th>
						<th>Caduca</th>
					</tr>
					<tr>
						<td align="center"><%=alimentoSeleccionado.getNombreAlimento()%></td>
						<td align="center"><%=alimentoSeleccionado.getTipoAlimento()%></td>
						<td align="center"><%=alimentoSeleccionado.getMarcaAlimento()%></td>
						<td align="center"><%=alimentoSeleccionado.getCantidadAlimento()%></td>
						<%
						// Comprobamos la caducidad del alimento						
						if (alimentoSeleccionado.getCaducidadAlimento()) {
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
				<a href="alimentos.jsp"> <input type="button"
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
		</div>
</body>
</html>