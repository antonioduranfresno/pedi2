package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Zona;

public interface ZonaDao {

	public void guardar(Zona zona);
	
	public void actualizar(Zona zona);
	
	public void eliminar(Zona zona);

	public List<Zona> listado();
	
	public Zona buscar(Integer id);

	
}
