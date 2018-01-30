package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Zona;

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
public class ZonaDaoImpl implements ZonaDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Zona zona) {
		getSession().save(zona);	
	}

	@Override
	public void actualizar(Zona zona) {
		getSession().update(zona);
	}

	@Override
	public void eliminar(Zona zona) {
		getSession().delete(zona);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Zona> listado() {		
		
		Query query = getSession().createQuery("from Zona");
		
		return query.list();
	}
	
	@Override
	public Zona buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Zona.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Zona) crit.uniqueResult();
	}

}
