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
                    <h2 class="page-header derecha">Pedidos
                    	<a href="exportarPedidos" class="btn btn-success"><span class="glyphicon glyphicon-export"></span> Exportar</a>
                    	<a href="#" onclick="agregar(0,'Pedido');" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Añadir</a>
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
    
	<div id="modalMaestra" class="modal fade" >
	
	  <div class="modal-dialog">
	
	    <div class="modal-content">
	    
	    <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3 class="modal-title">Pedido</h3>        
	    </div>
	      
		    <div class="modal-body" >
		      
				<sf:form id="pedidoForm" method="post" modelAttribute="pedido" >
						
					<sf:input type="hidden" path="id" value="${pedido.id}" />
										
				   	<div class="row">
						<div class="col-sm-12">
	                  		<label>Cliente</label>	                  		
	                  		<label id="error_cliente" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="cliente" path="cliente.id" value="${pedido.cliente.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaClientes}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == pedido.cliente.id ? 'selected' : '' }>${c.clie_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>
			        </div>
			        <div class="row">
						<div class="col-sm-12">
	                  		<label>Origen</label>	                  		
	                  		<label id="error_nodo" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodo" path="nodo.id" value="${pedido.nodoOrigen.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodosOrigen}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == pedido.nodoOrigen.id ? 'selected' : '' }>${c.nodo_direccion}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
					<div class="row">
						<div class="col-sm-12">
	                  		<label>Destino</label>	                  		
	                  		<label id="error_nodo" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodo" path="nodo.id" value="${pedido.nodoDestino.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodosDestino}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == pedido.nodoDestino.id ? 'selected' : '' }>${c.nodo_direccion}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
				   	<div class="row">
			        	<div class="col-sm-12">
			        		<label>Código</label>
			        		<label id="pedi_codigo" class="error label label-danger"></label>			        			   
			        		<sf:input class="form-control" path="clno_codigo" value="${pedido.pedi_codigo}" maxlength="45" />		        		
			        	</div>
					</div>
					
					<div class="row">
						<div class="col-sm-1 ">
				        	<label>Creación</label>
							<label id="pedi_fechaCreacion" class="error label label-danger"></label>
						</div>
						<div class="col-sm-3 date">
							<sf:input  class="form-control" id="pedi_fechaCreacion" path="pedi_fechaCrecion" />
														
								            		
				    	</div>
				    	<div class="col-sm-1 date"></div>
				    	<div class="col-sm-1 ">
				        	<label>Recogida</label>
							<label id="pedi_fechaRecogida" class="error label label-danger"></label>
						</div>
						<div class="col-sm-3 date">		
			                    <sf:input  class="form-control" id="pedi_fechaRecogida" path="pedi_fechaRecogida" />
							         		
				    	</div>
				    	<div class="col-sm-1 date"></div>
				    	<div class="col-sm-1 ">
				        	<label>Entrega</label>
							<label id="pedi_fechaEntrega" class="error label label-danger"></label>
						</div>
						<div class="col-sm-3 date">		
			                    <sf:input  class="form-control" id="pedi_fechaEntrega" path="pedi_fechaEntrega" />							         		
				    	</div>
				    </div>
					
					<br>
		
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptar('pedido');">
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
		
		cargarTabla('pedido');
		
	});	
		
    </script>
    
</body>

</html>