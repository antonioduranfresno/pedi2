package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.DetallePedido;

public interface DetallePedidoDao {

	public void guardar(DetallePedido detallePedido);
	
	public void actualizar(DetallePedido detallePedido);
	
	public void eliminar(DetallePedido detallePedido);

	public List<DetallePedido> listadoDetallePedido(Integer idPedido);
	
	public DetallePedido buscar(Integer id);

	public Integer obtenerTotalPalets(Integer idPedido);

	
}
