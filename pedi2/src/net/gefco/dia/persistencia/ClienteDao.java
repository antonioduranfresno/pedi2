package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Cliente;

public interface ClienteDao {

	public void guardar(Cliente cliente);
	
	public void actualizar(Cliente cliente);
	
	public void eliminar(Cliente cliente);

	public List<Cliente> listado();
	
	public Cliente buscarCliente(Integer id);
	
}
