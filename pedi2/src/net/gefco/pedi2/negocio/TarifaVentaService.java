package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.TarifaVenta;
import net.gefco.pedi2.persistencia.TarifaVentaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarifaVentaService {

	@Autowired
	private TarifaVentaDao tarifaVentaDao;

	public void guardar(TarifaVenta tarifaVenta) {		
		tarifaVentaDao.guardar(tarifaVenta);
	}

	public void actualizar(TarifaVenta tarifaVenta) {		
		tarifaVentaDao.actualizar(tarifaVenta);
	}	
	
	public void eliminar(TarifaVenta tarifaVenta) {		
		tarifaVentaDao.eliminar(tarifaVenta);
	}
	
	public List<TarifaVenta> listado(){
		return tarifaVentaDao.listado();
	}
	
	public TarifaVenta buscar(Integer id){
		return tarifaVentaDao.buscar(id);
	}

	public boolean solapada(TarifaVenta tarifaVenta) {
		return tarifaVentaDao.solapada(tarifaVenta);
	}
	
}