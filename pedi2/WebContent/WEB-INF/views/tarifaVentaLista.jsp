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
    
    <title>DIA - Gefco España S.A.</title>

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
                    <h2 class="page-header derecha">Tarifas Venta
                    	<a href="exportarTarifaVenta" class="btn btn-success"><span class="glyphicon glyphicon-export"></span> Exportar</a>
                    	<a href="#" onclick="agregar(0,'tarifaVenta');" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Añadir</a>
                    </h2>
                </div>
           </div> 
           
           <div class="row">
           	   <c:choose>
				    <c:when test="${param.success eq true}">
				        <div class="alert alert-success">Cambios realizados correctamente.</div>
				    </c:when>
			   </c:choose>
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
						
					<sf:input type="hidden" path="id" value="${tarifaVenta.id}" />
					
					<div class="row">
						<div class="col-sm-1 ">
				        	<label>Desde</label>
							<label id="tave_fechaDesde" class="error label label-danger"></label>
						</div>
						<div class="col-sm-3 date">
							<sf:input  class="form-control" id="tave_fechaDesde" path="tave_fechaDesde" />
														
								            		
				    	</div>
				    	<div class="col-sm-1 date"></div>
				    	<div class="col-sm-1 ">
				        	<label>Hasta</label>
							<label id="tave_fechaHasta" class="error label label-danger"></label>
						</div>
						<div class="col-sm-3 date">		
			                    <sf:input  class="form-control" id="tave_fechaHasta" path="tave_fechaHasta" />
							         		
				    	</div>
				    </div>
				    	
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Agencia</label>	                  		
	                  		<label id="error_agencia" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="agencia" path="agencia.id" value="${tarifaVenta.agencia.id}">
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
	                  		<sf:select class="form-control" id="cliente" path="cliente.id" value="${tarifaVenta.cliente.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaClientes}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == tarifaVenta.cliente.id ? 'selected' : '' }>${c.clie_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>
			        </div>
			        <div class="row">
						<div class="col-sm-12">
	                  		<label>Origen</label>	                  		
	                  		<label id="error_nodoOrigen" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodoOrigen" path="nodoOrigen.id" value="${tarifaVenta.nodoOrigen.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodosOrigen}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == tarifaVenta.nodoOrigen.id ? 'selected' : '' }>${c.nodo_direccion}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Destino</label>	                  		
	                  		<label id="error_nodoDestino" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodoDestino" path="nodoDestino.id" value="${tarifaVenta.nodoDestino.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodosDestino}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == tarifaVenta.nodoDestino.id ? 'selected' : '' }>${c.nodo_direccion}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
				   	<br>
			        <div class="row">
			        	<div class="col-sm-5">
			        		<label>Precio camión completo</label>
			        		<label id="tave_importeCamionCompleto" class="error label label-danger"></label>
			        	</div>
			        	
			        	<div class="col-sm-3">
								   
			        		<sf:input class="form-control text-right" path="tave_importeCamionCompleto" value="${tarifaVenta.tave_importeCamionCompleto}" />
			        	</div>
			        	<div class="col-sm-4">
			        		<label>€ / Camión</label>
			        	</div>
			        </div>
			        
			        <br>
			        <div class="row">	
			        	<div class="col-sm-4">
			        		<label>Trancha 1. Hasta</label>
			        	
							<label id="tave_numeroMaxPaletT1" class="error label label-danger"></label>
						</div>
						<div class="col-sm-2">
			        		<sf:input class="form-control text-right" path="tave_numeroMaxPaletT1" value="${tarifaVenta.tave_numeroMaxPaletT1}" />
			        	</div>
			        	<div class="col-sm-1" >
			        		<label>palets </label>
			        	</div>
			        	<div class="col-sm-3">
			        		<label id="tave_importePaletT1" class="error label label-danger"></label>
			        		<sf:input class="form-control text-right" path="tave_importePaletT1" value="${tarifaVenta.tave_importePaletT1}" />
			        	</div>
			        	<div class="col-sm-2">
			        		<label>€ / Palet</label>
			        	</div>
			        </div>
			        
			        <br>
			         <div class="row">	
			        	<div class="col-sm-4">
			        		<label>Trancha 2. Hasta</label>
			        	
							<label id="tave_numeroMaxPaletT2" class="error label label-danger"></label>
						</div>
						<div class="col-sm-2">
			        		<sf:input class="form-control text-right" path="tave_numeroMaxPaletT2" value="${tarifaVenta.tave_numeroMaxPaletT2}" />
			        	</div>
			        	<div class="col-sm-1" >
			        		<label>palets </label>
			        	</div>
			        	<div class="col-sm-3">
			        		<label id="tave_importePaletT2" class="error label label-danger"></label>
			        		<sf:input class="form-control text-right" path="tave_importePaletT2" value="${tarifaVenta.tave_importePaletT2}" />
			        	</div>
			        	<div class="col-sm-2">
			        		<label>€ / Palet</label>
			        	</div>
			        </div>
			        
			        <br>
			         <div class="row">	
			        	<div class="col-sm-4">
			        		<label>Trancha 3. Hasta</label>
			        	
							<label id="tave_numeroMaxPaletT3" class="error label label-danger"></label>
						</div>
						<div class="col-sm-2">
			        		<sf:input class="form-control text-right" path="tave_numeroMaxPaletT3" value="${tarifaVenta.tave_numeroMaxPaletT3}" />
			        	</div>
			        	<div class="col-sm-1" >
			        		<label>palets </label>
			        	</div>
			        	<div class="col-sm-3">
			        		<label id="tave_importePaletT3" class="error label label-danger"></label>
			        		<sf:input class="form-control text-right" path="tave_importePaletT3" value="${tarifaVenta.tave_importePaletT3}" />
			        	</div>
			        	<div class="col-sm-2">
			        		<label>€ / Palet</label>
			        	</div>
			        </div>
			        
			        <br>
			         <div class="row">	
			        	<div class="col-sm-4">
			        		<label>Trancha 4. Hasta</label>
			        	
							<label id="tave_numeroMaxPaletT4" class="error label label-danger"></label>
						</div>
						<div class="col-sm-2">
			        		<sf:input class="form-control text-right" path="tave_numeroMaxPaletT4" value="${tarifaVenta.tave_numeroMaxPaletT4}" />
			        	</div>
			        	<div class="col-sm-1" >
			        		<label>palets </label>
			        	</div>
			        	<div class="col-sm-3">
			        		<label id="tave_importePaletT4" class="error label label-danger"></label>
			        		<sf:input class="form-control text-right" path="tave_importePaletT4" value="${tarifaVenta.tave_importePaletT4}" />
			        	</div>
			        	<div class="col-sm-2">
			        		<label>€ / Palet</label>
			        	</div>
			        </div>
			        
			        <br>
			         <div class="row">	
			        	<div class="col-sm-4">
			        		<label>Trancha 5. Hasta</label>
			        	
							<label id="tave_numeroMaxPaletT5" class="error label label-danger"></label>
						</div>
						<div class="col-sm-2">
			        		<sf:input class="form-control text-right" path="tave_numeroMaxPaletT5" value="${tarifaVenta.tave_numeroMaxPaletT5}" />
			        	</div>
			        	<div class="col-sm-1" >
			        		<label>palets </label>
			        	</div>
			        	<div class="col-sm-3">
			        		<label id="tave_importePaletT5" class="error label label-danger"></label>
			        		<sf:input class="form-control text-right" path="tave_importePaletT5" value="${tarifaVenta.tave_importePaletT5}" />
			        	</div>
			        	<div class="col-sm-2">
			        		<label>€ / Palet</label>
			        	</div>
			        </div>
					
					<br>
		
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptar('tarifaVenta');">
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
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
    
    <script type="text/javascript">
    
	$(document).ready(function() {
		
		cargarTabla('tarifaVenta');
		
	});	
		
    </script>
    
</body>

</html>