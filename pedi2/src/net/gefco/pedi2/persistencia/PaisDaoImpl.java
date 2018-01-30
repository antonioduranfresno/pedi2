package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Pais;

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
public class PaisDaoImpl implements PaisDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Pais pais) {
		getSession().save(pais);	
	}

	@Override
	public void actualizar(Pais pais) {
		getSession().update(pais);
	}

	@Override
	public void eliminar(Pais pais) {
		getSession().delete(pais);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pais> listado() {		
		
		Query query = getSession().createQuery("from Pais order by pais_codigo");
		
		return query.list();
	}
	
	@Override
	public Pais buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Pais.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Pais) crit.uniqueResult();
	}

}
