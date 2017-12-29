<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="11%">NIF</th>			
			<th width="25%">Nombre</th>
			<th width="57%">Observaciones</th>			
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaProveedores}" var="t" varStatus="index">
	
		<tr>
			<td>${t.prov_nif}</td>			
			<td>${t.prov_nombre}</td>
			<td>${t.prov_observaciones}</td>				
			<td style="text-align: center;"><a href="#" 
				onclick="agregar(${t.id},'proveedor');
						if (${t.prov_esPlataforma}) {marcar('prov_esPlataforma');} else {desmarcar('prov_esPlataforma');};" 
				class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'proveedor');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>