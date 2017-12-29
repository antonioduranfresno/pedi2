package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Pedido;

public interface PedidoDao {

	public void guardar(Pedido pedido);
	
	public void actualizar(Pedido pedido);
	
	public void eliminar(Pedido pedido);

	public List<Pedido> listado();
	
	public Pedido buscar(Integer id);

	
}
