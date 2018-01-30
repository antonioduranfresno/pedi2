package net.gefco.pedi2.persistencia;

import java.util.Date;
import java.util.List;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.TarifaVenta;

public interface TarifaVentaDao {

	public void guardar(TarifaVenta tarifaVenta);
	
	public void actualizar(TarifaVenta tarifaVenta);
	
	public void eliminar(TarifaVenta tarifaVenta);

	public List<TarifaVenta> listado(Agencia agencia);
	
	public TarifaVenta buscar(Integer id);

	public boolean solapada(TarifaVenta tarifaVenta);

	public TarifaVenta obtenerTarifa(Integer idAgencia, Integer idCliente, Integer idNodoOrigen, Integer idNodoDestino, Date fecha);
	
}
