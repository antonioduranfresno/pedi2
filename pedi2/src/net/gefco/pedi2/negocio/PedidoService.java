package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Pedido;
import net.gefco.pedi2.persistencia.PedidoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoDao pedidoDao;

	public void guardar(Pedido pedido) {		
		pedidoDao.guardar(pedido);
	}

	public void actualizar(Pedido pedido) {		
		pedidoDao.actualizar(pedido);
	}	
	
	public void eliminar(Pedido pedido) {		
		pedidoDao.eliminar(pedido);
	}
	
	public List<Pedido> listado(Agencia agencia){
		return pedidoDao.listado(agencia);
	}
	
	public Pedido buscar(Integer id){
		return pedidoDao.buscar(id);
	}
	
	public Integer obtenerUltimoGrupo(){
		return pedidoDao.obtenerUltimoGrupo();
	}
	
	public List<Pedido> listadoPedidosGrupo(Integer idGrupo){
		return pedidoDao.listadoPedidosGrupo(idGrupo);
	}
	
	public List<Pedido> listadoFiltro(Agencia agencia, String paramCliente, String paramNodoDestino, String paramFechaEntrega, String paramEstadoFactura){
		return pedidoDao.listadoFiltro(agencia, paramCliente, paramNodoDestino, paramFechaEntrega, paramEstadoFactura);
	}

	public List<Pedido> listadoPedidosFactura(Integer idFactura) {
		return pedidoDao.listadoPedidosFactura(idFactura);
	}
		
}