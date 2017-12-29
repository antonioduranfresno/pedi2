package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Proveedor;

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
public class ProveedorDaoImpl implements ProveedorDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Proveedor proveedor) {
		getSession().save(proveedor);	
	}

	@Override
	public void actualizar(Proveedor proveedor) {
		getSession().update(proveedor);
	}

	@Override
	public void eliminar(Proveedor proveedor) {
		getSession().delete(proveedor);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Proveedor> listado() {		
		
		Query query = getSession().createQuery("from Proveedor");
		
		return query.list();
	}
	
	@Override
	public Proveedor buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Proveedor.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Proveedor) crit.uniqueResult();
	}

}
