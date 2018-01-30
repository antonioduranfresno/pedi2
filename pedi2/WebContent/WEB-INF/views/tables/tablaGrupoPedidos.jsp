<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="3%"></th>
			<th width="12%">F. Creaci�n</th>
			<th width="7%">Cliente</th>
			<th width="8%">C�digo</th>
			<th width="15%">Origen</th>
			<th width="15%">Destino</th>
			<th width="9%">F. Entrega</th>
			<th width="12%">Palets/Huecos</th>
			<th width="7%">Importe</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaPedidos}" var="t" varStatus="index">
	
		<tr>
			<td><input type="checkbox" id="${t.id}" checked="checked" disabled="disabled"/></td>
			<td data-order='${t.pedi_fechaCreacion}'>${t.getPedi_fechaCreacionFormateada()}</td>
			<td>${t.cliente.clie_alias}</td>
			<td>${t.pedi_codigo}</td>
			<td class="fuente_minima">${t.nodoOrigen.nodo_nombre}</td>
			<td class="fuente_minima">${t.nodoDestino.nodo_nombre}</td>
			<td data-order='${t.pedi_fechaEntrega}'>${t.getPedi_fechaEntregaFormateada()}</td>
			<td class="text-right">${t.pedi_totalPalets} / ${t.pedi_totalHuecos}</td>
			<td class="text-right">${t.pedi_importe}</td>
			<td style="text-align: center;">
				<a href="pedidoForm?idPedido=${t.id}"  class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a>	
			</td>							
			<td style="text-align: center;"><span class="glyphicon glyphicon-paperclip"></span></td>			
		</tr>
	
	</c:forEach>

</table>