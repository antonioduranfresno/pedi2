package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.Pedido;
import net.gefco.dia.persistencia.PedidoDao;

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
	
	public List<Pedido> listado(){
		return pedidoDao.listado();
	}
	
	public Pedido buscar(Integer id){
		return pedidoDao.buscar(id);
	}
	
}