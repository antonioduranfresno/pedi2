<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url='${pageContext.servletContext.contextPath}/salir'">

      <nav class="navbar navbar-default navbar-static-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
            	<img src="res/img/bg/gefco.png"> 	
          	</a>            
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="pedidoLista">Pedidos</a></li>              
              
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Maestros <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="clienteLista">Clientes</a></li>                  
                  <li><a href="proveedorLista">Proveedores</a></li>
                  <li><a href="nodoLista">Nodos</a></li>
                   <li><a href="tarifaVentaLista">Tarifas venta</a></li>
                  <li class="divider"></li>                  
                  <li><a href="agenciaLista">Agencias</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Configuración <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="clienteProveedorLista">Cliente - Proveedores</a></li>
                  <li><a href="clienteNodoLista">Cliente - Nodos</a></li>
                </ul>
              </li>                                                                    
            </ul>
			<ul class="nav navbar-top-links navbar-right" >
				<li><a href="salir" ><i class="fa fa-sign-out fa-lg"></i></a></li>
            </ul>   
            <ul class="nav navbar-top-links navbar-right" >
				<li><a target="_blank" href="manual"><i class="fa fa-book fa-lg"></i></a></li>
			</ul>  
          </div>
        </div>
      </nav>
        
