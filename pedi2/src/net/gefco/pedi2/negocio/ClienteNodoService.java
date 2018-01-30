package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.modelo.ClienteNodo;
import net.gefco.pedi2.modelo.Nodo;
import net.gefco.pedi2.persistencia.ClienteNodoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteNodoService {

	@Autowired
	private ClienteNodoDao clienteNodoDao;

	public void guardar(ClienteNodo clienteNodo) {		
		clienteNodoDao.guardar(clienteNodo);
	}

	public void actualizar(ClienteNodo clienteNodo) {		
		clienteNodoDao.actualizar(clienteNodo);
	}	
	
	public void eliminar(ClienteNodo clienteNodo) {		
		clienteNodoDao.eliminar(clienteNodo);
	}
	
	public List<ClienteNodo> listado(){
		return clienteNodoDao.listado();
	}
	
	public ClienteNodo buscar(Integer id){
		return clienteNodoDao.buscar(id);
	}
	
	public List<Nodo> listadoOrigenesCliente(Cliente cliente){
		return clienteNodoDao.listadoOrigenesCliente(cliente);
	}
	
	public List<Nodo> listadoDestinosCliente(Cliente cliente){
		return clienteNodoDao.listadoDestinosCliente(cliente);
	}
	
	public ClienteNodo devuelveClienteNodo(Cliente cliente, Nodo nodo){
		return clienteNodoDao.devuelveClienteNodo(cliente, nodo);
	}
	
}