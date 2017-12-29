<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="15%">NAD_BY</th>
			<th width="15%">NAD_PR</th>
			<th width="40%">NOMBRE</th>
			<th width="18%">NIF</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaClientes}" var="t" varStatus="index">
	
		<tr>
			<td>${t.clie_nadBy}</td>
			<td>${t.clie_nadPr}</td>
			<td>${t.clie_nombre}</td>
			<td>${t.clie_nif}</td>
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'cliente');" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'cliente');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>