package net.gefco.dia.persistencia;

import java.util.List;

import net.gefco.dia.modelo.TarifaVenta;

public interface TarifaVentaDao {

	public void guardar(TarifaVenta tarifaVenta);
	
	public void actualizar(TarifaVenta tarifaVenta);
	
	public void eliminar(TarifaVenta tarifaVenta);

	public List<TarifaVenta> listado();
	
	public TarifaVenta buscar(Integer id);

	public boolean solapada(TarifaVenta tarifaVenta);

	
}
