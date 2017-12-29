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

@Entity
@Table(name="clienteNodo")
public class ClienteNodo extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id					= null;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente		cliente				= null;
	
	@ManyToOne
	@JoinColumn(name="nodo")
	private Nodo		nodo				= null;
	
	private String		clno_codigo			= null;
	
	private Boolean		clno_nodoCarga		= null;
	
	private Boolean 	clno_nodoDescarga	= null;

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

	public Nodo getNodo() {
		return nodo;
	}
	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public String getClno_codigo() {
		return clno_codigo;
	}
	public void setClno_codigo(String clno_codigo) {
		this.clno_codigo = clno_codigo;
	}
	
	public Boolean getClno_nodoCarga() {
		return clno_nodoCarga;
	}
	public void setClno_nodoCarga(Boolean clno_nodoCarga) {
		this.clno_nodoCarga = clno_nodoCarga;
	}
	public Boolean getClno_nodoDescarga() {
		return clno_nodoDescarga;
	}
	public void setClno_nodoDescarga(Boolean clno_nodoDescarga) {
		this.clno_nodoDescarga = clno_nodoDescarga;
	}
	public ClienteNodo() {
		super();
	}

	public ClienteNodo(Integer id, Cliente cliente, Nodo nodo,
			String clno_codigo, Boolean clno_nodoCarga,
			Boolean clno_nodoDescarga) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.nodo = nodo;
		this.clno_codigo = clno_codigo;
		this.clno_nodoCarga = clno_nodoCarga;
		this.clno_nodoDescarga = clno_nodoDescarga;
	}
	
	@Override
	public String toString() {
		return "ClienteNodo [id=" + id + ", cliente=" + cliente + ", nodo="
				+ nodo + ", clno_codigo=" + clno_codigo + ", clno_nodoCarga="
				+ clno_nodoCarga + ", clno_nodoDescarga=" + clno_nodoDescarga
				+ "]";
	}
	
	

}
