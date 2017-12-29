<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="38%">Cliente</th>
			<th width="30%">Nodo</th>
			<th width="20%">Código</th>						
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaClienteNodos}" var="t" varStatus="index">
	
		<tr>
			<td>${t.cliente.clie_nombre}</td>
			<td class="fuente_minima">${t.nodo.nodo_direccion}</td>
			<td>${t.clno_codigo}</td>			
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'clienteNodo'); 
				if (${t.clno_nodoCarga}) {marcar('clno_nodoCarga');} else {desmarcar('clno_nodoCarga');};
				if (${t.clno_nodoDescarga}) {marcar('clno_nodoDescarga');} else {desmarcar('clno_nodoDescarga');};" 
				class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'clienteNodo');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>