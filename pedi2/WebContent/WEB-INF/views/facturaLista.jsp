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
                    	
                    	<c:choose>
	                    	<c:when test="${param.documento == 'prefactura'}">
	                    		Prefacturas
	                    		<button data-toggle="tooltip" data-placement="auto" title="Facturar" class="form-control btn btn-danger gefco-tooltip" onclick="ventanaFechaFactura();">
                    				<span class="glyphicon glyphicon-euro"></span>
                   	 			</button>
	                    	</c:when>
	                    	<c:when test="${param.documento == 'factura'}">
	                    		Facturas	                    		                											                    		
	                    	</c:when>
                    	</c:choose>
						<button data-toggle="tooltip" data-placement="auto" title="Generar fichero EDI" class="form-control btn btn-primary gefco-tooltip" onclick="generarEdi();">
                    		<span class="glyphicon glyphicon-file"></span>
                   	 	</button>                    							                    	 
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
           
		   <div class="table-responsive" id="divTabla">

		   </div>
			        
        </div>
    </div>
    
	<div id="modalMaestra" class="modal fade">
	
	  <div class="modal-dialog">
	
	    <div class="modal-content">
	    
	    <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">
                <c:choose>
                  	<c:when test="${param.documento == 'prefactura'}">
                  		Prefactura
                  	</c:when>
                  	<c:when test="${param.documento == 'factura'}">
                  		Factura
                  	</c:when>
                </c:choose>			
			
			</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<sf:form id="facturaForm" method="post" modelAttribute="factura">
						
					<sf:input type="hidden" path="id" />
					<sf:input type="hidden" path="fact_esTransferencia" />
										
					<div class="row">

					<c:choose>
							
						<c:when test="${param.documento == 'factura'}">

				        	<div class="col-sm-6">
				        		<label>Nº Factura</label>
				        		<label id="fact_numeroFactura" class="error label label-danger"></label>								
				        		<sf:input class="form-control" id="numeroFactura" path="fact_numeroFactura" maxlength="45" />		        			   
				        	</div>
		
				        	<div class="col-sm-6 date">
					        	<label>Fecha Factura</label>
								<sf:errors path="fact_fechaFactura" class="label label-danger"></sf:errors>
								<div class='input-group date' id='divFechaFactura'>							
				                    <sf:input class="form-control" id="fact_fechaFactura" path="fact_fechaFactura" readonly="true" />
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>									      
									</span>									
			            		</div>	            		
					    	</div>
					    	
						</c:when>
					</c:choose>
					
					</div>    

					<div class="row">
			        	<div class="col-sm-6">
			        		<label>Nº Prefactura</label>
			        		<label id="fact_numeroPrefactura" class="error label label-danger"></label>								
			        		<sf:input class="form-control" path="fact_numeroPrefactura" maxlength="45" readonly="true" />		        			   
			        	</div>
	
			        	<div class="col-sm-6 date">
				        	<label>Fecha Prefactura</label>
							<sf:errors path="fact_fechaPrefactura" class="label label-danger"></sf:errors>
							<div class='input-group date' id='divFechaPrefactura'>							
			                    <sf:input class="form-control" id="fact_fechaPrefactura" path="fact_fechaPrefactura" readonly="true" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>									
		            		</div>	            		
				    	</div>					
					</div>

					<div class="row">
			        	<div class="col-sm-6">			        	
			        		<label>Estado</label>
							<label id="error_estadoPrefactura" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="fact_estadoPrefactura" path="fact_estadoPrefactura" onchange="completaFechaRespuesta();">
	                       		
	                       		<option value=0 ${factura.fact_estadoPrefactura == 0 ? 'selected' : ''}> Sin respuesta</option>
	                       		<option value=1 ${factura.fact_estadoPrefactura == 1 ? 'selected' : ''}> Validado</option>
	                       		<option value=2 ${factura.fact_estadoPrefactura == 2 ? 'selected' : ''}> Rechazado</option>	                       		
	                       			                       		               		            	
	                   		</sf:select>    			   
			        	</div>
	
			        	<div class="col-sm-6 date">
				        	<label>Fecha Respuesta</label>
							<sf:errors path="fact_fechaRespuesta" class="label label-danger"></sf:errors>
							<div class='input-group date' id='divFechaRespuesta'>							
			                    <sf:input class="form-control" id="fact_fechaRespuesta" path="fact_fechaRespuesta" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>									
		            		</div>	            		
				    	</div>					
					</div>

					<div class="row">
						<div class="col-sm-6">
	                  		<label>Agencia</label>	                  		
	                  		<label id="error_agencia" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="agencia" path="agencia.id"  readonly="true">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaAgencias}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == factura.agencia.id ? 'selected' : '' }>${c.toStringCodigo()}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			
						<div class="col-sm-6">				
							<label>Nº Pedidos</label>
						    <sf:input class="form-control text-right" path="fact_numeroPedidos" readonly="true"/>
						</div>								        	        	
					</div>
			
					<div class="row">
						<div class="col-sm-6">
	                  		<label>Cliente</label>	                  		
	                  		<label id="error_cliente" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="cliente" path="cliente.id" readonly="true">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaClientes}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == factura.cliente.id ? 'selected' : '' }>${c.clie_alias}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>
						<div class="col-sm-6">				
							<label>Código Destino</label>
						    <sf:input class="form-control" path="fact_codigoNodoCliente" readonly="true"/>
						</div>			        				        	
					</div>	     
					
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Destino</label>	                  		
	                  		<label id="error_nodoDestino" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodoDestino" path="nodoDestino.id" readonly="true">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodos}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == factura.nodoDestino.id ? 'selected' : '' }>${c.toStringCodigo()}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>									        				        	
					</div>
					
					<div class="row">
			        	<div class="col-sm-6 date">
				        	<label>Fecha Vencimiento</label>
							<sf:errors path="fact_fechaVencimiento" class="label label-danger"></sf:errors>
							<div class='input-group date' id='divFechaVencimiento'>							
			                    <sf:input class="form-control" id="fact_fechaVencimiento" path="fact_fechaVencimiento" readonly="true"/>
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>									
		            		</div>	            		
				    	</div>
			        	<div class="col-sm-6 date">
				        	<label>Fecha Límite Pedidos</label>
							<sf:errors path="fact_fechaLimite" class="label label-danger"></sf:errors>
							<div class='input-group date' id='divFechaLimite'>							
			                    <sf:input class="form-control" id="fact_fechaLimite" path="fact_fechaLimite" readonly="true"/>
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>									
		            		</div>	            		
				    	</div>					    					
			    	</div>
			    	
			    	<div class="row">
						<div class="col-sm-4">				
							<label>Base Imponible</label>
						    <sf:input class="form-control text-right" path="fact_baseImponible" readonly="true"/>
						</div>
						
						<div class="col-sm-4">				
							<label>Cuota</label>
						    <sf:input class="form-control text-right" path="fact_cuotaIva" readonly="true"/>
						</div>			    
						
						<div class="col-sm-4">				
							<label>Total</label>
						    <sf:input class="form-control text-right" path="fact_totalDocumento" readonly="true"/>
						</div>			    			    								   	
					</div>
										
					<br>
		
					<div class="footer">      
					      	
						<div class="col-sm-2 izquierdaEmergente">
							<input type="button" class="btn btn-primary" value="Ver pedidos" onclick="verPedidos();">
						</div>					      	
					      	
					 	<div class="col-sm-10 derecha">			
					 	
		 	                <c:choose>
			                  	<c:when test="${param.documento == 'prefactura'}">
			                  		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptarDocumento('prefactura');">
			                  	</c:when>
			                  	<c:when test="${param.documento == 'factura'}">
			                  		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptarDocumento('factura');">
			                  	</c:when>
			                </c:choose>	
					 	    
				      	</div>      	
				      	
				    </div>		
	
				</sf:form>
				
		    </div>

	    </div>
	
	  </div>
	  
	</div>
	  
	<div id="modalFechaFactura" class="modal fade">
	
	  <div class="modal-dialog">
	
	    <div class="modal-content">
	    
	    <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">Facturar seleccionadas</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<form id="formularioFechaFactura" >

			    <div class="row">			    
		        	<div class="col-sm-6 date">			        							
						<label>Fecha Facturas</label>							
						<div class='input-group date' id='divFechaFacturaModal'>
		                    <input class="form-control" id="fechaFacturaModal" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>									      
							</span>									
	            		</div>	            		
			    	</div>	
	        	</div>					
				
				</form>
		
				<div class="footer">      
				      	
				 	<div class="col-sm-12 derecha">			      		
			      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="facturar();">
			      	</div>      	
			      	
			    </div>		
	
		    </div>

	    </div>
	
	  </div>	  
	  
	</div>    
	
	<div id="modalFechaContable" class="modal fade">
	
	  <div class="modal-dialog">
	
	    <div class="modal-content">
	    
	    <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">Contabilizar seleccionadas</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<form id="formularioFechaContable" >

			    <div class="row">			    
		        	<div class="col-sm-6 date">			        							
						<label>Fecha Contable</label>							
						<div class='input-group date' id='divFechaContableModal'>
		                    <input class="form-control" id="fechaContableModal" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>									      
							</span>									
	            		</div>	            		
			    	</div>	
	        	</div>					
				
				</form>
		
				<div class="footer">      
				      	
				 	<div class="col-sm-12 derecha">			      		
			      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="contabilizar();">
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
    
	$(document).ready(function() {
		
		//Función que extrae parametros de la url
		$.urlParam = function(name){
			var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
			return results[1] || 0;
		}
		
		var documento = $.urlParam('documento');
				
		cargarTabla(documento); //puede ser prefactura o factura
		
		$('#divFechaFactura').datetimepicker({
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
		
		$('#divFechaPrefactura').datetimepicker({
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
		
		$('#divFechaVencimiento').datetimepicker({
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
		
		$('#divFechaFacturaModal').datetimepicker({
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
		
		$('#divFechaContableModal').datetimepicker({
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
		
		$('#divFechaRespuesta').datetimepicker({
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
		
		$('#divFechaLimite').datetimepicker({
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
		
	function aceptarDocumento(documento){
		
		$.ajax({
			type	 	: "post",
			url      	: "aceptar_factura",
			data 	 	: $('#facturaForm').serialize(),
		}).done(function (res) {
			
			if(res.status == "SUCCESS"){
				$(location).attr('href','facturaLista?documento='+documento+'&success=true');
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
	
	function eliminarDocumento(id){
		
		if(id!=""){
			if(confirm("¿Desea eliminar el registro?")){

				$.ajax({
					type	 	: "post",
					url      	: "facturaLista&id="+id+"/eliminar",
					dataType	: "json",
					data 	 	: {}		
				}).done(function (data) {
					
					if(data.tipoDocumento=="Error"){
						alert("No se puede eliminar");
					}else{
						$(location).attr('href','facturaLista?documento='+data.tipoDocumento+'&success=true');	
					}
					
				}).fail(function (jqXHR, textStatus) {
				    console.log("Error: "+textStatus);				
				});
				
			}			
		}else{
			alert("No se puede realizar la acción.");
		}

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
	
	function ventanaFechaFactura(){		
		$('#modalFechaFactura').modal({});
	}
	
	function ventanaContabilizar(){
		$('#modalFechaContable').modal({});
	}
	
	function facturar(){
		
		var listaIds = [];
		//Ver si estan checked
		$("#divTabla").find("input[type='checkbox']").each(
				function(){
					if($(this).is(':checked')){
						listaIds.push(this.id);	
					}
				}
		);
		
		var fecha = $("#fechaFacturaModal").val();
		
		if(listaIds.length>0){				
			if(fecha.length>0){
				if(confirm("¿Desea facturar las prefacturas seleccionadas?")){					
					$.ajax({
						type	 	: "get",
						url      	: "facturar",
						dataType 	: "json",
						data 	 	: {
										lista    : JSON.stringify(listaIds),
										fecha	 : fecha
										
									  },//Paso el array como string					
					}).done(function (res) {
						if(res.mensaje!=null){						
							alert(res.mensaje);						
							if(res.mensaje.indexOf("Error") !== -1){
								$(location).attr('href','facturaLista?documento=prefactura&success=false');						
							}else{
								$(location).attr('href','facturaLista?documento=factura&success=true');
							}
						}else{
							alert("Se ha producido un error");					
						}
					}).fail(function (jqXHR, textStatus) {
					    console.log("Error: "+textStatus);				
					});					
				}				
			}else{
				alert("Introduzca fecha de las facturas");
			}							
		}else{
			alert("Seleccione al menos una prefactura");
		}
	}	
	
	function verPedidos(){
		
		var idFactura = $("#id").val();
		
		$(location).attr('href','pedidoLista?idFactura='+idFactura);
				
	}
	
	function generarEdi(){
		
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
			if(confirm("¿Desea generar el fichero EDI de cada documento seleccionado?")){					
				$.ajax({
					type	 	: "get",
					url      	: "generar_edi",
					dataType 	: "json",
					data 	 	: {lista    : JSON.stringify(listaIds)},//Paso el array como string					
				}).done(function (res) {
					if(res.mensaje!=null){						
						alert(res.mensaje);
					}else{
						alert("Se ha producido un error");					
					}
				}).fail(function (jqXHR, textStatus) {
				    console.log("Error: "+textStatus);				
				});					
			}			
										
		}else{
			alert("Seleccione al menos un documento");
		}
		
	}

	function agregarDocumento(id,objeto){
		
		agregar(id, objeto);
		
		if(document.getElementById("numeroFactura")){ //Para ver si existe la caja
			$("#fact_estadoPrefactura").prop("disabled", true);
			$("#fact_fechaRespuesta").prop("disabled", true);
		}
		
	}
	
	function completaFechaRespuesta(){
		
		var selector			= document.getElementById("fact_estadoPrefactura");
		var estado				= selector.options[selector.selectedIndex].value;
		
		if(estado>0){

			var d = new Date();

			var month = d.getMonth()+1;
			var day = d.getDate();

			var fecha = (day<10 ? '0' : '') + day + '/' + (month<10 ? '0' : '') + month + '/' + d.getFullYear();
			
			$("#fact_fechaRespuesta").val(fecha);
			
		}else{
			$("#fact_fechaRespuesta").val('');
		}
				
	}
	
	
    </script>
    
</body>

</html>