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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import net.gefco.pedi2.util.JsonResponse;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pedido")
public class Pedido extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id							= null;
	
	@ManyToOne
	@JoinColumn(name="agencia")
	private Agencia		agencia						= null;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente		cliente						= null;
	
	@ManyToOne
	@JoinColumn(name="nodoOrigen")
	private Nodo		nodoOrigen					= null;
	
	@ManyToOne
	@JoinColumn(name="nodoDestino")
	private Nodo		nodoDestino					= null;
	
	@NotEmpty
	private String		pedi_codigo					= null;
	
	private String		pedi_referencia				= null;
	
	private String		pedi_matricula				= null;	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	private Date		pedi_fechaCreacion			= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date		pedi_fechaRecogida			= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	private Date		pedi_fechaEntrega			= null;
	
	private String		pedi_observaciones			= null;
	
	@NotNull
	@Min(value = 1, message ="!")
	private Integer		pedi_totalPalets			= null;
	
	@NotNull
	@Min(value = 1, message ="!")
	private Integer		pedi_totalHuecos			= null;
	
	private Double		pedi_importe				= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date		pedi_fechaTasacion			= null;

	@ManyToOne
	@JoinColumn(name="factura")
	private Factura		factura						= null;
	
	private Integer		pedi_grupo					= null;
	
	private Boolean		pedi_anulado				= null;
	
	private Boolean		pedi_incidencia				= null;
	
	private Date		pedi_fechaActualizacion 	= null;
	
	private String		pedi_usuarioActualizacion 	= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Nodo getNodoOrigen() {
		return nodoOrigen;
	}
	public void setNodoOrigen(Nodo nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public Nodo getNodoDestino() {
		return nodoDestino;
	}
	public void setNodoDestino(Nodo nodoDestino) {
		this.nodoDestino = nodoDestino;
	}

	public String getPedi_codigo() {
		return pedi_codigo;
	}
	public void setPedi_codigo(String pedi_codigo) {
		this.pedi_codigo = pedi_codigo;
	}

	public String getPedi_referencia() {
		return pedi_referencia;
	}
	public void setPedi_referencia(String pedi_referencia) {
		this.pedi_referencia = pedi_referencia;
	}

	public String getPedi_matricula() {
		return pedi_matricula;
	}
	public void setPedi_matricula(String pedi_matricula) {
		this.pedi_matricula = pedi_matricula;
	}

	public Date getPedi_fechaCreacion() {
		return pedi_fechaCreacion;
	}
	public void setPedi_fechaCreacion(Date pedi_fechaCreacion) {
		this.pedi_fechaCreacion = pedi_fechaCreacion;
	}

	public Date getPedi_fechaRecogida() {
		return pedi_fechaRecogida;
	}
	public void setPedi_fechaRecogida(Date pedi_fechaRecogida) {
		this.pedi_fechaRecogida = pedi_fechaRecogida;
	}

	public Date getPedi_fechaEntrega() {
		return pedi_fechaEntrega;
	}
	public void setPedi_fechaEntrega(Date pedi_fechaEntrega) {
		this.pedi_fechaEntrega = pedi_fechaEntrega;
	}

	public String getPedi_observaciones() {
		return pedi_observaciones;
	}
	public void setPedi_observaciones(String pedi_observaciones) {
		this.pedi_observaciones = pedi_observaciones;
	}

	public Integer getPedi_totalPalets() {
		return pedi_totalPalets;
	}
	public void setPedi_totalPalets(Integer pedi_totalPalets) {
		this.pedi_totalPalets = pedi_totalPalets;
	}
	
	public Integer getPedi_totalHuecos() {
		return pedi_totalHuecos;
	}
	public void setPedi_totalHuecos(Integer pedi_totalHuecos) {
		this.pedi_totalHuecos = pedi_totalHuecos;
	}
	
	public Double getPedi_importe() {
		return pedi_importe;
	}
	public void setPedi_importe(Double pedi_importe) {
		this.pedi_importe = pedi_importe;
	}

	public Date getPedi_fechaTasacion() {
		return pedi_fechaTasacion;
	}
	public void setPedi_fechaTasacion(Date pedi_fechaTasacion) {
		this.pedi_fechaTasacion = pedi_fechaTasacion;
	}

	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Integer getPedi_grupo() {
		return pedi_grupo;
	}
	public void setPedi_grupo(Integer pedi_grupo) {
		this.pedi_grupo = pedi_grupo;
	}
	
	public Boolean getPedi_anulado() {
		return pedi_anulado;
	}
	public void setPedi_anulado(Boolean pedi_anulado) {
		this.pedi_anulado = pedi_anulado;
	}
	
	public Boolean getPedi_incidencia() {
		return pedi_incidencia;
	}
	public void setPedi_incidencia(Boolean pedi_incidencia) {
		this.pedi_incidencia = pedi_incidencia;
	}
	
	public Date getPedi_fechaActualizacion() {
		return pedi_fechaActualizacion;
	}
	public void setPedi_fechaActualizacion(Date pedi_fechaActualizacion) {
		this.pedi_fechaActualizacion = pedi_fechaActualizacion;
	}
	
	public String getPedi_usuarioActualizacion() {
		return pedi_usuarioActualizacion;
	}
	public void setPedi_usuarioActualizacion(String pedi_usuarioActualizacion) {
		this.pedi_usuarioActualizacion = pedi_usuarioActualizacion;
	}
	
	public String getPedi_fechaCreacionFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (pedi_fechaCreacion == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaCreacion);
		}
	}
	
	public String getPedi_fechaRecogidaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaRecogida == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaRecogida);
		}
	}
	
	public String getPedi_fechaEntregaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (pedi_fechaEntrega == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaEntrega);
		}
	}
	
	public String getPedi_fechaTasacionFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaTasacion== null) {
			return "";
		} else {
			return sdf.format(pedi_fechaTasacion);
		}
	}
	
	public String getPedi_fechaActualizacionFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaActualizacion== null) {
			return sdf.format(new Date());
		} else {
			return sdf.format(pedi_fechaActualizacion);
		}
	}
	
	
	public Pedido() {
		super();
	}
	
	public Pedido(Integer id, Agencia agencia, Cliente cliente,
			Nodo nodoOrigen, Nodo nodoDestino, String pedi_codigo,
			String pedi_referencia, String pedi_matricula,
			Date pedi_fechaCreacion, Date pedi_fechaRecogida,
			Date pedi_fechaEntrega, String pedi_observaciones,
			Integer pedi_totalPalets, Integer pedi_totalHuecos,
			Double pedi_importe, Date pedi_fechaTasacion, Factura factura,
			Integer pedi_grupo, Boolean pedi_anulado, Boolean pedi_incidencia,
			Date pedi_fechaActualizacion, String pedi_usuarioActualizacion) {
		super();
		this.id = id;
		this.agencia = agencia;
		this.cliente = cliente;
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.pedi_codigo = pedi_codigo;
		this.pedi_referencia = pedi_referencia;
		this.pedi_matricula = pedi_matricula;
		this.pedi_fechaCreacion = pedi_fechaCreacion;
		this.pedi_fechaRecogida = pedi_fechaRecogida;
		this.pedi_fechaEntrega = pedi_fechaEntrega;
		this.pedi_observaciones = pedi_observaciones;
		this.pedi_totalPalets = pedi_totalPalets;
		this.pedi_totalHuecos = pedi_totalHuecos;
		this.pedi_importe = pedi_importe;
		this.pedi_fechaTasacion = pedi_fechaTasacion;
		this.factura = factura;
		this.pedi_grupo = pedi_grupo;
		this.pedi_anulado = pedi_anulado;
		this.pedi_incidencia = pedi_incidencia;
		this.pedi_fechaActualizacion = pedi_fechaActualizacion;
		this.pedi_usuarioActualizacion = pedi_usuarioActualizacion;
	}
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", agencia=" + agencia + ", cliente="
				+ cliente + ", nodoOrigen=" + nodoOrigen + ", nodoDestino="
				+ nodoDestino + ", pedi_codigo=" + pedi_codigo
				+ ", pedi_referencia=" + pedi_referencia + ", pedi_matricula="
				+ pedi_matricula + ", pedi_fechaCreacion=" + pedi_fechaCreacion
				+ ", pedi_fechaRecogida=" + pedi_fechaRecogida
				+ ", pedi_fechaEntrega=" + pedi_fechaEntrega
				+ ", pedi_observaciones=" + pedi_observaciones
				+ ", pedi_totalPalets=" + pedi_totalPalets
				+ ", pedi_totalHuecos=" + pedi_totalHuecos + ", pedi_importe="
				+ pedi_importe + ", pedi_fechaTasacion=" + pedi_fechaTasacion
				+ ", factura=" + factura + ", pedi_grupo=" + pedi_grupo
				+ ", pedi_anulado=" + pedi_anulado + ", pedi_incidencia="
				+ pedi_incidencia + ", pedi_fechaActualizacion="
				+ pedi_fechaActualizacion + ", pedi_usuarioActualizacion="
				+ pedi_usuarioActualizacion + "]";
	}
	
}