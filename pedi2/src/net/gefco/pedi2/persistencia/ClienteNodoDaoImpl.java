package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.modelo.ClienteNodo;
import net.gefco.pedi2.modelo.Nodo;

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
		
		Query query = getSession().createQuery("from ClienteNodo c order by c.cliente, c.nodo.proveedor.prov_nombre, c.nodo.nodo_nombre");
		
		return query.list();
	}
	
	@Override
	public ClienteNodo buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(ClienteNodo.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (ClienteNodo) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nodo> listadoOrigenesCliente(Cliente cliente) {

		Query query = getSession().createQuery("select c.nodo from ClienteNodo c where c.cliente = :cliente and c.clno_nodoCarga = true");
		
		query.setParameter("cliente", cliente);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nodo> listadoDestinosCliente(Cliente cliente) {
		
		Query query = getSession().createQuery("select c.nodo from ClienteNodo c where c.cliente = :cliente and c.clno_nodoDescarga = true");
		
		query.setParameter("cliente", cliente);
		
		return query.list();
	}

	@Override
	public ClienteNodo devuelveClienteNodo(Cliente cliente, Nodo nodo){
		
		Query query = getSession().createQuery("from ClienteNodo where cliente = :cliente and nodo = :nodo");
		
		query.setParameter("cliente", cliente);
		query.setParameter("nodo", nodo);
		
		return (ClienteNodo) query.uniqueResult();
	}
	
}
