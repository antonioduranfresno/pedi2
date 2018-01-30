package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import net.gefco.pedi2.util.JsonResponse;

@Entity
@Table(name="pais")
public class Pais extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	@NotEmpty
	private String				pais_codigo				= null;
	
	@NotEmpty
	private String				pais_nombre				= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPais_codigo() {
		return pais_codigo;
	}
	public void setPais_codigo(String pais_codigo) {
		this.pais_codigo = pais_codigo;
	}

	public String getPais_nombre() {
		return pais_nombre;
	}
	public void setPais_nombre(String pais_nombre) {
		this.pais_nombre = pais_nombre;
	}

	public Pais() {
		super();
	}

	public Pais(Integer id, String pais_codigo, String pais_nombre) {
		super();
		this.id = id;
		this.pais_codigo = pais_codigo;
		this.pais_nombre = pais_nombre;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", pais_codigo=" + pais_codigo
				+ ", pais_nombre=" + pais_nombre + "]";
	}
	
	
	public String toStringCodigo(){		
		return pais_codigo+" - "+pais_nombre;
	}

}
