/**
 * 
 */

function addTipoUsuario() {
	$('#bookDialog').dialog("option", "title", 'Agregar Tipo Usuario');
	$('#bookDialog').dialog('open');
}

function editTypeUser(id) {

	$.get("tipousuario/get/" + id, function(result) {

		$("#bookDialog").html(result);

		$('#bookDialog').dialog("option", "title", 'Editar Tipo Usuario');

		$("#bookDialog").dialog('open');

	});
}

function resetDialog(form) {

	form.find("input").val("");
}

$(document).ready(function() {

	$('#bookDialog').dialog({

		autoOpen : false,
		position : 'center',
		modal : true,
		resizable : false,
		width : 440,
		buttons : {
			"Save" : function() {
				$('#tipoUsuarioForm').submit();
			},
			"Cancel" : function() {
				$(this).dialog('close');
			}
		},
		close : function() {

			resetDialog($('#tipoUsuarioForm'));

			$(this).dialog('close');
		}
	});

});