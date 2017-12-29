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
@Table(name="clienteNodo")
public class Pedido extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id					= null;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente		cliente				= null;
	
	@ManyToOne
	@JoinColumn(name="nodoOrigen")
	private Nodo		nodoOrigen			= null;
	
	@ManyToOne
	@JoinColumn(name="nodoDestino")
	private Nodo		nodoDestino			= null;
	
	private String		pedi_codigo			= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date		pedi_fechaCreacion	= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date		pedi_fechaRecogida	= null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date		pedi_fechaEntrega	= null;

	public Pedido() {
		super();
	}

	public Pedido(Integer id, Cliente cliente, Nodo nodoOrigen,
			Nodo nodoDestino, String pedi_codigo, Date pedi_fechaCreacion,
			Date pedi_fechaRecogida, Date pedi_fechaEntrega) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.pedi_codigo = pedi_codigo;
		this.pedi_fechaCreacion = pedi_fechaCreacion;
		this.pedi_fechaRecogida = pedi_fechaRecogida;
		this.pedi_fechaEntrega = pedi_fechaEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", nodoOrigen="
				+ nodoOrigen + ", nodoDestino=" + nodoDestino
				+ ", pedi_codigo=" + pedi_codigo + ", pedi_fechaCreacion="
				+ pedi_fechaCreacion + ", pedi_fechaRecogida="
				+ pedi_fechaRecogida + ", pedi_fechaEntrega="
				+ pedi_fechaEntrega + "]";
	}

	public String getTave_fechaCreacionFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaCreacion == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaCreacion);
		}
	}
	
	public String getTave_fechaRecogidaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaRecogida == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaRecogida);
		}
	}
	
	public String getTave_fechaEntregaFormateada() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (pedi_fechaEntrega == null) {
			return "";
		} else {
			return sdf.format(pedi_fechaEntrega);
		}
	}

}
