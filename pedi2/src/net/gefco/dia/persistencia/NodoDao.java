package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Nodo;

public interface NodoDao {

	public void guardar(Nodo nodo);
	
	public void actualizar(Nodo nodo);
	
	public void eliminar(Nodo nodo);

	public List<Nodo> listado();
	
	public Nodo buscar(Integer id);

	
}
