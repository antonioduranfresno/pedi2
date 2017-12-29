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
@Table(name="proveedor")
public class Proveedor extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;

	@NotEmpty
	private String				prov_nif				= null;
	
	@NotEmpty
	private String				prov_nombre				= null;
	
	private Boolean				prov_esPlataforma		= null;
	
	private String				prov_observaciones		= null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProv_nif() {
		return prov_nif;
	}

	public void setProv_nif(String prov_nif) {
		this.prov_nif = prov_nif;
	}

	public String getProv_nombre() {
		return prov_nombre;
	}

	public void setProv_nombre(String prov_nombre) {
		this.prov_nombre = prov_nombre;
	}

	public Boolean getProv_esPlataforma() {
		return prov_esPlataforma;
	}

	public void setProv_esPlataforma(Boolean prov_esPlataforma) {
		this.prov_esPlataforma = prov_esPlataforma;
	}

	public String getProv_observaciones() {
		return prov_observaciones;
	}

	public void setProv_observaciones(String prov_observaciones) {
		this.prov_observaciones = prov_observaciones;
	}

	public Proveedor() {
		super();
	}

	public Proveedor(Integer id, String prov_nif, String prov_nombre,
			Boolean prov_esPlataforma, String prov_observaciones) {
		super();
		this.id = id;
		this.prov_nif = prov_nif;
		this.prov_nombre = prov_nombre;
		this.prov_esPlataforma = prov_esPlataforma;
		this.prov_observaciones = prov_observaciones;
	}

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", prov_nif=" + prov_nif
				+ ", prov_nombre=" + prov_nombre + ", prov_esPlataforma="
				+ prov_esPlataforma + ", prov_observaciones="
				+ prov_observaciones + "]";
	}

}
