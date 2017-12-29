function eliminar(id, objeto){
		
	if(id!=""){
		if(confirm("¿Desea eliminar el registro?")){

			$.ajax({
				type	 	: "post",
				url      	: objeto+"Lista&id="+id+"/eliminar",
				data 	 	: {}		
			}).done(function (data) {
				if(data=="ok"){
					$(location).attr('href',objeto+'Lista?success=true');	
				}else{
					alert("No se puede eliminar");
				}
				
			}).fail(function (jqXHR, textStatus) {
			    console.log("Error: "+textStatus);				
			});
			
		}			
	}else{
		alert("No se puede realizar la acción.");
	}

}

function agregar(id, objeto){
	
	$(".error").each(function(){
		$(this).html('');
	});
	
	$('#'+objeto+'Form')[0].reset();
	
	if(id!=0){
					
		$.ajax({
			type	 	: "get",
			url      	: "devuelve_"+objeto,
			dataType 	: "json",
			data 	 	: {id : id},
		}).done(function (res) {

			for (var key in res.objeto) {
				
				//Inputs
				if($("[name="+key+"]").prop('tagName')=='INPUT'){
					
					if($("[name="+key+"]").prop('type')=='checkbox'){
						
						$("#"+key).prop('checked', res.objeto[key]);
						
					}else{
						$("[name="+key+"]").val(res.objeto[key]);
					}
				
				//Textareas
				}else if($("[name="+key+"]").prop('tagName')=='TEXTAREA'){
						
					$("[name="+key+"]").html(res.objeto[key]);
					
				//Selects	
				}else{
					$("#"+key).val(res.objeto[key].id);					
				}
				
			}
			
		}).fail(function (jqXHR, textStatus) {
		    console.log("Error: "+textStatus);				
		});
		
	}else{
		$("#id").val(0);
		$('textarea').val(''); //Añadido porque el reset no afecta a textareas
	}
	
	$('#modalMaestra').modal({});
}

function aceptar(objeto){
	
	$.ajax({
		type	 	: "post",
		url      	: "aceptar_"+objeto,
		data 	 	: $('#'+objeto+'Form').serialize(),
	}).done(function (res) {
		
		if(res.status == "SUCCESS"){
			$(location).attr('href',objeto+'Lista?success=true');
		}else{	

			$(".error").each(function(){
				
				$(this).html('');
				
				for(i =0 ; i < res.result.length ; i++){
					
					$("#"+res.result[i].field).html(res.result[i].defaultMessage);
										
				}
				
       		});						
            
		}
		
	}).fail(function (jqXHR, textStatus) {
	    console.log("Error: "+textStatus);				
	});	
	
}

function cargarTabla(objeto){

	waitingDialog.show('Un momento, por favor...');
	
	$.ajax({
		url      	: objeto+"Lista/carga_tabla",
		type	 	: "post",
		async	 	: true,
		data 	 	: {},	
		success  	: function( data, textStatus, jqXHR) {
				
			$("#divTabla").html(data);		
			
			if(data!=''){
				
				$('#tabla').DataTable({ 										
		 		    	"language": {
		 		    		"url": "res/json/es.json"
		 		        },
		 		    	"pageLength": 10,
		 		    	"initComplete": waitingDialog.hide()		    
										
		 		}); 	
			}
				 								
		},
		   
		error: function (xhr, data, thrownError) {
			
		        alert(xhr.status);
		        alert(thrownError);
		}
	
	});
	
}

function marcar (idCheck) {	
	document.getElementById(idCheck + 'Span1').style.display ='inline-block';
	document.getElementById(idCheck + 'Span2').style.display ='none';
}

function desmarcar (idCheck) {
	document.getElementById(idCheck + 'Span1').style.display ='none';
	document.getElementById(idCheck + 'Span2').style.display ='inline-block';
} 
