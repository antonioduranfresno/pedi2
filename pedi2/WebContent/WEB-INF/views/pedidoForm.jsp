<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <div class="wrapper">

	  <jsp:include page="menu.jsp"/>
	  
	  <div id="page-wrapper">
        
          <div class="row">
               <div class="col-sm-12">
                   <h2 class="page-header mini">Pedido
	 			
		 			<c:if test="${not empty pedido.pedi_grupo}">
					    (agrupado)
                    	<a href="pedidoLista?idGrupo=${pedido.pedi_grupo}" class="btn btn-primary gefco-tooltip" data-toggle="tooltip" data-placement="auto" title="Ver grupo de pedidos" >
                    		<span class="glyphicon glyphicon-paperclip"></span> 
                    	</a>					    
					</c:if>                   
                   
                   </h2>
               </div>                                
          </div>

		  <sf:form id="pedidoForm" method="post" modelAttribute="pedido">
		  
		  <div id="pedido">

			<sf:input type="hidden" path="id" id="id" value="${pedido.id}" />			
			<sf:input type="hidden" path="agencia.id" id="idAgencia" value="${pedido.agencia.id}" />
			<sf:input type="hidden" path="pedi_fechaTasacion" value="${pedido.getPedi_fechaTasacionFormateada()}" />
			
			<div class="row">
        		<div class="col-sm-2">				
					<label>Agencia</label>				    
				    <sf:input class="form-control" id="agencia" path="agencia.agen_codigo" value="${pedido.agencia.agen_codigo}" />
				</div>
				<div class="col-sm-2">				
					<label>Nº Palets</label>
					<sf:errors path="pedi_totalPalets" class="label label-danger"></sf:errors>
				    <sf:input class="form-control text-right modificable" path="pedi_totalPalets" value="${pedido.pedi_totalPalets}" />
				</div>
				<div class="col-sm-2">				
					<label>Nº Huecos</label>
					<sf:errors path="pedi_totalHuecos" class="label label-danger"></sf:errors>
				    <sf:input class="form-control text-right modificable" path="pedi_totalHuecos" value="${pedido.pedi_totalHuecos}" />
				</div>
				<div class="col-sm-2">				
					<label>Importe</label>
				    <sf:input class="form-control text-right" path="pedi_importe" value="${pedido.pedi_importe}"  />
				</div>
	        	<div class="col-sm-2">
	        		<label>Nº Prefactura</label>
	        		<div class='input-group'>
	        			<input type="text" class="form-control" id="numeroPrefactura" value="${pedido.factura.fact_numeroPrefactura}" disabled/>
	        			<span class="input-group-addon sacar" id="btn_desvincular" title="Sacar de la prefactura" onclick="desvincular();">							
							<i class="fa fa-sign-out"></i>									      
						</span>
	        		</div>						        	
	        	</div>
	        	<div class="col-sm-2">	        		
					<label>Nº Factura</label>
	        		<input type="text" class="form-control" id="numeroFactura" value="${pedido.factura.fact_numeroFactura}" disabled/>	        	
	        	</div>			        			   			    			 
			</div>
			
			<br>
					
			<div class="row">

	        	<div class="col-sm-6">
	        		<label>Cliente</label>
	        		<sf:errors path="cliente.id" class="label label-danger"></sf:errors>
					<sf:select class="form-control modificable" id="selCliente" path="cliente.id" value="${pedido.cliente.id}" >
						<option value="0">Selección</option>
						<c:forEach items="${listaClientes}" var="c" varStatus="index">
		        			<option value="${c.id}" ${c.id == pedido.cliente.id ? 'selected' : '' }>${c.clie_nombre}</option>
		        		</c:forEach>
					</sf:select>		        		
	        	</div>	        	
        		<div class="col-sm-2">				
					<label>Tipo</label>				    
				    <input class="form-control" id="tipo" disabled="disabled" value="${tipo}"/>
				</div>	       	
	        	<div class="col-sm-2">	        		
					<label>Anulado</label>
				    <sf:checkbox class="form-control" data-group-cls="btn-group-justified"  id="pedi_anulado" path="pedi_anulado" />
				</div>
				<div class="col-sm-2">
					<label>Incidencia</label>
				    <sf:checkbox class="form-control" data-group-cls="btn-group-justified"  id="pedi_incidencia" path="pedi_incidencia" />	        	
	        	</div>	       	
        	</div>
        	
        	<br>
        	
			<div class="row">

	        	<div class="col-sm-6">
	        		<label>Origen</label>
	        		<sf:errors path="nodoOrigen.id" class="label label-danger"></sf:errors>
					<sf:select class="form-control modificable" id="selNodoOrigen" path="nodoOrigen.id" value="${pedido.nodoOrigen.id}" >
						<option value="0">Selección</option>
						<c:forEach items="${listaNodosOrigen}" var="c" varStatus="index">
		        			<option value="${c.id}" ${c.id == pedido.nodoOrigen.id ? 'selected' : '' }>${c.toStringCodigo()}</option>
		        		</c:forEach>
					</sf:select>		        		
	        	</div>

				<div class="col-sm-6">
	        		<label>Destino</label>
	        		<sf:errors path="nodoDestino.id" class="label label-danger"></sf:errors>
					<sf:select class="form-control modificable" id="selNodoDestino" path="nodoDestino.id" value="${pedido.nodoDestino.id}" >
						<option value="0">Selección</option>
						<c:forEach items="${listaNodosDestino}" var="c" varStatus="index">
		        			<option value="${c.id}" ${c.id == pedido.nodoDestino.id ? 'selected' : '' }>${c.toStringCodigo()}</option>
		        		</c:forEach>
					</sf:select>		        		
	        	</div>
        	
        	</div>        	
        	
        	<br>
        	
        	<div class="row">
        	
        		<div class="col-sm-2">				
					<label>Código</label>
				    <sf:errors path="pedi_codigo" class="label label-danger"></sf:errors>
				    <sf:input class="form-control modificable" path="pedi_codigo" value="${pedido.pedi_codigo}" maxlength="45" />
				</div>

        		<div class="col-sm-2">				
					<label>Referencia</label>
				    <sf:errors path="pedi_referencia" class="label label-danger"></sf:errors>
				    <sf:input class="form-control modificable" path="pedi_referencia" value="${pedido.pedi_referencia}" maxlength="45" />
				</div>
				
        		<div class="col-sm-2">				
					<label>Matrícula</label>
				    <sf:errors path="pedi_matricula" class="label label-danger"></sf:errors>
				    <sf:input class="form-control modificable" path="pedi_matricula" value="${pedido.pedi_matricula}" maxlength="20" />
				</div>				 	        	
        	
	        	<div class="col-sm-2 date">
		        	<label>Fecha Creación</label>
					<sf:errors path="pedi_fechaCreacion" class="label label-danger"></sf:errors>
					<div class='input-group date' id='divFechaCreacion'>							
	                    <sf:input class="form-control modificable" id="pedi_fechaCreacion" path="pedi_fechaCreacion" value="${pedido.getPedi_fechaCreacionFormateada()}" />
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>									      
						</span>									
            		</div>	            		
		    	</div>	
		    	
	        	<div class="col-sm-2 date">
		        	<label>Fecha Recogida</label>
					<sf:errors path="pedi_fechaRecogida" class="label label-danger"></sf:errors>
					<div class='input-group date' id='divFechaRecogida'>							
	                    <sf:input class="form-control modificable" id="pedi_fechaRecogida" path="pedi_fechaRecogida" value="${pedido.getPedi_fechaRecogidaFormateada()}" />
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>									      
						</span>									
            		</div>	            		
		    	</div>	
		    	
	        	<div class="col-sm-2 date">
		        	<label>Fecha Entrega</label>
					<sf:errors path="pedi_fechaEntrega" class="label label-danger"></sf:errors>
					<div class='input-group date' id='divFechaEntrega'>							
	                    <sf:input class="form-control modificable" id="pedi_fechaEntrega" path="pedi_fechaEntrega" value="${pedido.getPedi_fechaEntregaFormateada()}" />
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>									      
						</span>									
            		</div>	             		
		    	</div>			    			    	
				
        	</div>
        	
        	<br>
        	
        	<div class="row">       	
	        	<div class="col-sm-12">
	        		<label>Observaciones</label>
	        		<label id="pedi_observaciones" class="error label label-danger"></label>								
	        		<sf:textarea rows="4" class="form-control modificable" path="pedi_observaciones" value="${pedido.pedi_observaciones}" maxlength="255" ></sf:textarea>		        			   
	        	</div>    	
        	</div>
        	
        	<sf:input type="hidden" path="factura.id" id="idFactura" value="${pedido.factura.id}" />
        	<sf:input type="hidden" path="pedi_grupo" id="idGrupo" value="${pedido.pedi_grupo}" />
        	
        	<sf:input type="hidden" path="pedi_fechaActualizacion" value="${pedido.getPedi_fechaActualizacionFormateada()}" />
        	<sf:input type="hidden" path="pedi_usuarioActualizacion"  value="${pedido.pedi_usuarioActualizacion}" />
        	
        	<br>		
        			
			<div class="footer">
			 	<div class="col-sm-4 izquierda">		      		
		      		<input type="button" class="btn btn-primary" id="btnEliminar" value="Eliminar pedido" onclick='eliminar($("#id").val(),"pedido");' >
		      	</div>
			 	<div class="col-sm-8 derecha">
		      		<input type="submit" class="btn btn-primary" id="btnAceptar" value="Aceptar" >		      		
		      	</div> 		      			      	
		    </div>
	
		  </div>
	
		  </sf:form>

      </div>

    </div>

    <script type="text/javascript" src='<c:url value="/res/js/jquery-1.10.2.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap-checkbox.min.js" />' ></script>
	<script type="text/javascript" src='<c:url value="/res/js/bootstrap-datetimepicker.min.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/locales/bootstrap-datetimepicker.es.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/pedido.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/maestro.js" />'></script>
    <script type="text/javascript" src='<c:url value="/res/js/loading.js" />'></script>
	
  </body>

</html>