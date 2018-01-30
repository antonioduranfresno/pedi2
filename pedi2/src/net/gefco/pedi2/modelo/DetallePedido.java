package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.gefco.pedi2.util.JsonResponse;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="detallePedido")
public class DetallePedido extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 		id							= null;
	
	@ManyToOne
	@JoinColumn(name="pedido")
	private Pedido			pedido						= null;
	
	@NotEmpty
	private String			depe_codigoArticuloEan		= null;
	
	@NotEmpty
	private String			depe_codigoArticuloCliente  = null;
	
	private String			depe_descripcionArticulo	= null;
	
	@NotNull
	private Integer			depe_unidadesSolicitadas	= null;
	
	@NotNull
	private Integer			depe_unidadesCaja			= null;
	
	@NotNull
	private Integer			depe_cajasPalet				= null;
	
	@NotNull
	private Integer			depe_numeroPalets			= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getDepe_codigoArticuloEan() {
		return depe_codigoArticuloEan;
	}
	public void setDepe_codigoArticuloEan(String depe_codigoArticuloEan) {
		this.depe_codigoArticuloEan = depe_codigoArticuloEan;
	}

	public String getDepe_codigoArticuloCliente() {
		return depe_codigoArticuloCliente;
	}
	public void setDepe_codigoArticuloCliente(String depe_codigoArticuloCliente) {
		this.depe_codigoArticuloCliente = depe_codigoArticuloCliente;
	}

	public String getDepe_descripcionArticulo() {
		return depe_descripcionArticulo;
	}
	public void setDepe_descripcionArticulo(String depe_descripcionArticulo) {
		this.depe_descripcionArticulo = depe_descripcionArticulo;
	}

	public Integer getDepe_unidadesSolicitadas() {
		return depe_unidadesSolicitadas;
	}
	public void setDepe_unidadesSolicitadas(Integer depe_unidadesSolicitadas) {
		this.depe_unidadesSolicitadas = depe_unidadesSolicitadas;
	}

	public Integer getDepe_unidadesCaja() {
		return depe_unidadesCaja;
	}
	public void setDepe_unidadesCaja(Integer depe_unidadesCaja) {
		this.depe_unidadesCaja = depe_unidadesCaja;
	}

	public Integer getDepe_cajasPalet() {
		return depe_cajasPalet;
	}
	public void setDepe_cajasPalet(Integer depe_cajasPalet) {
		this.depe_cajasPalet = depe_cajasPalet;
	}

	public Integer getDepe_numeroPalets() {
		return depe_numeroPalets;
	}
	public void setDepe_numeroPalets(Integer depe_numeroPalets) {
		this.depe_numeroPalets = depe_numeroPalets;
	}

	public DetallePedido() {
		super();	
	}

	public DetallePedido(Integer id, Pedido pedido,
			String depe_codigoArticuloEan, String depe_codigoArticuloCliente,
			String depe_descripcionArticulo, Integer depe_unidadesSolicitadas,
			Integer depe_unidadesCaja, Integer depe_cajasPalet,
			Integer depe_numeroPalets) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.depe_codigoArticuloEan = depe_codigoArticuloEan;
		this.depe_codigoArticuloCliente = depe_codigoArticuloCliente;
		this.depe_descripcionArticulo = depe_descripcionArticulo;
		this.depe_unidadesSolicitadas = depe_unidadesSolicitadas;
		this.depe_unidadesCaja = depe_unidadesCaja;
		this.depe_cajasPalet = depe_cajasPalet;
		this.depe_numeroPalets = depe_numeroPalets;
	}

	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", pedido=" + pedido
				+ ", depe_codigoArticuloEan=" + depe_codigoArticuloEan
				+ ", depe_codigoArticuloCliente=" + depe_codigoArticuloCliente
				+ ", depe_descripcionArticulo=" + depe_descripcionArticulo
				+ ", depe_unidadesSolicitadas=" + depe_unidadesSolicitadas
				+ ", depe_unidadesCaja=" + depe_unidadesCaja
				+ ", depe_cajasPalet=" + depe_cajasPalet
				+ ", depe_numeroPalets=" + depe_numeroPalets + "]";
	}

}
