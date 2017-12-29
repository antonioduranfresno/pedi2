<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">			
			<th width="11%">Agencia</th>
			<th width="20%">Cliente</th>
			<th width="8%">Desde</th>
			<th width="8%">Hasta</th>
			<th width="15%">Origen</th>
			<th width="15%">Destino</th>
			<th width="3%">Camión Completo</th>
				
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaTarifaVentas}" var="t" varStatus="index">
	
		<tr>
			<td>${t.agencia.agen_nombre}</td>
			<td>${t.cliente.clie_nombre}</td>
			<td>${t.getTave_fechaDesdeFormateada()}</td>
			<td>${t.getTave_fechaHastaFormateada()}</td>
			<td>${t.nodoOrigen.nodo_direccion}</td>
			<td>${t.nodoDestino.nodo_direccion}</td>
			<td>${t.tave_importeCamionCompleto}</td>
						
			
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'tarifaVenta');" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'tarifaVenta');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>