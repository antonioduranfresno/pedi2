$(document).ready(function() {
	
	$('[data-toggle="tooltip"]').tooltip();
		
	$("#agencia").attr("readonly", true);
	$("#pedi_fechaCreacion").attr("readonly", true);
	$("#pedi_importe").attr("readonly", true);
	$("#pedi_fechaTasacion").attr("readonly", true);
	$("#pedi_importe").css({'font-weight': 'bold'});
		
	if($("#numeroFactura").val()!=""){		
		//Deshabilitamos todas las cajas/selects del formulario
		$("#pedidoForm :input").prop("disabled", true);		
		$("#numeroFactura").css({'font-weight': 'bold'});
	}
	
	$("#btn_desvincular").css("pointer-events", "none");
	
	if($("#numeroPrefactura").val()!="" || $("#idGrupo").val()!=""){
		
		$("#selCliente").attr("readonly", true);
		$("#selNodoOrigen").attr("readonly", true);
		$("#selNodoDestino").attr("readonly", true);
		
		if($("#numeroFactura").val()==""){
			$("#btn_desvincular").css("pointer-events", "auto");
		}
		
	}
		
	$('#divFechaRecogida').datetimepicker({
	 	language: 'es',
	 	format: "dd/mm/yyyy",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});	
	
	$('#divFechaEntrega').datetimepicker({
	 	language: 'es',
	 	format: "dd/mm/yyyy hh:ii",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 0,
		forceParse: 0			
	});	
	
	
});


$("#selCliente").change( function(){
	
	var selector			= document.getElementById("selCliente");
	var idCliente			= selector.options[selector.selectedIndex].value;
	
	$.ajax({
		type	 	: "post",
		url      	: "pedidoForm/selCliente="+idCliente,
		dataType	: "json",
		data 	 	: "",		
	}).done(function (data) {    			
		
		$("#selNodoOrigen").html(data.listaOrigenes);
		$("#selNodoDestino").html(data.listaDestinos);
		
	}).fail(function (jqXHR, textStatus) {
	    console.log("Error: "+textStatus);
	});
	
	devuelveTarifa();
	
});

$("#selNodoOrigen").change( function(){
	
	var selector		= document.getElementById("selNodoOrigen");
	var idNodo			= selector.options[selector.selectedIndex].value;
	
	$.ajax({
		type	 	: "post",
		url      	: "pedidoForm/selNodoOrigen="+idNodo,
		dataType	: "json",
		data 	 	: "",		
	}).done(function (data) {    			
		
		$("#tipo").val(data.tipo);

	}).fail(function (jqXHR, textStatus) {
	    console.log("Error: "+textStatus);
	});
	
	devuelveTarifa();
	
});

$("#selNodoDestino").change(function(){
	devuelveTarifa();
});

$("#pedi_fechaEntrega").change(function(){	
	devuelveTarifa();	
});

$("#pedi_totalHuecos").keyup(function(){	
	devuelveTarifa();	
});

$("#pedi_totalPalets").keyup(function(){	
	$("#pedi_totalHuecos").val($("#pedi_totalPalets").val());	
});

$(".modificable").each(function(){
	$(this).keyup(function(){
		$(this).css({'font-weight': 'bold','background-color':'#F2F5A9'});
	});
	$(this).change(function(){
		$(this).css({'font-weight': 'bold','background-color':'#F2F5A9'});
	});	
})

function devuelveTarifa(){
	
	var selCliente		= document.getElementById("selCliente");
	var idCliente		= selCliente.options[selCliente.selectedIndex].value;
	
	var selNodoOrigen	= document.getElementById("selNodoOrigen");
	var idNodoOrigen	= selNodoOrigen.options[selNodoOrigen.selectedIndex].value;
	
	var selNodoDestino	= document.getElementById("selNodoDestino");
	var idNodoDestino	= selNodoDestino.options[selNodoDestino.selectedIndex].value;
	
	var idAgencia		= $("#idAgencia").val();
	
	var fecha			= $("#pedi_fechaEntrega").val();
	
	var numeroHuecos	= $("#pedi_totalHuecos").val();
			
	var idPedido		= $("#id").val();
		
	if(idCliente!="0" && idNodoOrigen!="0" && idNodoDestino!="0" && fecha!="" && numeroHuecos!="" && numeroHuecos>0){

		$.ajax({
			type	 	: "get",
			url      	: "devuelve_tarifa",
			dataType 	: "json",
			data 	 	: {idCliente    : idCliente,
						   idAgencia	: idAgencia,
						   idNodoOrigen : idNodoOrigen,
						   idNodoDestino: idNodoDestino,
						   fecha	    : fecha,
						   numHuecos	: numeroHuecos,
						   idPedido		: idPedido
						   },
		}).done(function (res) {
			
			if(res.tarifa!=null){
				
				$("#pedi_importe").val(res.valorTarifa);
				
				if(res.valorTarifa!=0){
					$("#btnAceptar").attr("disabled", false);
					$("#pedi_fechaTasacion").val(res.fechaTasacion);
				}else{
					$("#btnAceptar").attr("disabled", true);
					$("#pedi_fechaTasacion").val('');
				}				
				
			}else{
				
				if(res.mensaje!=null){
					alert(res.mensaje);
				}else{
					alert("No existe tarifa");
					$("#pedi_importe").val(0.0);
					$("#pedi_fechaTasacion").val('');
				}
				
				$("#btnAceptar").attr("disabled", true);	
				
			}

		}).fail(function (jqXHR, textStatus) {
		    console.log("Error: "+textStatus);				
		});
		
	}
	
}

function desvincular(){
	
	var idPedido = $("#id").val();
	
	if(idPedido!=""){
		if(confirm("¿Desea desvincular el pedido de la prefactura?")){

			$.ajax({
				type	 	: "post",
				url      	: "desvinculaPedido&id="+idPedido,
				data 	 	: {}		
			}).done(function (data) {
				if(data!="error"){
					alert("Pedido sacado de la prefactura");
					$(location).attr('href','pedidoForm?idPedido='+idPedido);
				}else{
					alert("Error");
				}
				
			}).fail(function (jqXHR, textStatus) {
			    console.log("Error: "+textStatus);				
			});
			
		}			
	}else{
		alert("No se puede realizar la acción.");
	}

}
