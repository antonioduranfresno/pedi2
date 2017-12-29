package net.gefco.pedi2.negocio;

import net.gefco.pedi2.modelo.Usuario;
import net.gefco.pedi2.persistencia.UsuarioDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	public void guardar(Usuario usuario) {		
		usuarioDao.guardar(usuario);
	}

	public void eliminar(Usuario usuario) {		
		usuarioDao.eliminar(usuario);
	}

	public Usuario buscarMatricula(String matricula){
		return usuarioDao.buscarMatricula(matricula);
	}
}