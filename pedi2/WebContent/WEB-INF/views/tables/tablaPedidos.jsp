<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="6%"></th>
			<th width="12%">F. Creación</th>
			<th width="6%">Cliente</th>
			<th width="8%">Código</th>
			<th width="14%">Origen</th>
			<th width="14%">Destino</th>
			<th width="9%">F. Entrega</th>
			<th width="12%">Palets/Huecos</th>
			<th width="7%">Importe</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaPedidos}" var="t" varStatus="index">
	
		<tr>
			<td>
				<input type="checkbox" id="${t.id}" class="${(empty t.factura ? '' : 'invisible')}" />				
				<label class="${(not empty t.factura ? '' : 'invisible')}" title='Nº ${empty t.factura.fact_numeroFactura ? t.factura.fact_numeroPrefactura : t.factura.fact_numeroFactura}'>					
					<i class="fa fa-money" ${empty t.factura.fact_numeroFactura ? 'style="font-size:30px; color: orange;"' : 'style="font-size:30px; color: green;"'} ></i>
				</label>
				<label id='etiquetaIncidencia' class="${(t.pedi_incidencia == true ? '' : 'invisible')}">!</label>
				<span id='etiquetaGrupo' class="${(not empty t.pedi_grupo ? 'glyphicon glyphicon-paperclip' : 'invisible')}" ></span>
			</td>
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
			<td style="text-align: center;">
				<a href="#" onclick="eliminar(${t.id},'pedido');" class="btn btn-default" ${(not empty t.pedi_grupo || not empty t.factura ? 'disabled' : '' )} ><span class="glyphicon glyphicon-trash"></span></a>
			</td>			
		</tr>
	
	</c:forEach>

</table>