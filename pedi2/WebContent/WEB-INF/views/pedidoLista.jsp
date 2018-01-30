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
    <link rel="stylesheet" href="res/css/bootstrap-datetimepicker.min.css"/>
    
  </head>

<body>

    <div id="wrapper">

		<jsp:include page="menu.jsp"/>

        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-sm-12">
                    <h2 class="page-header mini">
                    
                    	Pedidos 
                    
                    	<c:if test="${not empty param.idFactura}">
                    		
                    		<small> - Prefactura ${factura.fact_numeroPrefactura} / Factura ${factura.fact_numeroFactura}</small> 
                    	
                    	</c:if>
                            
                    	<button data-toggle="collapse" id="btnFiltros" class="btn btn-warning" data-target="#divFiltros" title="Mostrar filtros">
                    		<span class="glyphicon glyphicon-filter"></span>
                    	</button>                    	                    	
                    	<a href="pedidoForm" id="btnNuevo" class="btn btn-primary gefco-tooltip" data-toggle="tooltip" data-placement="auto" title="Nuevo pedido">
                    		<span class="glyphicon glyphicon-plus"></span> </a>
                    	<a href="#" id="btnAgrupar" class="btn btn-primary gefco-tooltip" data-toggle="tooltip" data-placement="auto" title="Agrupar pedidos" onclick="agrupar();">
                    		<span class="glyphicon glyphicon-paperclip"></span> 
                    	</a>
		        		<button data-toggle="tooltip" data-placement="auto" title="Deseleccionar todo" class="form-control btn btn-primary gefco-tooltip" onclick="deseleccionar();">
                    		<span class="glyphicon glyphicon-unchecked"></span>
                   	 	</button>	                    	
		        		<button data-toggle="tooltip" data-placement="auto" title="Seleccionar todo" class="form-control btn btn-primary gefco-tooltip" onclick="seleccionar();">
                    		<span class="glyphicon glyphicon-check"></span>
                   	 	</button>	                                	                    	
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
           
           <div id="divFiltros" class="collapse">
           
	           <div class="row">
	           
		        	<div class="col-sm-2">
		        		<label>Cliente</label>	        		
						<select class="form-control" id="filtroCliente" onchange="filtrar();">
							<option value="0">Todos</option>
							<c:forEach items="${listaClientes}" var="c" varStatus="index">
			        			<option value="${c.id}">${c.clie_alias}</option>
			        		</c:forEach>
						</select>		        		
		        	</div>
		        	<div class="col-sm-2">
		        		<label>Destino</label>	        	
						<select class="form-control" id="filtroNodoDestino" onchange="filtrar();">
							<option value="0">Todos</option>
							<c:forEach items="${listaNodosDestino}" var="c" varStatus="index">
			        			<option value="${c.id}">${c.nodo_nombre}</option>
			        		</c:forEach>
						</select>
		        	</div>		
		        	<div class="col-sm-3 date">
			        	<label>Fecha Entrega</label>
						<div class='input-group date' id='divFiltroFechaEntrega'>
							<span class="input-group-addon">Hasta</span>							
		                    <input class="form-control" id="fechaEntrega" onchange="filtrar();"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>									      
							</span>									
	            		</div>
			    	</div>
		        	<div class="col-sm-2">
		        		<label>Estado</label>	        	
						<select class="form-control" id="filtroEstadoFactura" onchange="filtrar();">
							<option value="0">Todos</option>
							<option value="1">Pendiente</option>
							<option value="2">Prefacturados</option>
							<option value="3">Facturados</option>							
						</select>
		        	</div>				    	  
			    	<div class="col-sm-2">
			    		<label class="vacio">.</label>			    		
			    		<input type="button" class="form-control btn btn-danger" id="btnPrefacturar" value="PREFACTURAR" onclick="ventanaFechaPreFactura();">
			    	</div>
			    	<div class="col-sm-1">
			    		<label class="vacio">.</label>	        	
			    		<a href="#" class="form-control btn btn-primary" onclick="quitarFiltros();" title="Quitar filtros" >                    		 
			    			<i class="fa fa-filter" style="text-decoration:line-through;"></i>
                    	</a>                    	
			    	</div>			    	
	           </div>
	           
			   <div class="row">
               		<div class="col-sm-12"><h2 class="page-header"></h2></div>
               </div>
	           
           </div>
                                 
		   <div class="table-responsive" id="divTabla">
		   			
		   </div>

        </div>

		<div id="modalFechaPrefactura" class="modal fade">
		
		  <div class="modal-dialog">
		
		    <div class="modal-content">
		    
		    <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h3 class="modal-title">Prefacturar seleccionadas</h3>        
		    </div>
		      
			    <div class="modal-body">
			      
					<form id="formularioFechaFactura" >
	
				    <div class="row">			    
			        	<div class="col-sm-6 date">			        							
							<label>Fecha Prefacturas</label>							
							<div class='input-group date' id='divFechaPrefacturaModal'>
			                    <input class="form-control" id="fechaPrefacturaModal" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>									
		            		</div>	            		
				    	</div>	
		        	</div>					
					
					</form>
			
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="prefacturar();">
				      	</div>      	
				      	
				    </div>		
		
			    </div>
	
		    </div>
		
		  </div>	  
		  
		</div>            
        
    </div>

    <script type="text/javascript" src='<c:url value="/res/js/jquery-1.10.2.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap.min.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/res/js/jquery.dataTables.js" />'></script>
	<script type="text/javascript" src='<c:url value="/res/js/dataTables.bootstrap.js" />'></script>	
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap-datetimepicker.min.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/locales/bootstrap-datetimepicker.es.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
        
    <script type="text/javascript">
    
    function existeParametro(parametro){
    	
    	var url = window.location.href;
    	if(url.indexOf('?' + parametro + '=') != -1)
    	    return true;
    	else if(url.indexOf('&' + parametro + '=') != -1)
    	    return true;
    	return false
    }
    
	$(document).ready(function() {
		
		$("#btnPrefacturar").prop("disabled", true);
		
		if(existeParametro('idFactura')){

			//Función que extrae parametros de la url
			$.urlParam = function(name){
				var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
				return results[1] || 0;
			}
			
			var idFactura = $.urlParam('idFactura');
			
			$("#btnFiltros").hide();
			$("#btnAgrupar").hide();
			$("#btnNuevo").hide();
			
			waitingDialog.show('Un momento, por favor...');
			
			$.ajax({				
				url      	: "pedidoLista&idFactura="+idFactura+"/carga_tabla",
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
				 		        "paging": false,		
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
			
		}else{
			
			$('[data-toggle="tooltip"]').tooltip(); 
			
			$('#divFiltroFechaEntrega').datetimepicker({
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
			
			waitingDialog.show('Un momento, por favor...');
			
			$.ajax({
				url      	: "pedidoLista/carga_tabla",
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
				 		        "paging": false,
				 		    	"order" : [1 , "desc"],
				 		        "initComplete" : waitingDialog.hide()
				 		}); 
						
						//Al iniciar solo muestro los pedidos pendientes:
						$("#filtroEstadoFactura").val(1);
					}
						 								
				},
				   
				error: function (xhr, data, thrownError) {
					
				        alert(xhr.status);
				        alert(thrownError);
				}
				
			});	
			
		}
		
		$('#divFechaPrefacturaModal').datetimepicker({
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
				
	});
	
	function agrupar(){
		
		var listaIds = [];
		//Ver si estan checked
		$("#divTabla").find("input[type='checkbox']").each(
				function(){
					if($(this).is(':checked')){
						listaIds.push(this.id);	
					}
				}
		);
		
		if(listaIds.length>0){				
			if(confirm("¿Desea agrupar los pedidos seleccionados?")){					
				$.ajax({
					type	 	: "get",
					url      	: "agrupa_pedidos",
					dataType 	: "json",
					data 	 	: {lista    : JSON.stringify(listaIds)},//Paso el array como string					
				}).done(function (res) {
					if(res.mensaje!=null){						
						alert(res.mensaje);						

						if(res.mensaje.indexOf("Error") !== -1){
							$(location).attr('href','pedidoLista?success=false');						
						}else{
							$(location).attr('href','pedidoLista?success=true');
						}
					}else{
						alert("Se ha producido un error");					
					}
				}).fail(function (jqXHR, textStatus) {
				    console.log("Error: "+textStatus);				
				});					
			}				
		}else{
			alert("Seleccione al menos dos pedidos para agrupar");
		}			
	}	
	
	function filtrar(){
		
		var selCliente		= document.getElementById("filtroCliente");
		var idCliente		= selCliente.options[selCliente.selectedIndex].value;
		
		var selNodoDestino	= document.getElementById("filtroNodoDestino");
		var idNodoDestino	= selNodoDestino.options[selNodoDestino.selectedIndex].value;
		
		var fechaEntrega	= $("#fechaEntrega").val();
		
		var selEstado		= document.getElementById("filtroEstadoFactura");
		var idEstado		= selEstado.options[selEstado.selectedIndex].value;
		
		waitingDialog.show('Un momento, por favor...');
		
		$.ajax({			
			url      	: "pedidoFiltroLista/carga_tabla",
			type	 	: "post",
			async	 	: true,
			data 	 	: {idCliente     : idCliente,
						   idNodoDestino : idNodoDestino,
						   fechaEntrega  : fechaEntrega,
						   idEstado		 : idEstado
						  },	
			success  	: function( data, textStatus, jqXHR) {
					
				$("#divTabla").html(data);		
				
				if(data!=''){
					
					$('#tabla').DataTable({ 										
			 		    	"language": {
			 		    		"url": "res/json/es.json"
			 		        },
			 		    	"paging": false,		
			 		    	"order" : [1 , "desc"],
			 		        "initComplete" : waitingDialog.hide()
			 		}); 	
				}
				
				//Control del botón prefacturar
				if(idEstado==1 && fechaEntrega.length>0){					
					$("#btnPrefacturar").prop("disabled", false);		
	
					//Quito el filtro de 10 elementos para mostrar todos y que se puedan seleccionar todos	
					/*var table = $('#tabla').DataTable();
					table.page.len( -1 ).draw();*/
					
					seleccionar();
				}else{
					$("#btnPrefacturar").prop("disabled", true);					
				}
				
			},
			   
			error: function (xhr, data, thrownError) {
				
			        alert(xhr.status);
			        alert(thrownError);
			}
		
		});
	}		
	
	function quitarFiltros(){
		
		$("#filtroCliente").val("0");
		$("#filtroNodoDestino").val("0");
		$('#fechaEntrega').val("");
		$("#filtroEstadoFactura").val("0");
		
		filtrar();
		
	}
	
	function seleccionar(){
	
		$("#divTabla").find("input[type='checkbox']").each(
				function(){					
					$(this).prop('checked',true);					
				}
		);		
	}

	function deseleccionar(){
		
		$("#divTabla").find("input[type='checkbox']").each(
				function(){					
					$(this).prop('checked',false);		
				}
		);		
	}
	
	function ventanaFechaPreFactura(){		
		$('#modalFechaPrefactura').modal({});
	}
	
	function prefacturar(){
		
		var listaIds = [];
		//Ver si estan checked
		$("#divTabla").find("input[type='checkbox']").each(
				function(){
					if($(this).is(':checked')){
						listaIds.push(this.id);	
					}
				}
		);
		
		var fechaPrefacturas = $("#fechaPrefacturaModal").val();
		var fechaLimite 	 = $("#fechaEntrega").val();
		
		if(listaIds.length>0){	
			if(fechaPrefacturas.length>0){
				if(confirm("¿Desea prefacturar los pedidos seleccionados?")){					
					$.ajax({
						type	 	: "get",
						url      	: "prefacturar",
						dataType 	: "json",
						data 	 	: {
										lista    		 : JSON.stringify(listaIds),
										fechaPrefacturas : fechaPrefacturas,
										fechaLimite		 : fechaLimite
									  },					
					}).done(function (res) {
						if(res.mensaje!=null){						
							alert(res.mensaje);						

							if(res.mensaje.indexOf("Error") !== -1){
								$(location).attr('href','pedidoLista?success=false');						
							}else{
								$(location).attr('href','facturaLista?documento=prefactura');
							}
						}else{
							alert("Se ha producido un error");					
						}
					}).fail(function (jqXHR, textStatus) {
					    console.log("Error: "+textStatus);				
					});					
				}						
			}else{
				alert("Introduzca fecha de las prefacturas");
			}		
		}else{
			alert("Seleccione al menos un pedido para prefacturar");
		}			
	}		
	
    </script>
    
</body>

</html>