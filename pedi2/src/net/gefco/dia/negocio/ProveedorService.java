package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.Proveedor;
import net.gefco.dia.persistencia.ProveedorDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorDao proveedorDao;

	public void guardar(Proveedor proveedor) {		
		proveedorDao.guardar(proveedor);
	}

	public void actualizar(Proveedor proveedor) {		
		proveedorDao.actualizar(proveedor);
	}	
	
	public void eliminar(Proveedor proveedor) {		
		proveedorDao.eliminar(proveedor);
	}
	
	public List<Proveedor> listado(){
		return proveedorDao.listado();
	}
	
	public Proveedor buscar(Integer id){
		return proveedorDao.buscar(id);
	}
	
}