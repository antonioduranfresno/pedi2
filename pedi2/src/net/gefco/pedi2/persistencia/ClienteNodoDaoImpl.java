package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.ClienteNodo;

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
public class ClienteNodoDaoImpl implements ClienteNodoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(ClienteNodo clienteNodo) {
		getSession().save(clienteNodo);	
	}

	@Override
	public void actualizar(ClienteNodo clienteNodo) {
		getSession().update(clienteNodo);
	}

	@Override
	public void eliminar(ClienteNodo clienteNodo) {
		getSession().delete(clienteNodo);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClienteNodo> listado() {		
		
		Query query = getSession().createQuery("from ClienteNodo");
		
		return query.list();
	}
	
	@Override
	public ClienteNodo buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(ClienteNodo.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (ClienteNodo) crit.uniqueResult();
	}

}
