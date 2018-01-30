package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Nodo;
import net.gefco.pedi2.persistencia.NodoDao;

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
	
	public Nodo buscar(Integer id){
		return nodoDao.buscar(id);
	}

	public List<Nodo> listadoPlataformas(){
		return nodoDao.listadoPlataformas();
	}
	
}