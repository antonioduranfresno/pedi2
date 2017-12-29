package net.gefco.dia.negocio;

import java.util.List;

import net.gefco.dia.modelo.Agencia;
import net.gefco.dia.persistencia.AgenciaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenciaService {

	@Autowired
	private AgenciaDao agenciaDao;

	public void guardar(Agencia agencia) {		
		agenciaDao.guardar(agencia);
	}

	public void actualizar(Agencia agencia) {		
		agenciaDao.actualizar(agencia);
	}	
	
	public void eliminar(Agencia agencia) {		
		agenciaDao.eliminar(agencia);
	}
	
	public List<Agencia> listado(){
		return agenciaDao.listado();
	}
	
	public Agencia buscar(Integer id){
		return agenciaDao.buscar(id);
	}
	
}