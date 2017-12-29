package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.Pedido;

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
public class PedidoDaoImpl implements PedidoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(Pedido pedido) {
		getSession().save(pedido);	
	}

	@Override
	public void actualizar(Pedido pedido) {
		getSession().update(pedido);
	}

	@Override
	public void eliminar(Pedido pedido) {
		getSession().delete(pedido);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> listado() {		
		
		Query query = getSession().createQuery("from Pedido");
		
		return query.list();
	}
	
	@Override
	public Pedido buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Pedido.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Pedido) crit.uniqueResult();
	}

}
