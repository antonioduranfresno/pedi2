package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.ClienteNodo;
import net.gefco.dia.persistencia.ClienteNodoDao;

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
	
}