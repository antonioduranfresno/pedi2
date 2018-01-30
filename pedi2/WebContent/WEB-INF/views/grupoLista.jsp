<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="icon" href="res/img/ico/favicon.ico" type="image/x-icon" />
    
    <title>PEDI2 - Gefco España S.A.</title>

    <link rel="stylesheet" href="res/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="res/css/font-awesome.min.css"/>    
    <link rel="stylesheet" href="res/css/estilos.css"/>
    <link rel="stylesheet" href="res/css/dataTables.bootstrap.min.css"/>
    
  </head>

<body>

    <div id="wrapper">

		<jsp:include page="menu.jsp"/>

        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-sm-12">
                    <h2 class="page-header mini">Grupo de pedidos   	                    	
						<a href="#" class="btn btn-primary gefco-tooltip" data-toggle="tooltip" data-placement="auto" title="Desagrupar pedidos" onclick="desagrupar();">
                    		<span class="glyphicon glyphicon-resize-full"></span> 
                    	</a>                             
                    	<c:choose>
							<c:when test="${param.success eq true}">
								<label class="alert alert-success ">Cambios realizados correctamente.</label>
							</c:when>
							<c:when test="${param.success eq false}">
								<label class="alert alert-danger ">No se realizaron los cambios.</label>
							</c:when>
						</c:choose>   
                    </h2>					                    
                </div>
           </div>
           
		   <div class="table-responsive" id="divTabla">
		   			
		   </div>

        </div>
    </div>

    <script type="text/javascript" src='<c:url value="/res/js/jquery-1.10.2.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap.min.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/res/js/jquery.dataTables.js" />'></script>
	<script type="text/javascript" src='<c:url value="/res/js/dataTables.bootstrap.js" />'></script>	
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
        
    <script type="text/javascript">
    
	$(document).ready(function() {
		
		$('[data-toggle="tooltip"]').tooltip();
		
		waitingDialog.show('Un momento, por favor...');

		//Función que extrae parametros de la url
		$.urlParam = function(name){
			var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
			return results[1] || 0;
		}
		
		var idGrupo = $.urlParam('idGrupo');
		
		$.ajax({
			url      	: "grupoLista&idGrupo="+idGrupo+"/carga_tabla",
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
			 		    	"order" : [1 , "desc"],
			 		        "initComplete" : waitingDialog.hide()
			 		}); 
										
				}
					 								
			},
			   
			error: function (xhr, data, thrownError) {
				
			        alert(xhr.status);
			        alert(thrownError);
			}
		
		});

	});

	
	function desagrupar(){ 
		
		if(confirm("¿Desea desagrupar los pedidos seleccionados?")){
			
			var listaIds = [];
			//Ver si estan checked
			$("#divTabla").find("input[type='checkbox']").each(
					function(){
						if($(this).is(':checked')){
							listaIds.push(this.id);	
						}
					}
			);
			
			if(listaIds.length>1){
				
				$.ajax({
					type	 	: "get",
					url      	: "desagrupa_pedidos",
					dataType 	: "json",
					data 	 	: {lista    : JSON.stringify(listaIds)},//Paso el array como string					
				}).done(function (res) {
					
					alert(res.mensaje);
					$(location).attr('href','pedidoLista?success=true');

				}).fail(function (jqXHR, textStatus) {
				    console.log("Error: "+textStatus);				
				});
			}else{
				alert("Seleccione al menos 2 pedidos");
			}
			
		}

	}
	
    </script>
    
</body>

</html>