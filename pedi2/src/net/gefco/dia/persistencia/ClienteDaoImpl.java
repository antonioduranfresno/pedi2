package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Cliente;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ClienteDaoImpl implements ClienteDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Cliente cliente) {
		getSession().save(cliente);	
	}

	@Override
	public void actualizar(Cliente cliente) {
		getSession().update(cliente);
	}

	@Override
	public void eliminar(Cliente cliente) {
		getSession().delete(cliente);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> listado() {		
		
		Query query = getSession().createQuery("from Cliente");
		
		return query.list();
	}
	
	@Override
	public Cliente buscarCliente(Integer id) {
		
		Criteria crit = getSession().createCriteria(Cliente.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Cliente) crit.uniqueResult();
	}

}
