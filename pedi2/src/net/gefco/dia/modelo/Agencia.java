package net.gefco.dia.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.gefco.dia.util.JsonResponse;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="agencia")
public class Agencia extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	@NotEmpty
	private String				agen_codigo 			= null;
	
	@NotEmpty
	private String  			agen_nombre				= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgen_codigo() {
		return agen_codigo;
	}
	public void setAgen_codigo(String agen_codigo) {
		this.agen_codigo = agen_codigo;
	}
	
	public String getAgen_nombre() {
		return agen_nombre;
	}
	public void setAgen_nombre(String agen_nombre) {
		this.agen_nombre = agen_nombre;
	}
		
	public Agencia() {
		super();
	}

	public Agencia(Integer id) {
		super();
		this.id= id;		
	}

	public Agencia(Integer id, String agen_codigo, String agen_nombre) {
		super();
		this.id = id;
		this.agen_codigo = agen_codigo;
		this.agen_nombre = agen_nombre;
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", agen_codigo=" + agen_codigo
				+ ", agen_nombre=" + agen_nombre + "]";
	}
	
	public String toStringCodigo(){		
		return agen_codigo+" - "+agen_nombre;
	}
	
}
