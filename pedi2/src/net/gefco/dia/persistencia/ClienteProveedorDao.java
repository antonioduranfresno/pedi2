package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.ClienteProveedor;

public interface ClienteProveedorDao {

	public void guardar(ClienteProveedor clienteProveedor);
	
	public void actualizar(ClienteProveedor clienteProveedor);
	
	public void eliminar(ClienteProveedor clienteProveedor);

	public List<ClienteProveedor> listado();
	
	public ClienteProveedor buscar(Integer id);

	
}
