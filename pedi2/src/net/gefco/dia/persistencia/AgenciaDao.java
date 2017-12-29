package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Agencia;

public interface AgenciaDao {

	public void guardar(Agencia agencia);
	
	public void actualizar(Agencia agencia);
	
	public void eliminar(Agencia agencia);

	public List<Agencia> listado();
	
	public Agencia buscar(Integer id);

	
}
