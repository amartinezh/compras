<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url var="actionUrl" value="/tipousuario/save" />

<form:form id="tipoUsuarioForm" commandName="typeuser" method="post" action="${actionUrl }" class="pure-form pure-form-aligned">

	<fieldset>
		<legend></legend>
		<br>
		<div class="pure-control-group">
			<label for="code">Descripción:</label>
			<form:input path="descripcion" placeholder="descripcion" maxlength="30" />
		</div>
		
		<form:input path="id" type="hidden" />

	</fieldset>
</form:form>