package net.gefco.pedi2.persistencia;

import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Factura;
import net.gefco.pedi2.modelo.Pedido;

public interface FacturaDao {

	public Integer guardar(Factura factura);
	
	public void actualizar(Factura factura);
	
	public void eliminar(Factura factura);

	public List<Factura> listadoFacturas(Agencia agencia);
	
	public List<Factura> listadoPrefacturas(Agencia agencia);
	
	public Factura buscar(Integer id);

	public Integer ultimaFactura(Agencia agencia, Integer anyo);

	public Integer ultimaPrefactura(Agencia agencia, Integer anyo);

	public List<Factura> agrupaPedidos(List<Pedido> listaPedidos);
	
}
