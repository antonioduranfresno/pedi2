package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Pedido;

public interface PedidoDao {

	public void guardar(Pedido pedido);
	
	public void actualizar(Pedido pedido);
	
	public void eliminar(Pedido pedido);

	public List<Pedido> listado();
	
	public Pedido buscar(Integer id);

	
}
