package net.gefco.dia.negocio;

import net.gefco.dia.modelo.Usuario;
import net.gefco.dia.persistencia.UsuarioDao;

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