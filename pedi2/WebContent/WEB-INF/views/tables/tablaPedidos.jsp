<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="38%">Cliente</th>
			<th width="20%">Código</th>						
			<th width="30%">F. Creación</th>
			<th width="30%">Origen</th>
			<th width="30%">Destino</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaPedidos}" var="t" varStatus="index">
	
		<tr>
			<td>${t.cliente.clie_nombre}</td>
			<td>${t.pedi_codigo}</td>
			<td>${t.getPedi_fechaCreacionFormateada()}</td>
			<td class="fuente_minima">${t.nodoOrigen.nodo_direccion}</td>
			<td class="fuente_minima">${t.nodoDestino.nodo_direccion}</td>
			<td>${t.clno_codigo}</td>			
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'pedido');" 
				class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'pedido');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>