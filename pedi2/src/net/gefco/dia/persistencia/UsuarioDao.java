package net.gefco.dia.persistencia;

import net.gefco.dia.modelo.Usuario;

public interface UsuarioDao {

	public void guardar(Usuario usuario);
	
	public void actualizar(Usuario usuario);
	
	public void eliminar(Usuario usuario);
	
	public Usuario buscarMatricula(String matricula);

}
