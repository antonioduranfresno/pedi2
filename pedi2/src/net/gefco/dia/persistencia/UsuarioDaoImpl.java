package net.gefco.dia.persistencia;

import net.gefco.dia.modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UsuarioDaoImpl implements UsuarioDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Usuario usuario) {
		getSession().save(usuario);	
	}

	@Override
	public void actualizar(Usuario usuario) {
		getSession().update(usuario);
	}

	@Override
	public void eliminar(Usuario usuario) {
		getSession().delete(usuario);
	}

	@Override
	public Usuario buscarMatricula(String matricula) {

		Criteria crit = getSession().createCriteria(Usuario.class);
		
		crit.add(Restrictions.eq("usua_matricula", matricula));
		
		return (Usuario) crit.uniqueResult();
		
	}

}
