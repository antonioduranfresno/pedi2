package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.gefco.pedi2.util.JsonResponse;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="zona")
public class Zona extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	@NotEmpty
	private String				zona_nombre				= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getZona_nombre() {
		return zona_nombre;
	}
	public void setZona_nombre(String zona_nombre) {
		this.zona_nombre = zona_nombre;
	}

	public Zona() {
		super();
	}

	public Zona(Integer id, String zona_nombre) {
		super();
		this.id = id;
		this.zona_nombre = zona_nombre;
	}

	@Override
	public String toString() {
		return "Zona [id=" + id + ", zona_nombre=" + zona_nombre + "]";
	}
		
}
