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
                    <h2 class="page-header mini">Cliente - Nodos                    	
                    	<a href="#" onclick="agregar(0,'clienteNodo');" class="btn btn-primary" title="Nuevo cliente - nodo"><span class="glyphicon glyphicon-plus"></span></a>
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
			<h3 class="modal-title">Cliente - Nodo</h3>        
	    </div>
	      
		    <div class="modal-body" >
		      
				<sf:form id="clienteNodoForm" method="post" modelAttribute="clienteNodo" >
						
					<sf:input type="hidden" path="id" value="${clienteNodo.id}" />
										
				   	<div class="row">
						<div class="col-sm-12">
	                  		<label>Cliente</label>	                  		
	                  		<label id="error_cliente" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="cliente" path="cliente.id" value="${clienteNodo.cliente.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaClientes}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == clienteNodo.cliente.id ? 'selected' : '' }>${c.clie_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>
			        </div>
			        <div class="row">
						<div class="col-sm-12">
	                  		<label>Nodo</label>	                  		
	                  		<label id="error_nodo" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="nodo" path="nodo.id" value="${clienteNodo.nodo.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaNodos}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == clienteNodo.nodo.id ? 'selected' : '' }>${c.proveedor.prov_nombre} - ${c.nodo_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>
					
				   	<div class="row">
			        	<div class="col-sm-12">
			        		<label>Código</label>
			        		<label id="clno_codigo" class="error label label-danger"></label>			        			   
			        		<sf:input class="form-control" path="clno_codigo" value="${clienteNodo.clno_codigo}" maxlength="45" />		        		
			        	</div>
					</div>
					
					<div class="row">
			        	<div class="col-sm-4">	        	
				        	 
				            <label>Usado para carga</label><br/> 
							<sf:checkbox class="form-control" data-group-cls="btn-group-justified" id="clno_nodoCarga" path="clno_nodoCarga" />				             
				       
					     </div>
			        	
			        	<div class="col-sm-4 pull-right">	        	
				        	 
				            <label>Usado para descarga</label><br/> 
							<sf:checkbox class="form-control" data-group-cls="btn-group-justified" id="clno_nodoDescarga" path="clno_nodoDescarga" />				             
				       
					     </div>
					    
					</div>
					
					<br>
		
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptar('clienteNodo');">
				      	</div>      	
				      	
				    </div>		
	
				</sf:form>
				
		    </div>

	    </div>
	
	  </div>
	  
	</div>    

    <script type="text/javascript" src='<c:url value="/res/js/jquery-1.10.2.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap-checkbox.min.js" />' ></script>
    <script type="text/javascript" src='<c:url value="/res/js/jquery.dataTables.js" />'></script>
	<script type="text/javascript" src='<c:url value="/res/js/dataTables.bootstrap.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
    
    <script type="text/javascript">
    
	$(document).ready(function() {
		
		cargarTabla('clienteNodo');
		
		$('#clno_nodoCarga').checkboxpicker({
            html: true,
            offLabel: '<span class="glyphicon glyphicon-remove">',
            onLabel: '<span class="glyphicon glyphicon-ok">'
    	});
		
		$('#clno_nodoDescarga').checkboxpicker({
            html: true,
            offLabel: '<span class="glyphicon glyphicon-remove">',
            onLabel: '<span class="glyphicon glyphicon-ok">'
    	});
		
	});	
		
    </script>
    
</body>

</html>