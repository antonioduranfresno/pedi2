<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">			
			<th width="22%">Proveedor</th>
			<th width="22%">Nombre</th>
			<th width="22%">Población</th>
			<th width="11%">Provincia</th>
			<th width="11%">Pais</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaNodos}" var="t" varStatus="index">
	
		<tr>
			<td>${t.proveedor.prov_nombre}</td>
			<td title='${t.nodo_direccion}'>${t.nodo_nombre}</td>
			<td>${t.nodo_poblacion}</td>
			<td>${t.nodo_provincia}</td>
			<td>${t.pais.pais_nombre}</td>
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'nodo');" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'nodo');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>