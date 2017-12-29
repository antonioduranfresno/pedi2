package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.gefco.pedi2.util.JsonResponse;

@Entity
@Table(name="clienteProveedor")
public class ClienteProveedor extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id				= null;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente		cliente			= null;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor	proveedor		= null;
	
	private String		clpr_codigo		= null;

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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getClpr_codigo() {
		return clpr_codigo;
	}

	public void setClpr_codigo(String clpr_codigo) {
		this.clpr_codigo = clpr_codigo;
	}


	public ClienteProveedor() {
		super();
	}

	public ClienteProveedor(Integer id, Cliente cliente, Proveedor proveedor,
			String clpr_codigo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.proveedor = proveedor;
		this.clpr_codigo = clpr_codigo;		
	}

	@Override
	public String toString() {
		return "ClienteProveedor [id=" + id + ", cliente=" + cliente
				+ ", proveedor=" + proveedor + ", clpr_codigo=" + clpr_codigo + "]";
	}

}
