<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='tabla' class='table table-hover table-striped table-condensed table-bordered'>

	<thead>
		<tr class="info">
			<th width="30%">Código</th>
			<th width="58%">Nombre</th>
			<th width="6%">Editar</th>
			<th width="6%">Borrar</th>
		</tr>
	</thead>
				
	<c:forEach items="${listaPaises}" var="t" varStatus="index">
	
		<tr>
			<td>${t.pais_codigo}</td>
			<td>${t.pais_nombre}</td>
			<td style="text-align: center;"><a href="#" onclick="agregar(${t.id},'pais');" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td style="text-align: center;"><a href="#" onclick="eliminar(${t.id},'pais');" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></a></td>
		</tr>
	
	</c:forEach>

</table>