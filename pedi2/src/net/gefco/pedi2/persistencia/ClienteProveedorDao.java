package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.ClienteProveedor;

public interface ClienteProveedorDao {

	public void guardar(ClienteProveedor clienteProveedor);
	
	public void actualizar(ClienteProveedor clienteProveedor);
	
	public void eliminar(ClienteProveedor clienteProveedor);

	public List<ClienteProveedor> listado();
	
	public ClienteProveedor buscar(Integer id);

	
}
