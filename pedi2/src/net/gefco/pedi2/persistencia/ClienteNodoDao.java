package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.modelo.ClienteNodo;
import net.gefco.pedi2.modelo.Nodo;

public interface ClienteNodoDao {

	public void guardar(ClienteNodo clienteNodo);
	
	public void actualizar(ClienteNodo clienteNodo);
	
	public void eliminar(ClienteNodo clienteNodo);

	public List<ClienteNodo> listado();
	
	public ClienteNodo buscar(Integer id);

	public List<Nodo> listadoOrigenesCliente(Cliente cliente);

	public List<Nodo> listadoDestinosCliente(Cliente cliente);

	public ClienteNodo devuelveClienteNodo(Cliente cliente, Nodo nodo);
	
}
