package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Proveedor;

public interface ProveedorDao {

	public void guardar(Proveedor proveedor);
	
	public void actualizar(Proveedor proveedor);
	
	public void eliminar(Proveedor proveedor);

	public List<Proveedor> listado();
	
	public Proveedor buscar(Integer id);

	
}
