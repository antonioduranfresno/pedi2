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
                    <h2 class="page-header mini">Tarifas Venta                    	
                    	<a href="#" onclick="agregar(0,'tarifaVenta');" class="btn btn-primary" title="Nueva tarifa"><span class="glyphicon glyphicon-plus"></span></a>
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
			<h3 class="modal-title">Tarifa Venta</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<sf:form id="tarifaVentaForm" method="post" modelAttribute="tarifaVenta">
						
					<sf:input type="hidden" path="id" />
					
					<div class="row">						
						<div class="col-sm-6 date">
				        	<label>Desde</label>
							<label id="tave_fechaDesde" class="error label label-danger"></label>
							<div class='input-group date' id='divFechaDesde'>
								<sf:input  class="form-control" id="tave_fechaDesde" path="tave_fechaDesde" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>									      
								</span>
							</div>								            		
				    	</div>
				    	
						<div class="col-sm-6 date pull-right">
								<label>Hasta</label>
								<label id="tave_fechaHasta" class="error label label-danger"></label>
								<div class='input-group date' id='divFechaHasta'>		
				                    <sf:input  class="form-control" id="tave_fechaHasta" path="tave_fechaHasta" />
				                    <span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>									      
									</span>
							    </div>
				    	</div>
				    </div>
				    	
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Agencia</label>	                  		
	                  		<label id="error_agencia" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="agencia" path="agencia.id" >
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaAgencias}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == tarifaVenta.agencia.id ? 'selected' : '' }>${c.agen_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>	
			        </div>
										
				   	<div class="row">
						<div class="col-sm-12">
	                  		<label>Cliente</label>	                  		
	                  		<label id="error_cliente" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="cliente" path="cliente.id" >
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaClientes}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == tarifaVenta.cliente.id ? 'selected' : '' }>${c.clie_alias}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>
			        </div>
			        <div class="row">
						<div class="col-sm-12">
	                  		<label>Origen</label>	                  		
	                  		<label id="error_nodoOrigen" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodoOrigen" path="nodoOrigen.id" >
	                       		<option value="0">Selección</option>	                          	
	                   		</sf:select>
			        	</div>			        	
					</div>
					
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Destino</label>	                  		
	                  		<label id="error_nodoDestino" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodoDestino" path="nodoDestino.id" >
	                       		<option value="0">Selección</option>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
					<div class="row" style="margin-top: 12px; ">
						<div class="col-sm-12">
							<sf:checkbox class="checkGrande" data-group-cls="btn-group-justified" id="tave_soloTranchas" path="tave_soloTranchas" onclick="controlSoloTranchas();"/>			        			
			        		<label id="etiqueta_precio_cc"><b>Solo Tranchas</b></label>			
						</div>
					</div>
					
			        <div class="table-responsive" id="divTabla">
			        
			        <table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>
			        	<tr>
			        		<td class="default" width="20%">
								<label id="etiqueta_precio_cc"><b>Precio C.C.</b></label>
			        			<label id="error_tave_importeCamionCompleto" class="error label label-danger"></label>
			        		</td>
			        		<td class="default" width="40%">				        			
								<div class="input-group">									
									<span class="input-group-addon">Desde</span>
									<sf:input class="form-control text-right" id="numeroDesdeCC" path="tave_numeroDesdeCC" onkeyup="controlRelacionTranchas();"/>
									<span class="input-group-addon" id="basic-addon2">huecos</span>
								</div>				        			
			        		</td>
			        		<td width="40%">
								<div class="input-group" >
					        		<sf:input class="form-control text-right" path="tave_importeCamionCompleto" />
					        		<span class="input-group-addon" id="basic-addon2">€</span>
					        	</div>		
			        		</td>
			       		</tr> 
			        </table>
			        
			        <table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>
						<thead>
							<tr class="info">			
								<th width="20%">Trancha</th>
								<th width="40%">Hasta</th>
								<th width="40%">Precio / hueco</th>
							</tr>
						</thead>
						
						<tr>
							<td>
								<label>1</label>
								<label id="tave_numeroMaxT1" class="error label label-danger"></label>
								<label id="tave_importeT1" class="error label label-danger"></label>
							</td>
							<td>
								<div class="input-group">									
									<sf:input class="form-control text-right" id="numeroMaxT1" path="tave_numeroMaxT1" onkeyup="controlRelacionTranchas();" />
									<span class="input-group-addon" id="basic-addon2">huecos</span>
								</div>															
							</td>
							<td>
								<div class="input-group">									
			        				<sf:input class="form-control text-right" id="importeT1" path="tave_importeT1" onkeyup="controlRelacionTranchas();" />
			        				<span class="input-group-addon" id="basic-addon2">€</span>
			        			</div>			        			
							</td>
						</tr>
						<tr>
							<td>
								<label>2</label>
								<label id="tave_numeroMaxT2" class="error label label-danger"></label>
								<label id="tave_importeT2" class="error label label-danger"></label>
							</td>
							<td>
								<div class="input-group">
									<sf:input class="form-control text-right" id="numeroMaxT2" path="tave_numeroMaxT2" onkeyup="controlRelacionTranchas();" />
									<span class="input-group-addon" id="basic-addon2">huecos</span>
								</div>															
							</td>
							<td>
								<div class="input-group">									
			        				<sf:input class="form-control text-right" id="importeT2" path="tave_importeT2" onkeyup="controlRelacionTranchas();" />
			        				<span class="input-group-addon" id="basic-addon2">€</span>
			        			</div>			        			
							</td>
						</tr>		
						<tr>
							<td>
								<label>3</label>
								<label id="tave_numeroMaxT3" class="error label label-danger"></label>
								<label id="tave_importeT3" class="error label label-danger"></label>
							</td>
							<td>
								<div class="input-group">
									<sf:input class="form-control text-right" id="numeroMaxT3" path="tave_numeroMaxT3" onkeyup="controlRelacionTranchas();" />
									<span class="input-group-addon" id="basic-addon3">huecos</span>
								</div>															
							</td>
							<td>
								<div class="input-group">									
			        				<sf:input class="form-control text-right" id="importeT3" path="tave_importeT3" onkeyup="controlRelacionTranchas();" />
			        				<span class="input-group-addon" id="basic-addon2">€</span>
			        			</div>			        			
							</td>
						</tr>
						<tr>
							<td>
								<label>4</label>
								<label id="tave_numeroMaxT4" class="error label label-danger"></label>
								<label id="tave_importeT4" class="error label label-danger"></label>
							</td>
							<td>
								<div class="input-group">
									<sf:input class="form-control text-right" id="numeroMaxT4" path="tave_numeroMaxT4" onkeyup="controlRelacionTranchas();" />
									<span class="input-group-addon" id="basic-addon4">huecos</span>
								</div>															
							</td>
							<td>
								<div class="input-group">									
			        				<sf:input class="form-control text-right" id="importeT4" path="tave_importeT4" onkeyup="controlRelacionTranchas();" />
			        				<span class="input-group-addon" id="basic-addon2">€</span>
			        			</div>			        			
							</td>
						</tr>
						<tr>
							<td>
								<label>5</label>
								<label id="tave_numeroMaxT5" class="error label label-danger"></label>
								<label id="tave_importeT5" class="error label label-danger"></label>
							</td>
							<td>
								<div class="input-group">
									<sf:input class="form-control text-right" id="numeroMaxT5" path="tave_numeroMaxT5" onkeyup="controlRelacionTranchas();" />
									<span class="input-group-addon" id="basic-addon5">huecos</span>
								</div>															
							</td>
							<td>
								<div class="input-group">									
			        				<sf:input class="form-control text-right" id="importeT5" path="tave_importeT5" onkeyup="controlRelacionTranchas();" />
			        				<span class="input-group-addon" id="basic-addon2">€</span>
			        			</div>			        			
							</td>
						</tr>																						
			        
			        </table>			        
			        
			        </div>
		
					<div class="footer">
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" id="aceptarTarifa" value="Aceptar" onclick="aceptar('tarifaVenta');">
				      	</div>      	
				    </div>		
	
				</sf:form>
				
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
		
		cargarTabla('tarifaVenta');
		
		$('#divFechaDesde').datetimepicker({
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
		
		$('#divFechaHasta').datetimepicker({
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
	
	$("#cliente").change( function(){
		
		var selector			= document.getElementById("cliente");
		var idCliente			= selector.options[selector.selectedIndex].value;
		
		asignaNodos(0, idCliente);
		
	});
	
	function asignaNodos(idTarifa, idCliente){
				
		$.ajax({
			type	 	: "post",
			url      	: "tarifaVentaLista/selCliente="+idCliente,
			dataType	: "json",
			data 	 	: "",		
		}).done(function (data) {    			
			
			$("#nodoOrigen").html(data.listaOrigenes);
			$("#nodoDestino").html(data.listaDestinos);
	
			if(idTarifa!=0){
				agregarTarifa(idTarifa); 
			}
						
		}).fail(function (jqXHR, textStatus) {
		    console.log("Error: "+textStatus);
		});
	
	}

	function controlSoloTranchas(){
		
		if ($("#tave_soloTranchas").is(':checked')) {			
			$("#numeroDesdeCC").val(0);
			$("#tave_importeCamionCompleto").val(0);
			$("#numeroDesdeCC").prop("readonly", true);
			$("#tave_importeCamionCompleto").prop("readonly", true);
		
			controlRelacionTranchas();
			
		}else{
			
			$("#numeroDesdeCC").prop("readonly", false);
			$("#tave_importeCamionCompleto").prop("readonly", false);
			
			$("#aceptarTarifa").prop("disabled", funcion());
		}
		
	}
	
	function controlRelacionTranchas(){
		
		//Si has rellenado cantidad de palets X debes rellenar el importe X
		var valor = false;
		var numTranchas = 0;
		
		for (var i = 1; i < 6; i++) {
			if($("#numeroMaxT"+i).val().length>0){			
				if($("#importeT"+i).val().length>0){
					numTranchas++;
					valor = false;
				}else{								
					valor = true;
					break;
				}
			}
			if($("#importeT"+i).val().length>0){			
				if($("#numeroMaxT"+i).val().length>0){					
					valor = false;
				}else{								
					valor = true;
					break;
				}
			}	
		}
		
		//Si has rellenado cantidad de palets/importe Trancha Y debe existir valor para cantidad de palets/importe Trancha Y - 1 (siendo Trancha Y > 1)
		if(!valor){
			
			for (var i = 2; i < 6; i++) {
				if($("#numeroMaxT"+i).val().length>0){
					if($("#numeroMaxT"+(i-1)).val().length>0){
						//Valores coherentes
						if(parseInt($("#numeroMaxT"+i).val())>parseInt($("#numeroMaxT"+(i-1)).val())){
							valor = false;
						}else{
							valor = true;
							break;	
						}
					}else{								
						valor = true;
						break;
					}
				}
				if($("#importeT"+i).val().length>0){
					if($("#importeT"+(i-1)).val().length>0){
						valor = false;
					}else{								
						valor = true;
						break;
					}
				}		
			}
			
		}
		
		if(!valor){			
			
			//Si has marcado soloTranchas, debe haber al menos una trancha rellenada
			if ($("#tave_soloTranchas").is(':checked')) {				
				if(numTranchas>0){
					valor = false;
				}else{
					valor = true;
				}
			}else{								
				//Verificación numeroDesde > 0
				valor = funcion();								
			}
		}
		
		$("#aceptarTarifa").prop("disabled", valor);
		
	}
	
	function funcion(){
		
		var valor = false;
		
		if(parseInt($("#numeroDesdeCC").val())==0){
			console.log("Vale 0");
			valor = true;
		}else{
			console.log("Distinto de 0");										
			//Comprobación valores tranchas inferiores al valor desde cc
			for (var i = 1; i < 6; i++) {
				if($("#numeroMaxT"+i).val().length>0){
					if(parseInt($("#numeroMaxT"+i).val())>=parseInt($("#numeroDesdeCC").val())){							
						valor = true;
						break;
					}else{
						valor = false;
					}
				}					
			}					
			
		}
		
		return valor;
	}
	
	function agregarTarifa(id){
				
		$(".error").each(function(){
			$(this).html('');
		});
		
		$('#tarifaVentaForm')[0].reset();
			
		if(id!=0){
						
			$.ajax({
				type	 	: "get",
				url      	: "devuelve_tarifaVenta",
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
						
						if(res.objeto[key].id!=null){
							$("#"+key).val(res.objeto[key].id);	
						}else{
							$("#"+key).val(res.objeto[key]);	
						}					
											
					}
					
				}
				
				controlSoloTranchas();
				
			}).fail(function (jqXHR, textStatus) {
			    console.log("Error: "+textStatus);				
			});
			
		}else{
			$("#id").val(0);
			$('textarea').val(''); //Añadido porque el reset no afecta a textareas
		}
		
		$('#modalMaestra').modal({});
	}	
	
    </script>
    
</body>

</html>