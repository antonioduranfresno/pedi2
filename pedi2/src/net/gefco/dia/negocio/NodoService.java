package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.Nodo;
import net.gefco.dia.persistencia.NodoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodoService {

	@Autowired
	private NodoDao nodoDao;

	public void guardar(Nodo nodo) {		
		nodoDao.guardar(nodo);
	}

	public void actualizar(Nodo nodo) {		
		nodoDao.actualizar(nodo);
	}	
	
	public void eliminar(Nodo nodo) {		
		nodoDao.eliminar(nodo);
	}
	
	public List<Nodo> listado(){
		return nodoDao.listado();
	}
	
	public List<Nodo> listadoOrigenes(){
		return nodoDao.listado();
	}
	
	public List<Nodo> listadoDestinos(){
		return nodoDao.listado();
	}
	
	public Nodo buscar(Integer id){
		return nodoDao.buscar(id);
	}
	
}