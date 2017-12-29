package net.gefco.dia.modelo;

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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import net.gefco.dia.util.JsonResponse;

@Entity
@Table(name="tarifaVenta")
public class TarifaVenta extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id							= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date				tave_fechaDesde				= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date				tave_fechaHasta				= null;
		
	@ManyToOne
	@JoinColumn(name="agencia")
	private Agencia				agencia						= null;
		
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente				cliente						= null;
	
	@ManyToOne
	@JoinColumn(name="nodoOrigen")
	private Nodo				nodoOrigen					= null;
	
	@ManyToOne
	@JoinColumn(name="nodoDestino")
	private Nodo				nodoDestino					= null;
	
	private String  			tave_observaciones			= null;
	
	@NotNull
	private Double 				tave_importeCamionCompleto	= null;
	
	@NotNull
	private Integer 			tave_numeroMaxPaletT1		= null;
	
	@NotNull
	private Double 				tave_importePaletT1			= null;

	@NotNull
	private Integer 			tave_numeroMaxPaletT2		= null;

	@NotNull
	private Double 				tave_importePaletT2			= null;

	@NotNull
	private Integer 			tave_numeroMaxPaletT3		= null;

	@NotNull
	private Double 				tave_importePaletT3			= null;

	@NotNull
	private Integer 			tave_numeroMaxPaletT4		= null;

	@NotNull
	private Double 				tave_importePaletT4			= null;

	@NotNull
	private Integer 			tave_numeroMaxPaletT5		= null;

	@NotNull
	private Double 				tave_importePaletT5			= null;

	public TarifaVenta() {
		super();
	}

	public TarifaVenta(Integer id, Date tave_fechaDesde, Date tave_fechaHasta,
			Agencia agencia, Cliente cliente, Nodo nodoOrigen,
			Nodo nodoDestino, String tave_observaciones,
			Double tave_importeCamionCompleto, Integer tave_numeroMaxPaletT1,
			Double tave_importePaletT1, Integer tave_numeroMaxPaletT2,
			Double tave_importePaletT2, Integer tave_numeroMaxPaletT3,
			Double tave_importePaletT3, Integer tave_numeroMaxPaletT4,
			Double tave_importePaletT4, Integer tave_numeroMaxPaletT5,
			Double tave_importePaletT5) {
		super();
		this.id = id;
		this.tave_fechaDesde = tave_fechaDesde;
		this.tave_fechaHasta = tave_fechaHasta;
		this.agencia = agencia;
		this.cliente = cliente;
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.tave_observaciones = tave_observaciones;
		this.tave_importeCamionCompleto = tave_importeCamionCompleto;
		this.tave_numeroMaxPaletT1 = tave_numeroMaxPaletT1;
		this.tave_importePaletT1 = tave_importePaletT1;
		this.tave_numeroMaxPaletT2 = tave_numeroMaxPaletT2;
		this.tave_importePaletT2 = tave_importePaletT2;
		this.tave_numeroMaxPaletT3 = tave_numeroMaxPaletT3;
		this.tave_importePaletT3 = tave_importePaletT3;
		this.tave_numeroMaxPaletT4 = tave_numeroMaxPaletT4;
		this.tave_importePaletT4 = tave_importePaletT4;
		this.tave_numeroMaxPaletT5 = tave_numeroMaxPaletT5;
		this.tave_importePaletT5 = tave_importePaletT5;
	}

	public Integer getId() {
		return id;
	}

	public Date getTave_fechaDesde() {
		return tave_fechaDesde;
	}

	public void setTave_fechaDesde(Date tave_fechaDesde) {
		this.tave_fechaDesde = tave_fechaDesde;
	}

	public Date getTave_fechaHasta() {
		return tave_fechaHasta;
	}

	public void setTave_fechaHasta(Date tave_fechaHasta) {
		this.tave_fechaHasta = tave_fechaHasta;
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

	public String getTave_observaciones() {
		return tave_observaciones;
	}

	public void setTave_observaciones(String tave_observaciones) {
		this.tave_observaciones = tave_observaciones;
	}

	public Double getTave_importeCamionCompleto() {
		return tave_importeCamionCompleto;
	}

	public void setTave_importeCamionCompleto(Double tave_importeCamionCompleto) {
		this.tave_importeCamionCompleto = tave_importeCamionCompleto;
	}

	public Integer getTave_numeroMaxPaletT1() {
		return tave_numeroMaxPaletT1;
	}

	public void setTave_numeroMaxPaletT1(Integer tave_numeroMaxPaletT1) {
		this.tave_numeroMaxPaletT1 = tave_numeroMaxPaletT1;
	}

	public Double getTave_importePaletT1() {
		return tave_importePaletT1;
	}

	public void setTave_importePaletT1(Double tave_importePaletT1) {
		this.tave_importePaletT1 = tave_importePaletT1;
	}

	public Integer getTave_numeroMaxPaletT2() {
		return tave_numeroMaxPaletT2;
	}

	public void setTave_numeroMaxPaletT2(Integer tave_numeroMaxPaletT2) {
		this.tave_numeroMaxPaletT2 = tave_numeroMaxPaletT2;
	}

	public Double getTave_importePaletT2() {
		return tave_importePaletT2;
	}

	public void setTave_importePaletT2(Double tave_importePaletT2) {
		this.tave_importePaletT2 = tave_importePaletT2;
	}

	public Integer getTave_numeroMaxPaletT3() {
		return tave_numeroMaxPaletT3;
	}

	public void setTave_numeroMaxPaletT3(Integer tave_numeroMaxPaletT3) {
		this.tave_numeroMaxPaletT3 = tave_numeroMaxPaletT3;
	}

	public Double getTave_importePaletT3() {
		return tave_importePaletT3;
	}

	public void setTave_importePaletT3(Double tave_importePaletT3) {
		this.tave_importePaletT3 = tave_importePaletT3;
	}

	public Integer getTave_numeroMaxPaletT4() {
		return tave_numeroMaxPaletT4;
	}

	public void setTave_numeroMaxPaletT4(Integer tave_numeroMaxPaletT4) {
		this.tave_numeroMaxPaletT4 = tave_numeroMaxPaletT4;
	}

	public Double getTave_importePaletT4() {
		return tave_importePaletT4;
	}

	public void setTave_importePaletT4(Double tave_importePaletT4) {
		this.tave_importePaletT4 = tave_importePaletT4;
	}

	public Integer getTave_numeroMaxPaletT5() {
		return tave_numeroMaxPaletT5;
	}

	public void setTave_numeroMaxPaletT5(Integer tave_numeroMaxPaletT5) {
		this.tave_numeroMaxPaletT5 = tave_numeroMaxPaletT5;
	}

	public Double getTave_importePaletT5() {
		return tave_importePaletT5;
	}

	public void setTave_importePaletT5(Double tave_importePaletT5) {
		this.tave_importePaletT5 = tave_importePaletT5;
	}
	
	@Override
	public String toString() {
		return "TarifaVenta [id=" + id + ", tave_fechaDesde=" + tave_fechaDesde
				+ ", tave_fechaHasta=" + tave_fechaHasta + ", agencia="
				+ agencia + ", cliente=" + cliente + ", nodoOrigen="
				+ nodoOrigen + ", nodoDestino=" + nodoDestino
				+ ", tave_observaciones=" + tave_observaciones
				+ ", tave_importeCamionCompleto=" + tave_importeCamionCompleto
				+ ", tave_numeroMaxPaletT1=" + tave_numeroMaxPaletT1
				+ ", tave_importePaletT1=" + tave_importePaletT1
				+ ", tave_numeroMaxPaletT2=" + tave_numeroMaxPaletT2
				+ ", tave_importePaletT2=" + tave_importePaletT2
				+ ", tave_numeroMaxPaletT3=" + tave_numeroMaxPaletT3
				+ ", tave_importePaletT3=" + tave_importePaletT3
				+ ", tave_numeroMaxPaletT4=" + tave_numeroMaxPaletT4
				+ ", tave_importePaletT4=" + tave_importePaletT4
				+ ", tave_numeroMaxPaletT5=" + tave_numeroMaxPaletT5
				+ ", tave_importePaletT5=" + tave_importePaletT5 + "]";
	}

	public String getTave_fechaDesdeFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (tave_fechaDesde == null) {
			return "";
		} else {
			return sdf.format(tave_fechaDesde);
		}
	}
	
	public String getTave_fechaHastaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (tave_fechaHasta == null ) {
			return "";
		} else {
			return sdf.format(tave_fechaHasta);
		}
	}

}
