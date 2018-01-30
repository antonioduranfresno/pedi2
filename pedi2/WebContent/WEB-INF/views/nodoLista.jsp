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
                    <h2 class="page-header mini">Nodos
                    	<a href="#" onclick="agregar(0,'nodo');" class="btn btn-primary" title="Nuevo nodo"><span class="glyphicon glyphicon-plus"></span></a>
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
			<h3 class="modal-title">Nodo</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<sf:form id="nodoForm" method="post" modelAttribute="nodo">
						
					<sf:input type="hidden" path="id" value="${nodo.id}" />

					<div class="row">
						<div class="col-sm-12">
	                  		<label>Proveedor</label>	                  		
	                  		<label id="error_proveedor" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="proveedor" path="proveedor.id" value="${nodo.proveedor.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaProveedores}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == nodo.proveedor.id ? 'selected' : '' }>${c.prov_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>

					<div class="row">
			        	<div class="col-sm-12">
			        		<label>Nombre</label>
			        		<label id="nodo_nombre" class="error label label-danger"></label>								
			        		<sf:input class="form-control" path="nodo_nombre" value="${nodo.nodo_nombre}" maxlength="45" />		        			   
			        	</div>
					</div>	
					
					<div class="row">
			        	<div class="col-sm-12">
			        		<label>Dirección</label>
			        		<label id="nodo_direccion" class="error label label-danger"></label>								
			        		<sf:textarea rows="2" class="form-control" path="nodo_direccion" value="${nodo.nodo_direccion}" maxlength="255" ></sf:textarea>		        			   
			        	</div>
					</div>	

					<div class="row">
			        	<div class="col-sm-12">
			        		<label>Población</label>
			        		<label id="nodo_poblacion" class="error label label-danger"></label>								
			        		<sf:input class="form-control" path="nodo_poblacion" value="${nodo.nodo_poblacion}" maxlength="45" />			        			   
			        	</div>
					</div>
					
					<div class="row">
			        	<div class="col-sm-4">
			        		<label>Código Postal</label>
			        		<label id="nodo_codigoPostal" class="error label label-danger"></label>								
			        		<sf:input class="form-control" path="nodo_codigoPostal" value="${nodo.nodo_codigoPostal}" maxlength="45" />			        			   
			        	</div>
			        	<div class="col-sm-4">
			        		<label>Provincia</label>
			        		<label id="nodo_provincia" class="error label label-danger"></label>								
			        		<sf:input class="form-control" path="nodo_provincia" maxlength="255" />			        			   
			        	</div>
						<div class="col-sm-4">
	                  		<label>Pais</label>	                  		
	                  		<label id="error_pais" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="pais" path="pais.id" >
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaPaises}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == nodo.pais.id ? 'selected' : '' }>${c.toStringCodigo()}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>		

					<div class="row">
						<div class="col-sm-12">
	                  		<label>Zona</label>	                  		
	                  		<label id="error_zona" class="error label label-danger"></label>
	                  		<sf:select class="form-control" id="zona" path="zona.id" value="${nodo.zona.id}">
	                       		<option value="0">Selección</option>
	                          	<c:forEach items="${listaZonas}" var="c" varStatus="index">
	                          		<option value="${c.id}" ${c.id == nodo.zona.id ? 'selected' : '' }>${c.zona_nombre}</option>
	                   			</c:forEach>
	                   		</sf:select>
			        	</div>			        	
					</div>					
					
					<div class="row">
			        	<div class="col-sm-12">
			        		<label>Observaciones</label>
			        		<label id="nodo_observaciones" class="error label label-danger"></label>								
			        		<sf:textarea rows="2" class="form-control" path="nodo_observaciones" value="${nodo.nodo_observaciones}" maxlength="255" ></sf:textarea>		        			   
			        	</div>
					</div>		        	
					
					<br>
		
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptar('nodo');">
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
		
		cargarTabla('nodo');
		
	});	
		
    </script>
    
</body>

</html>