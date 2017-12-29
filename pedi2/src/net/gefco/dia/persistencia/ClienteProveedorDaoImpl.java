package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.ClienteProveedor;

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
public class ClienteProveedorDaoImpl implements ClienteProveedorDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(ClienteProveedor clienteProveedor) {
		getSession().save(clienteProveedor);	
	}

	@Override
	public void actualizar(ClienteProveedor clienteProveedor) {
		getSession().update(clienteProveedor);
	}

	@Override
	public void eliminar(ClienteProveedor clienteProveedor) {
		getSession().delete(clienteProveedor);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClienteProveedor> listado() {		
		
		Query query = getSession().createQuery("from ClienteProveedor");
		
		return query.list();
	}
	
	@Override
	public ClienteProveedor buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(ClienteProveedor.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (ClienteProveedor) crit.uniqueResult();
	}

}
