package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Pais;

public interface PaisDao {

	public void guardar(Pais pais);
	
	public void actualizar(Pais pais);
	
	public void eliminar(Pais pais);

	public List<Pais> listado();
	
	public Pais buscar(Integer id);

	
}
