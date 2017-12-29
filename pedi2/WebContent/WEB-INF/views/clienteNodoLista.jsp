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
                    <h2 class="page-header derecha">Cliente - Nodos
                    	<a href="exportarClienteNodo" class="btn btn-success"><span class="glyphicon glyphicon-export"></span> Exportar</a>
                    	<a href="#" onclick="agregar(0,'clienteNodo');" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Añadir</a>
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
	                          		<option value="${c.id}" ${c.id == clienteNodo.nodo.id ? 'selected' : '' }>${c.nodo_direccion}</option>
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
			        	<div class="col-sm-6 form-group">	        	
				        	
				            <sf:checkbox path="clno_nodoCarga"  id="clno_nodoCarga" name="clno_nodoCarga" cssStyle="display:none"  
				            	onchange="if (this.checked) {marcar(this.id);} else {desmarcar(this.id);}; " />
				             

				            <div class="btn-group">
				                <label for="clno_nodoCarga" class="btn btn-success">
				                    <span class="glyphicon glyphicon-ok" id="clno_nodoCargaSpan1" style="width: 20px;"></span>
				                    <span id="clno_nodoCargaSpan2" style="width: 20px;"> </span>
				                </label>
				                <label for="clno_nodoCarga" class="btn btn-default active"> Usado para carga </label>
				            </div>
				       
					     </div>
			        	
						<div class="col-sm-6 form-group">	        	
				        	
				            <sf:checkbox path="clno_nodoDescarga"  id="clno_nodoDescarga" name="clno_nodoDesarga" cssStyle="display:none"  
				            	onchange="if (this.checked) {marcar(this.id);} else {desmarcar(this.id);}; " />				             

				            <div class="btn-group">
				                <label for="clno_nodoDescarga" class="btn btn-success">
				                    <span class="glyphicon glyphicon-ok" id="clno_nodoDescargaSpan1" style="width: 20px;"></span>
				                    <span id="clno_nodoDescargaSpan2" style="width: 20px;"> </span>
				                </label>
				                <label for="clno_nodoDescarga" class="btn btn-default active"> Usado para descarga </label>
				            </div>
				       
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
    <script type="text/javascript" src='<c:url value="/res/js/jquery.dataTables.js" />'></script>
	<script type="text/javascript" src='<c:url value="/res/js/dataTables.bootstrap.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
    
    <script type="text/javascript">
    
	$(document).ready(function() {
		
		cargarTabla('clienteNodo');
		
	});	
		
    </script>
    
</body>

</html>