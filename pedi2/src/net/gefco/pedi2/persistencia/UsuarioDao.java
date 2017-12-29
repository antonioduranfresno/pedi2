package net.gefco.pedi2.persistencia;

import net.gefco.pedi2.modelo.Usuario;

public interface UsuarioDao {

	public void guardar(Usuario usuario);
	
	public void actualizar(Usuario usuario);
	
	public void eliminar(Usuario usuario);
	
	public Usuario buscarMatricula(String matricula);

}
