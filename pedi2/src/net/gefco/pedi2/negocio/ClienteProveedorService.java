package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.ClienteProveedor;
import net.gefco.pedi2.persistencia.ClienteProveedorDao;

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