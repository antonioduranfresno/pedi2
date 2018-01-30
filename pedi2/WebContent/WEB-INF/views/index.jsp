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

  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
    
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">PEDI2 <small>v1.0</small></a>
          
        </div>
        
        	<div id="navbar" class="navbar-collapse collapse">

				<form class="navbar-form navbar-right">

					<div class="form-group">
						<input type="text" id="login" name="login" placeholder="Matrícula Gefco" class="form-control">
					</div>
					<div class="form-group">
						<input type="password" id="password" name="password" placeholder="Contraseña" class="form-control">
					</div>

					<button type="button" id="btnAcceso" class="btn btn-success" 
						data-loading-text="<i class='fa fa-circle-o-notch fa-spin'></i> Espere...">Acceder</button>
						
				</form>

			</div>
			
      </div>
    </nav>

    <div class="jumbotron">

      	<div class="container">

	        <div class="imagenFondo">
	                
	        </div>
	    
    	</div>
    	
    </div>
    
    <div class="container">

      <hr>

      <footer>
        <p>&copy; 2018 Gefco España S.A. - Departamento Informática
						
	  		<a href="#" id="noRegistrado" class="pull-right">¿No está registrado?</a>        	
	        
	    </p>    
      </footer>
    </div> 

	<div id="modalRegistro" class="modal fade">
	  <div class="modal-dialog">
	
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Nuevo Registro</h4>        
	      </div>
	      <div class="modal-body" >
			 <form id="formularioRegistro" autocomplete="off" >			
			   	<div class="row">
		        	<div class="col-sm-4">
		        		<label>Matrícula GEFCO</label>
		        		<input class="form-control" type="text" id="reg_matricula" name="reg_matricula" maxlength="7"  />
		        	</div>
		        	<div class="col-sm-8">
			        	<label>Código Centro</label>			        	     
			        	<select class="form-control" id="selAgenciaEmpleado" >
			        			<option value="0">Selección</option>
			        		<c:forEach items="${listaAgencias}" var="c" varStatus="index">
			        			<option value="${c.id}">${c.toStringCodigo()}</option>			        		
			        		</c:forEach>
			        	</select>   		
			        </div>		        			        	

			    </div>
			    <br/>
	        	<div class="row">
		        	<div class="col-sm-12">
	        			<label>Correo electrónico</label>
	        			<input class="form-control" type="email" id="reg_email" name="reg_email" maxlength="45"  />
			        </div>
				</div>			    
			    <br/>
	        	<div class="row">
					<div class="col-sm-12">
	        			<label>Nombre y apellidos</label>
	        			<input class="form-control" type="text" id="reg_nombre" name="reg_nombre" maxlength="85"  />
	        		</div>
				</div>
			    <br/>
			    <div class="row">
	        		<div class="col-sm-12">   		
		        		<label>Contraseña</label>
		        		<input class="form-control" type="password" id="reg_password" name="reg_password"  />
	        		</div>
	        	</div>
	        	<br/>
			    <div class="row">
	        		<div class="col-sm-12">   		
		        		<label>Confirmar Contraseña</label>
		        		<input class="form-control" type="password" id="reg_password2" name="reg_password2"  />
	        		</div>
	        	</div>	        	
	          </form>	        
	      </div>
	      <div class="modal-footer">
	      
	      	<button type="button" id="btnNuevoRegistro" class="btn btn-primary" 
		    	data-loading-text="<i class='fa fa-circle-o-notch fa-spin'></i> Espere...">Aceptar</button>
	      	
	      </div>
	    </div>
	
	  </div>
	</div>  

	<script type="text/javascript" src='<c:url value="/res/js/jquery-1.10.2.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/acceso.js" />' charset="UTF-8"></script>
    
  </body>
</html>