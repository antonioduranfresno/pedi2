package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.persistencia.ClienteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDao clienteDao;

	public void guardar(Cliente cliente) {		
		clienteDao.guardar(cliente);
	}

	public void actualizar(Cliente cliente) {		
		clienteDao.actualizar(cliente);
	}	
	
	public void eliminar(Cliente cliente) {		
		clienteDao.eliminar(cliente);
	}
	
	public List<Cliente> listado(){
		return clienteDao.listado();
	}
	
	public Cliente buscarCliente(Integer id){
		return clienteDao.buscarCliente(id);
	}
	
}