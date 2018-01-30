package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Pedido;

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
	public List<Pedido> listado(Agencia agencia) {		
		
		Query query = getSession().createQuery("from Pedido where agencia = :agencia");
		
		query.setParameter("agencia", agencia);
		
		return query.list();
	}
	
	@Override
	public Pedido buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Pedido.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Pedido) crit.uniqueResult();
	}
	
	//Obtener el último pedi_grupo de la tabla pedidos. No es necesario resetear.
	@Override
	public Integer obtenerUltimoGrupo(){
		
		Query query = getSession().createQuery("select max(pedi_grupo) from Pedido");
		
		return ((Integer) query.uniqueResult() != null ? (Integer) query.uniqueResult() : 0);
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> listadoPedidosGrupo(Integer idGrupo) {		
		
		Query query = getSession().createQuery("from Pedido where pedi_grupo = :idGrupo");
		
		query.setParameter("idGrupo", idGrupo);
		
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> listadoFiltro(Agencia agencia, String paramCliente, String paramNodoDestino, String paramFechaEntrega, String paramEstadoFactura) {		
		
		Query query = getSession().createQuery("from Pedido where agencia = :agencia and cliente.id "+paramCliente+" and nodoDestino.id "+paramNodoDestino +" and DATE(pedi_fechaEntrega) "+paramFechaEntrega + " "+ paramEstadoFactura);
		
		query.setParameter("agencia", agencia);
		
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pedido> listadoPedidosFactura(Integer idFactura) {
		
		Query query = getSession().createQuery("from Pedido where factura.id = :idFactura");
		
		query.setParameter("idFactura", idFactura);
		
		return query.list();
	}

}
