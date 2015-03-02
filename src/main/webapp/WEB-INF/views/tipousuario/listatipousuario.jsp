<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Lista Tipo de Usuarios</title>

<link rel="stylesheet" href="<c:url value="/resources/css/pure-0.4.2.css"/>" >

<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.0.3/css/font-awesome.css"/>" >

<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.10.4.custom.css"/>" >

<style type="text/css">
th {
	text-align: left
}
</style>


</head>

<body>
	<div style="width: 95%; margin: 0 auto;">

		<div id="bookDialog" style="display: none;">
			<%@ include file="tuForm.jsp"%>
		</div>

		<h1>LISTA DE TIPOS DE USUARIOS</h1>
	
		<button class="pure-button pure-button-primary" onclick="addTipoUsuario()">
			<i class="fa fa-plus"></i> Agregar Tipo Usuario
		</button>
		 
		<br>
		<table class="pure-table pure-table-bordered pure-table-striped">
			<thead>
				<tr>
					<th width="4%">ID</th>
					<th width="12%">Descipción</th>										
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${typeuserList}" var="typeUserr" varStatus="loopCounter">
					<tr>
						<!-- <td><c:out value="${loopCounter.count}" /></td>  -->
						<td><c:out value="${typeUserr.id}" /></td>
						<td><c:out value="${typeUserr.descripcion}" /></td>

						<!-- <td><c:out value="${book.code}" /></td>
						<td><c:out value="${book.price}" /></td>
						<td><c:out value="${book.authors}" /></td>
						<td><c:out value="${book.isbn}" /></td>
						<td><c:out value="${book.publisher}" /></td>
						<td><c:out value="${book.publishedOn}" /></td>
-->
						<td><nobr>
								<button class="pure-button pure-button-primary"
									onclick="editTypeUser(${typeUserr.id});">
									<i class="fa fa-pencil"></i> Editar
								</button>
								<a class="pure-button pure-button-primary"
									onclick="return confirm('¿Desea continuar con eliminar este tipo de usuario?');"
									href="tipousuario/delete/${typeUserr.id}"> <i class="fa fa-times"></i> Borrar
								</a>

							</nobr></td>
							 
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<!--  It is advised to put the <script> tags at the end of the document body so they don't block rendering of the page -->
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-1.10.2.js"/>" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-ui-1.10.4.custom.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery.ui.datepicker.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/lib/js-listTipoUsuario.js"/>"></script>
		 
</body>
</html>