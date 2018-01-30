package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.DetallePedido;
import net.gefco.pedi2.persistencia.DetallePedidoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {

	@Autowired
	private DetallePedidoDao detallePedidoDao;

	public void guardar(DetallePedido detallePedido) {		
		detallePedidoDao.guardar(detallePedido);
	}

	public void actualizar(DetallePedido detallePedido) {		
		detallePedidoDao.actualizar(detallePedido);
	}	
	
	public void eliminar(DetallePedido detallePedido) {		
		detallePedidoDao.eliminar(detallePedido);
	}
	
	public List<DetallePedido> listadoDetallePedido(Integer idPedido){
		return detallePedidoDao.listadoDetallePedido(idPedido);
	}
	
	public DetallePedido buscar(Integer id){
		return detallePedidoDao.buscar(id);
	}
	
	public Integer obtenerTotalPalets(Integer idPedido){
		return detallePedidoDao.obtenerTotalPalets(idPedido);
	}
	
}