package net.gefco.pedi2.negocio;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Factura;
import net.gefco.pedi2.modelo.Pedido;
import net.gefco.pedi2.persistencia.FacturaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

	@Autowired
	private FacturaDao facturaDao;

	public Integer guardar(Factura factura) {		
		return facturaDao.guardar(factura);
	}

	public void actualizar(Factura factura) {		
		facturaDao.actualizar(factura);
	}	
	
	public void eliminar(Factura factura) {		
		facturaDao.eliminar(factura);
	}
	
	public List<Factura> listadoFacturas(Agencia agencia){
		return facturaDao.listadoFacturas(agencia);
	}
	
	public List<Factura> listadoPrefacturas(Agencia agencia){
		return facturaDao.listadoPrefacturas(agencia);
	}
	
	public Factura buscar(Integer id){
		return facturaDao.buscar(id);
	}
	
	public String ultimaFactura(Agencia agencia, Integer anyo){		
		return "E0"+agencia.getAgen_codigo()+"1"+String.format("%04d", facturaDao.ultimaFactura(agencia, anyo)+1);
	}

	public String ultimaPrefactura(Agencia agencia, Integer anyo){		
		return agencia.getAgen_codigo()+"1"+String.format("%04d", facturaDao.ultimaPrefactura(agencia, anyo)+1);
	}
	
	public List<Factura> agrupaPedidos(List<Pedido> listaPedidos){
		return facturaDao.agrupaPedidos(listaPedidos);
	}
	
}