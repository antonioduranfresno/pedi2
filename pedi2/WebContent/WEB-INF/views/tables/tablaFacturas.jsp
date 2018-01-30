<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="6%"></th>			
			<th width="9%">Número</th>
			<th width="10%">Fecha</th>
			<th width="8%">Cliente</th>
			<th width="19%">Destino</th>
			<th width="9%">Pedidos</th>
			<th width="9%">Base</th>
			<th width="9%">Cuota</th>
			<th width="9%">Total</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaFacturas}" var="t" varStatus="index">
	
		<tr>
			<td>
				<c:if test="${t.fact_estadoPrefactura < 2}">
					<input type="checkbox" id="${t.id}" />  
				</c:if>
				<i class="fa fa-check validado" style="${(empty t.fact_numeroFactura and t.fact_estadoPrefactura == 1 ?'' : 'display: none;')}"></i>
				<c:if test="${t.fact_estadoPrefactura == 2}">
					<i class="fa fa-warning rechazado"></i>
				</c:if>
			</td>
			<c:choose>
				<c:when test="${empty t.fact_numeroFactura}">
					<td>${t.fact_numeroPrefactura}</td>
					<td  data-order='${t.fact_fechaPrefactura}'>${t.getFact_fechaPrefacturaFormateada()}</td>	
				</c:when>
				<c:when test="${not empty t.fact_numeroFactura}">
					<td>${t.fact_numeroFactura}</td>
					<td data-order='${t.fact_fechaFactura}'>${t.getFact_fechaFacturaFormateada()}</td>	
				</c:when>
			</c:choose>
			<td>${t.cliente.clie_alias}</td>
			<td>${t.nodoDestino.nodo_nombre}</td>
			<td class="text-right">${t.fact_numeroPedidos}</td>
			<td class="text-right">${t.fact_baseImponible}</td>
			<td class="text-right">${t.fact_cuotaIva}</td>
			<td class="text-right">${t.fact_totalDocumento}</td>
			<td style="text-align: center;"><a href="#" onclick="agregarDocumento(${t.id},'factura');" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminarDocumento(${t.id});" class="btn btn-default" ${(not empty t.fact_numeroFactura ? 'disabled' : '')} ><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>

