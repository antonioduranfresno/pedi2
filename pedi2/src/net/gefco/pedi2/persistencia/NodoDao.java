package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Nodo;

public interface NodoDao {

	public void guardar(Nodo nodo);
	
	public void actualizar(Nodo nodo);
	
	public void eliminar(Nodo nodo);

	public List<Nodo> listado();
	
	public Nodo buscar(Integer id);

	
}
