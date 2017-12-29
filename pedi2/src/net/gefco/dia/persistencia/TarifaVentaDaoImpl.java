package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.TarifaVenta;

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
public class TarifaVentaDaoImpl implements TarifaVentaDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void guardar(TarifaVenta tarifaVenta) {
		getSession().save(tarifaVenta);	
	}

	@Override
	public void actualizar(TarifaVenta tarifaVenta) {
		getSession().update(tarifaVenta);
	}

	@Override
	public void eliminar(TarifaVenta tarifaVenta) {
		getSession().delete(tarifaVenta);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TarifaVenta> listado() {		
		
		Query query = getSession().createQuery("from TarifaVenta");
		
		return query.list();
	}
	
	@Override
	public TarifaVenta buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(TarifaVenta.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (TarifaVenta) crit.uniqueResult();
	}
	
	@Override
	public boolean solapada(TarifaVenta tarifaVenta) {

		String 			consulta = "" ;
		
		consulta = "from TarifaVenta tv where " + 
					"tv.agencia.id = :agencia " +
					"and tv.cliente.id = :cliente " +
					"and tv.nodoOrigen.id = :origen " +
					"and tv.nodoDestino.id = :destino " +
					"and ((tv.tave_fechaDesde >= :desde and tv.tave_fechaHasta <= :hasta) " +
					"or (tv.tave_fechaHasta >= :desde and tv.tave_fechaHasta <= :hasta) " +
					"or (tv.tave_fechaDesde <= :desde and tv.tave_fechaHasta >= :desde)) " +
					"and tv.id != :tarifaVenta";
		
		Query query = getSession().createQuery(consulta);
		
		query.setParameter("agencia", tarifaVenta.getAgencia().getId());
		
		query.setParameter("cliente", tarifaVenta.getCliente().getId());
		
		query.setParameter("origen", tarifaVenta.getNodoOrigen().getId());
		
		query.setParameter("destino", tarifaVenta.getNodoDestino().getId());
		
		query.setParameter("desde", tarifaVenta.getTave_fechaDesde());
		
		query.setParameter("hasta", tarifaVenta.getTave_fechaHasta());
		
		query.setParameter("tarifaVenta", tarifaVenta.getId());
		
		return (query.list().size() != 0);
		
	}

}
