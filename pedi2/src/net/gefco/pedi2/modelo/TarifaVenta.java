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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import net.gefco.pedi2.util.JsonResponse;

@Entity
@Table(name="tarifaVenta")
public class TarifaVenta extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id								= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date				tave_fechaDesde					= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date				tave_fechaHasta					= null;
		
	@ManyToOne
	@JoinColumn(name="agencia")
	private Agencia				agencia							= null;
		
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente				cliente							= null;
	
	@ManyToOne
	@JoinColumn(name="nodoOrigen")
	private Nodo				nodoOrigen						= null;
	
	@ManyToOne
	@JoinColumn(name="nodoDestino")
	private Nodo				nodoDestino						= null;
	
	@NotNull
	private Double 				tave_importeCamionCompleto		= null;
	
	private Double 				tave_numeroDesdeCC 				= null;
	
	private Double 				tave_importeT1					= null;
	
	private Double 				tave_importeT2					= null;

	private Double 				tave_importeT3					= null;
	
	private Double 				tave_importeT4					= null;
	
	private Double 				tave_importeT5					= null;
	
	private Integer 			tave_numeroMaxT1				= null;	
	
	private Integer 			tave_numeroMaxT2				= null;
		
	private Integer 			tave_numeroMaxT3				= null;	
	
	private Integer 			tave_numeroMaxT4				= null;	
	
	private Integer 			tave_numeroMaxT5				= null;	
	
	private Boolean				tave_soloTranchas				= null;

	private String  			tave_observaciones				= null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Double getTave_importeCamionCompleto() {
		return tave_importeCamionCompleto;
	}
	public void setTave_importeCamionCompleto(Double tave_importeCamionCompleto) {
		this.tave_importeCamionCompleto = tave_importeCamionCompleto;
	}

	public Double getTave_numeroDesdeCC() {
		return tave_numeroDesdeCC;
	}
	public void setTave_numeroDesdeCC(Double tave_numeroDesdeCC) {
		this.tave_numeroDesdeCC = tave_numeroDesdeCC;
	}

	public Double getTave_importeT1() {
		return tave_importeT1;
	}
	public void setTave_importeT1(Double tave_importeT1) {
		this.tave_importeT1 = tave_importeT1;
	}

	public Double getTave_importeT2() {
		return tave_importeT2;
	}
	public void setTave_importeT2(Double tave_importeT2) {
		this.tave_importeT2 = tave_importeT2;
	}

	public Double getTave_importeT3() {
		return tave_importeT3;
	}
	public void setTave_importeT3(Double tave_importeT3) {
		this.tave_importeT3 = tave_importeT3;
	}

	public Double getTave_importeT4() {
		return tave_importeT4;
	}
	public void setTave_importeT4(Double tave_importeT4) {
		this.tave_importeT4 = tave_importeT4;
	}

	public Double getTave_importeT5() {
		return tave_importeT5;
	}
	public void setTave_importeT5(Double tave_importeT5) {
		this.tave_importeT5 = tave_importeT5;
	}

	public Integer getTave_numeroMaxT1() {
		return tave_numeroMaxT1;
	}
	public void setTave_numeroMaxT1(Integer tave_numeroMaxT1) {
		this.tave_numeroMaxT1 = tave_numeroMaxT1;
	}

	public Integer getTave_numeroMaxT2() {
		return tave_numeroMaxT2;
	}
	public void setTave_numeroMaxT2(Integer tave_numeroMaxT2) {
		this.tave_numeroMaxT2 = tave_numeroMaxT2;
	}

	public Integer getTave_numeroMaxT3() {
		return tave_numeroMaxT3;
	}
	public void setTave_numeroMaxT3(Integer tave_numeroMaxT3) {
		this.tave_numeroMaxT3 = tave_numeroMaxT3;
	}

	public Integer getTave_numeroMaxT4() {
		return tave_numeroMaxT4;
	}
	public void setTave_numeroMaxT4(Integer tave_numeroMaxT4) {
		this.tave_numeroMaxT4 = tave_numeroMaxT4;
	}

	public Integer getTave_numeroMaxT5() {
		return tave_numeroMaxT5;
	}
	public void setTave_numeroMaxT5(Integer tave_numeroMaxT5) {
		this.tave_numeroMaxT5 = tave_numeroMaxT5;
	}

	public Boolean getTave_soloTranchas() {
		return tave_soloTranchas;
	}
	public void setTave_soloTranchas(Boolean tave_soloTranchas) {
		this.tave_soloTranchas = tave_soloTranchas;
	}

	public String getTave_observaciones() {
		return tave_observaciones;
	}
	public void setTave_observaciones(String tave_observaciones) {
		this.tave_observaciones = tave_observaciones;
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

	public TarifaVenta() {
		super();		
	}

	public TarifaVenta(Integer id, Date tave_fechaDesde, Date tave_fechaHasta,
			Agencia agencia, Cliente cliente, Nodo nodoOrigen,
			Nodo nodoDestino, Double tave_importeCamionCompleto,
			Double tave_numeroDesdeCC, Double tave_importeT1,
			Double tave_importeT2, Double tave_importeT3,
			Double tave_importeT4, Double tave_importeT5,
			Integer tave_numeroMaxT1, Integer tave_numeroMaxT2,
			Integer tave_numeroMaxT3, Integer tave_numeroMaxT4,
			Integer tave_numeroMaxT5, Boolean tave_soloTranchas,
			String tave_observaciones) {
		super();
		this.id = id;
		this.tave_fechaDesde = tave_fechaDesde;
		this.tave_fechaHasta = tave_fechaHasta;
		this.agencia = agencia;
		this.cliente = cliente;
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.tave_importeCamionCompleto = tave_importeCamionCompleto;
		this.tave_numeroDesdeCC = tave_numeroDesdeCC;
		this.tave_importeT1 = tave_importeT1;
		this.tave_importeT2 = tave_importeT2;
		this.tave_importeT3 = tave_importeT3;
		this.tave_importeT4 = tave_importeT4;
		this.tave_importeT5 = tave_importeT5;
		this.tave_numeroMaxT1 = tave_numeroMaxT1;
		this.tave_numeroMaxT2 = tave_numeroMaxT2;
		this.tave_numeroMaxT3 = tave_numeroMaxT3;
		this.tave_numeroMaxT4 = tave_numeroMaxT4;
		this.tave_numeroMaxT5 = tave_numeroMaxT5;
		this.tave_soloTranchas = tave_soloTranchas;
		this.tave_observaciones = tave_observaciones;
	}
	
	@Override
	public String toString() {
		return "TarifaVenta [id=" + id + ", tave_fechaDesde=" + tave_fechaDesde
				+ ", tave_fechaHasta=" + tave_fechaHasta + ", agencia="
				+ agencia + ", cliente=" + cliente + ", nodoOrigen="
				+ nodoOrigen + ", nodoDestino=" + nodoDestino
				+ ", tave_importeCamionCompleto=" + tave_importeCamionCompleto
				+ ", tave_numeroDesdeCC=" + tave_numeroDesdeCC
				+ ", tave_importeT1=" + tave_importeT1 + ", tave_importeT2="
				+ tave_importeT2 + ", tave_importeT3=" + tave_importeT3
				+ ", tave_importeT4=" + tave_importeT4 + ", tave_importeT5="
				+ tave_importeT5 + ", tave_numeroMaxT1=" + tave_numeroMaxT1
				+ ", tave_numeroMaxT2=" + tave_numeroMaxT2
				+ ", tave_numeroMaxT3=" + tave_numeroMaxT3
				+ ", tave_numeroMaxT4=" + tave_numeroMaxT4
				+ ", tave_numeroMaxT5=" + tave_numeroMaxT5
				+ ", tave_soloTranchas=" + tave_soloTranchas
				+ ", tave_observaciones=" + tave_observaciones + "]";
	}

}