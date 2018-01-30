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
                    <h2 class="page-header mini">Paises
                    	<a href="#" onclick="agregar(0,'pais');" class="btn btn-primary" title="Nueva pais"><span class="glyphicon glyphicon-plus"></span></a>
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
			<h3 class="modal-title">Pais</h3>        
	    </div>
	      
		    <div class="modal-body">
		      
				<sf:form id="paisForm" method="post" modelAttribute="pais">
						
					<sf:input type="hidden" path="id" />
										
				   	<div class="row">
			        	<div class="col-sm-6">
			        		<label>Código</label>			        				
			        		<label id="pais_codigo" class="error label label-danger"></label>	        		       		
			        		<sf:input class="form-control" path="pais_codigo" maxlength="15" />		        		
			        	</div>
					</div>
					
				   	<div class="row">
			        	<div class="col-sm-12">
			        		<label>Nombre</label>
			        		<label id="pais_nombre" class="error label label-danger"></label>			        			   
			        		<sf:input class="form-control" path="pais_nombre" maxlength="45" />		        		
			        	</div>
					</div>
					
					<br>
		
					<div class="footer">      
					      	
					 	<div class="col-sm-12 derecha">			      		
				      		<input type="button" class="btn btn-primary" value="Aceptar" onclick="aceptar('pais');">
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
		
		cargarTabla('pais');
		
	});	
		
    </script>
    
</body>

</html>