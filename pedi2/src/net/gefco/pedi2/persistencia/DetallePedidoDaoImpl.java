package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.DetallePedido;

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
public class DetallePedidoDaoImpl implements DetallePedidoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(DetallePedido detallePedido) {
		getSession().save(detallePedido);	
	}

	@Override
	public void actualizar(DetallePedido detallePedido) {
		getSession().update(detallePedido);
	}

	@Override
	public void eliminar(DetallePedido detallePedido) {
		getSession().delete(detallePedido);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DetallePedido> listadoDetallePedido(Integer idPedido) {		
		
		Query query = getSession().createQuery("from DetallePedido where pedido.id = :idPedido");
		query.setParameter("idPedido", idPedido);
		
		return query.list();
	}
	
	@Override
	public DetallePedido buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(DetallePedido.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (DetallePedido) crit.uniqueResult();
	}
	
	@Override
	public Integer obtenerTotalPalets(Integer idPedido){
		
		Query query = getSession().createQuery("select sum(depe_numeroPalets) from DetallePedido where pedido.id = :idPedido group by pedido.id");
		query.setParameter("idPedido", idPedido);	
		
		return (query.list().size()>0 ? ((Long) query.list().get(0)).intValue() : 0);
	}

}
