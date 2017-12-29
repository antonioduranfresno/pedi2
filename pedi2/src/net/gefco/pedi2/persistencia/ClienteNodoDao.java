package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.ClienteNodo;

public interface ClienteNodoDao {

	public void guardar(ClienteNodo clienteNodo);
	
	public void actualizar(ClienteNodo clienteNodo);
	
	public void eliminar(ClienteNodo clienteNodo);

	public List<ClienteNodo> listado();
	
	public ClienteNodo buscar(Integer id);

	
}
