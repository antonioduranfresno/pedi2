package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Pedido;

public interface PedidoDao {

	public void guardar(Pedido pedido);
	
	public void actualizar(Pedido pedido);
	
	public void eliminar(Pedido pedido);

	public List<Pedido> listado(Agencia agencia);
	
	public Pedido buscar(Integer id);

	public Integer obtenerUltimoGrupo();

	public List<Pedido> listadoPedidosGrupo(Integer idGrupo);

	public List<Pedido> listadoFiltro(Agencia agencia, String paramCliente, String paramNodoDestino, String paramFechaEntrega, String paramEstadoFactura);

	public List<Pedido> listadoPedidosFactura(Integer idFactura);

}
