package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Nodo;

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
public class NodoDaoImpl implements NodoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Nodo nodo) {
		getSession().save(nodo);	
	}

	@Override
	public void actualizar(Nodo nodo) {
		getSession().update(nodo);
	}

	@Override
	public void eliminar(Nodo nodo) {
		getSession().delete(nodo);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Nodo> listado() {		
		
		Query query = getSession().createQuery("from Nodo");
		
		return query.list();
	}
	
	@Override
	public Nodo buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Nodo.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Nodo) crit.uniqueResult();
	}

}
