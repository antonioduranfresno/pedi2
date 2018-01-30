<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">			
			<th width="8%">Agencia</th>
			<th width="10%">Cliente</th>
			<th width="10%">Desde</th>
			<th width="10%">Hasta</th>
			<th width="20%">Origen</th>
			<th width="20%">Destino</th>			
			<th width="10%">Importe C.C.</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaTarifaVentas}" var="t" varStatus="index">
	
		<tr>
			<td>${t.agencia.agen_codigo}</td>
			<td class="fuente_minima">${t.cliente.clie_alias}</td>
			<td>${t.getTave_fechaDesdeFormateada()}</td>
			<td>${t.getTave_fechaHastaFormateada()}</td>
			<td class="fuente_minima">${t.nodoOrigen.nodo_nombre}</td>
			<td class="fuente_minima">${t.nodoDestino.nodo_nombre}</td>			
			<td class="text-right">${t.tave_importeCamionCompleto}</td>
			<td style="text-align: center;"><a href="#" onclick="asignaNodos(${t.id}, ${t.cliente.id});" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'tarifaVenta');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>