package net.gefco.pedi2.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import net.gefco.pedi2.util.JsonResponse;

@Entity
@Table(name="factura")
public class Factura extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	private Boolean				fact_esTransferencia 	= null;
	
	private String				fact_numeroPrefactura	= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private	Date				fact_fechaPrefactura	= null;
	
	private String				fact_numeroFactura		= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private	Date				fact_fechaFactura		= null;
		
	@ManyToOne
	@JoinColumn(name="agencia")
	private Agencia				agencia					= null;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente				cliente					= null;
	
	@ManyToOne
	@JoinColumn(name="nodoDestino")
	private Nodo				nodoDestino				= null;
	
	private String				fact_codigoNodoCliente	= null;
	
	private	Date				fact_fechaVencimiento	= null;
	
	private Double				fact_baseImponible		= null;
	
	private Double				fact_cuotaIva			= null;
	
	private Double				fact_totalDocumento		= null;
	
	private Integer				fact_estadoPrefactura	= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date				fact_fechaRespuesta		= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date				fact_fechaLimite		= null;
	
	private Integer				fact_numeroPedidos		= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getFact_esTransferencia() {
		return fact_esTransferencia;
	}
	public void setFact_esTransferencia(Boolean fact_esTransferencia) {
		this.fact_esTransferencia = fact_esTransferencia;
	}

	public String getFact_numeroPrefactura() {
		return fact_numeroPrefactura;
	}
	public void setFact_numeroPrefactura(String fact_numeroPrefactura) {
		this.fact_numeroPrefactura = fact_numeroPrefactura;
	}

	public Date getFact_fechaPrefactura() {
		return fact_fechaPrefactura;
	}
	public void setFact_fechaPrefactura(Date fact_fechaPrefactura) {
		this.fact_fechaPrefactura = fact_fechaPrefactura;
	}

	public String getFact_numeroFactura() {
		return fact_numeroFactura;
	}
	public void setFact_numeroFactura(String fact_numeroFactura) {
		this.fact_numeroFactura = fact_numeroFactura;
	}

	public Date getFact_fechaFactura() {
		return fact_fechaFactura;
	}
	public void setFact_fechaFactura(Date fact_fechaFactura) {
		this.fact_fechaFactura = fact_fechaFactura;
	}

	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Nodo getNodoDestino() {
		return nodoDestino;
	}
	public void setNodoDestino(Nodo nodoDestino) {
		this.nodoDestino = nodoDestino;
	}
	
	public String getFact_codigoNodoCliente() {
		return fact_codigoNodoCliente;
	}
	public void setFact_codigoNodoCliente(String fact_codigoNodoCliente) {
		this.fact_codigoNodoCliente = fact_codigoNodoCliente;
	}
	
	public Date getFact_fechaVencimiento() {
		return fact_fechaVencimiento;
	}
	public void setFact_fechaVencimiento(Date fact_fechaVencimiento) {
		this.fact_fechaVencimiento = fact_fechaVencimiento;
	}

	public Double getFact_baseImponible() {
		return fact_baseImponible;
	}
	public void setFact_baseImponible(Double fact_baseImponible) {
		this.fact_baseImponible = fact_baseImponible;
	}

	public Double getFact_cuotaIva() {
		return fact_cuotaIva;
	}
	public void setFact_cuotaIva(Double fact_cuotaIva) {
		this.fact_cuotaIva = fact_cuotaIva;
	}

	public Double getFact_totalDocumento() {
		return fact_totalDocumento;
	}
	public void setFact_totalDocumento(Double fact_totalDocumento) {
		this.fact_totalDocumento = fact_totalDocumento;
	}
	
	public Integer getFact_estadoPrefactura() {
		return fact_estadoPrefactura;
	}
	public void setFact_estadoPrefactura(Integer fact_estadoPrefactura) {
		this.fact_estadoPrefactura = fact_estadoPrefactura;
	}
	
	public Date getFact_fechaRespuesta() {
		return fact_fechaRespuesta;
	}
	public void setFact_fechaRespuesta(Date fact_fechaRespuesta) {
		this.fact_fechaRespuesta = fact_fechaRespuesta;
	}
	
	public Date getFact_fechaLimite() {
		return fact_fechaLimite;
	}
	public void setFact_fechaLimite(Date fact_fechaLimite) {
		this.fact_fechaLimite = fact_fechaLimite;
	}

	public Integer getFact_numeroPedidos() {
		return fact_numeroPedidos;
	}
	public void setFact_numeroPedidos(Integer fact_numeroPedidos) {
		this.fact_numeroPedidos = fact_numeroPedidos;
	}
	
	public String getFact_fechaPrefacturaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fact_fechaPrefactura== null) {
			return "";
		} else {
			return sdf.format(fact_fechaPrefactura);
		}
	}
	
	public String getFact_fechaFacturaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fact_fechaFactura== null) {
			return "";
		} else {
			return sdf.format(fact_fechaFactura);
		}
	}
	
	public String getFact_fechaVencimientoFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fact_fechaVencimiento== null) {
			return "";
		} else {
			return sdf.format(fact_fechaVencimiento);
		}
	}
	
	public String getFact_fechaRespuestaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fact_fechaRespuesta== null) {
			return "";
		} else {
			return sdf.format(fact_fechaRespuesta);
		}
	}
	
	public String getFact_fechaLimiteFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fact_fechaLimite== null) {
			return "";
		} else {
			return sdf.format(fact_fechaLimite);
		}
	}
	
	
	public Factura() {
		super();
	}
	
	public Factura(Integer id){
		super();
		this.id = id;
	}
	
	public Factura(Agencia agencia, Cliente cliente, Nodo nodoDestino, Boolean fact_esTransferencia,
			Double fact_baseImponible, Integer fact_numeroPedidos) {
		super();		
		this.agencia = agencia;				
		this.cliente = cliente;
		this.nodoDestino = nodoDestino;
		this.fact_esTransferencia = fact_esTransferencia;		
		this.fact_baseImponible = fact_baseImponible;
		this.fact_numeroPedidos = fact_numeroPedidos;
	}
	
	public Factura(Integer id, Boolean fact_esTransferencia,
			String fact_numeroPrefactura, Date fact_fechaPrefactura,
			String fact_numeroFactura, Date fact_fechaFactura, Agencia agencia,
			Cliente cliente, Nodo nodoDestino, String fact_codigoNodoCliente, Date fact_fechaVencimiento,
			Double fact_baseImponible, Double fact_cuotaIva,
			Double fact_totalDocumento, Integer fact_estadoPrefactura,
			Date fact_fechaRespuesta, Date fact_fechaLimite, Integer fact_numeroPedidos) {
		super();
		this.id = id;
		this.fact_esTransferencia = fact_esTransferencia;
		this.fact_numeroPrefactura = fact_numeroPrefactura;
		this.fact_fechaPrefactura = fact_fechaPrefactura;
		this.fact_numeroFactura = fact_numeroFactura;
		this.fact_fechaFactura = fact_fechaFactura;
		this.agencia = agencia;
		this.cliente = cliente;
		this.nodoDestino = nodoDestino;
		this.fact_codigoNodoCliente = fact_codigoNodoCliente;
		this.fact_fechaVencimiento = fact_fechaVencimiento;
		this.fact_baseImponible = fact_baseImponible;
		this.fact_cuotaIva = fact_cuotaIva;
		this.fact_totalDocumento = fact_totalDocumento;
		this.fact_estadoPrefactura = fact_estadoPrefactura;
		this.fact_fechaRespuesta = fact_fechaRespuesta;
		this.fact_fechaLimite = fact_fechaLimite;
		this.fact_numeroPedidos = fact_numeroPedidos;
	}
	
	@Override
	public String toString() {
		return "Factura [id=" + id + ", fact_esTransferencia="
				+ fact_esTransferencia + ", fact_numeroPrefactura="
				+ fact_numeroPrefactura + ", fact_fechaPrefactura="
				+ fact_fechaPrefactura + ", fact_numeroFactura="
				+ fact_numeroFactura + ", fact_fechaFactura="
				+ fact_fechaFactura + ", agencia=" + agencia + ", cliente="
				+ cliente + ", nodoDestino=" + nodoDestino
				+ ", fact_codigoNodoCliente=" + fact_codigoNodoCliente
				+ ", fact_fechaVencimiento=" + fact_fechaVencimiento
				+ ", fact_baseImponible=" + fact_baseImponible
				+ ", fact_cuotaIva=" + fact_cuotaIva + ", fact_totalDocumento="
				+ fact_totalDocumento + ", fact_estadoPrefactura="
				+ fact_estadoPrefactura + ", fact_fechaRespuesta="
				+ fact_fechaRespuesta + ", fact_fechaLimite="
				+ fact_fechaLimite + "]";
	}
	
}