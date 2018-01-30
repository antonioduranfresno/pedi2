package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Factura;
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
public class FacturaDaoImpl implements FacturaDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Integer guardar(Factura factura) {
		return (Integer) getSession().save(factura);	
	}

	@Override
	public void actualizar(Factura factura) {
		getSession().update(factura);
	}

	@Override
	public void eliminar(Factura factura) {
		getSession().delete(factura);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Factura> listadoPrefacturas(Agencia agencia) {		
		
		Query query = getSession().createQuery("from Factura where agencia = :agencia and fact_numeroFactura = null order by fact_fechaPrefactura DESC ");
		
		query.setParameter("agencia", agencia);
		
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Factura> listadoFacturas(Agencia agencia) {		
		
		Query query = getSession().createQuery("from Factura where agencia = :agencia and fact_numeroFactura != null order by fact_fechaFactura DESC");
		
		query.setParameter("agencia", agencia);
		
		return query.list();
	}
	
	@Override
	public Factura buscar(Integer id) {
		
		Criteria crit = getSession().createCriteria(Factura.class);
		
		crit.add(Restrictions.eq("id", id));
		
		return (Factura) crit.uniqueResult();
	}
	
	@Override
	public Integer ultimaFactura(Agencia agencia, Integer anyo){
		
		Query query = getSession().createQuery("select substring(fact_numeroFactura, 7, 4) as numeroFactura from Factura "
				+ "where agencia = :agencia and year(fact_fechaFactura) = :anyo "
				+ "order by substring(fact_numeroFactura, 7, 4) desc");
		
		query.setParameter("agencia", agencia);		
		query.setParameter("anyo", anyo);
		
		if(query.list().size()==0){
			return 0;
		}else{			
			return new Integer(query.list().get(0).toString());
		}
		
	}

	@Override
	public Integer ultimaPrefactura(Agencia agencia, Integer anyo) {
		
		Query query = getSession().createQuery("select substring(fact_numeroPrefactura, 5, 4) as numeroPrefactura from Factura "
				+ "where agencia = :agencia and year(fact_fechaPrefactura) = :anyo "
				+ "order by substring(fact_numeroPrefactura, 5, 4) desc");
		
		query.setParameter("agencia", agencia);		
		query.setParameter("anyo", anyo);
		
		if(query.list().size()==0){
			return 0;
		}else{			
			return new Integer(query.list().get(0).toString());
		}
	}

	@Override
	@SuppressWarnings("unchecked") //Hago cast en el count porque por defecto count espera ser devuelto como Long
	public List<Factura> agrupaPedidos(List<Pedido> listaPedidos){
		
		Query query = getSession().createQuery("select new net.gefco.pedi2.modelo.Factura(p.agencia, p.cliente, p.nodoDestino, p.nodoOrigen.proveedor.prov_esPlataforma, sum(p.pedi_importe), cast(count(p.id), int) ) "
				+ " from Pedido p where p in (:listaPedidos) group by p.cliente.id, p.nodoOrigen.proveedor.prov_esPlataforma, p.nodoDestino.id ");
		query.setParameterList("listaPedidos", listaPedidos);
		
		return query.list();
		
	}
	
}
