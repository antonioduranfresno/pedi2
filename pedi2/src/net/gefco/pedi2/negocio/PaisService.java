package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Pais;
import net.gefco.pedi2.persistencia.PaisDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

	@Autowired
	private PaisDao paisDao;

	public void guardar(Pais pais) {		
		paisDao.guardar(pais);
	}

	public void actualizar(Pais pais) {		
		paisDao.actualizar(pais);
	}	
	
	public void eliminar(Pais pais) {		
		paisDao.eliminar(pais);
	}
	
	public List<Pais> listado(){
		return paisDao.listado();
	}
	
	public Pais buscar(Integer id){
		return paisDao.buscar(id);
	}
	
}