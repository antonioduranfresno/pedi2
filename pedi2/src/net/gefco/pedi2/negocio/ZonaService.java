package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Zona;
import net.gefco.pedi2.persistencia.ZonaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaService {

	@Autowired
	private ZonaDao zonaDao;

	public void guardar(Zona zona) {		
		zonaDao.guardar(zona);
	}

	public void actualizar(Zona zona) {		
		zonaDao.actualizar(zona);
	}	
	
	public void eliminar(Zona zona) {		
		zonaDao.eliminar(zona);
	}
	
	public List<Zona> listado(){
		return zonaDao.listado();
	}
	
	public Zona buscar(Integer id){
		return zonaDao.buscar(id);
	}
	
}