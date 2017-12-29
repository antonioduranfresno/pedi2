package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.ClienteProveedor;
import net.gefco.dia.persistencia.ClienteProveedorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteProveedorService {

	@Autowired
	private ClienteProveedorDao clienteProveedorDao;

	public void guardar(ClienteProveedor clienteProveedor) {		
		clienteProveedorDao.guardar(clienteProveedor);
	}

	public void actualizar(ClienteProveedor clienteProveedor) {		
		clienteProveedorDao.actualizar(clienteProveedor);
	}	
	
	public void eliminar(ClienteProveedor clienteProveedor) {		
		clienteProveedorDao.eliminar(clienteProveedor);
	}
	
	public List<ClienteProveedor> listado(){
		return clienteProveedorDao.listado();
	}
	
	public ClienteProveedor buscar(Integer id){
		return clienteProveedorDao.buscar(id);
	}
	
}