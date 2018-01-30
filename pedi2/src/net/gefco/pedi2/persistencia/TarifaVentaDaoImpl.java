package net.gefco.pedi2.persistencia;

import java.util.Date;
import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.TarifaVenta;

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
	public List<TarifaVenta> listado(Agencia agencia) {		
		
		Query query = getSession().createQuery("from TarifaVenta where agencia = :agencia");
		
		query.setParameter("agencia", agencia);
		
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
	
	@Override
	public TarifaVenta obtenerTarifa(Integer idAgencia, Integer idCliente, Integer idNodoOrigen, Integer idNodoDestino, Date fecha) {
		
		String 			consulta = "" ;
		
		consulta = "from TarifaVenta tv where " + 
					"tv.agencia.id = :idAgencia " +
					"and tv.cliente.id = :idCliente " +
					"and tv.nodoOrigen.id = :idNodoOrigen " +
					"and tv.nodoDestino.id = :idNodoDestino " +
					"and (tv.tave_fechaDesde <= :fecha and tv.tave_fechaHasta >= :fecha)";
		
		Query query = getSession().createQuery(consulta);
		
		query.setParameter("idAgencia", idAgencia);
		
		query.setParameter("idCliente", idCliente);
		
		query.setParameter("idNodoOrigen", idNodoOrigen);
		
		query.setParameter("idNodoDestino", idNodoDestino);
		
		query.setParameter("fecha", fecha);
		
		return (query.list().size()>0 ? (TarifaVenta) query.list().get(0) : null);
		
	}

}
