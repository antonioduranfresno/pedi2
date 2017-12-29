package net.gefco.dia.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.gefco.dia.util.JsonResponse;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="nodo")
public class Nodo extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor			proveedor				= null;
		
	@NotEmpty
	private String				nodo_direccion 			= null;
	
	private String  			nodo_observaciones		= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public String getNodo_direccion() {
		return nodo_direccion;
	}
	public void setNodo_direccion(String nodo_direccion) {
		this.nodo_direccion = nodo_direccion;
	}

	public String getNodo_observaciones() {
		return nodo_observaciones;
	}
	public void setNodo_observaciones(String nodo_observaciones) {
		this.nodo_observaciones = nodo_observaciones;
	}
	
	public Nodo() {
		super();	
	}
	@Override
	public String toString() {
		return "Nodo [id=" + id + ", proveedor=" + proveedor
				+ ", nodo_direccion=" + nodo_direccion
				+ ", nodo_observaciones=" + nodo_observaciones + "]";
	}
	
}
