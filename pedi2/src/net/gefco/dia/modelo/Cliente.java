package net.gefco.dia.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import net.gefco.dia.util.JsonResponse;

@Entity
@Table(name="cliente")
public class Cliente extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id				= null;
	
	@NotEmpty
	private String		clie_nadBy		= null; //Buyer = Comprador
	
	@NotEmpty
	private String		clie_nadPr		= null; //Payer = Pagador
	
	@NotEmpty
	private String 		clie_nombre		= null;
	
	@NotEmpty
	private String		clie_nif		= null;
	
	@NotEmpty
	private String		clie_direccion	= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getClie_nadBy() {
		return clie_nadBy;
	}
	public void setClie_nadBy(String clie_nadBy) {
		this.clie_nadBy = clie_nadBy;
	}

	public String getClie_nadPr() {
		return clie_nadPr;
	}
	public void setClie_nadPr(String clie_nadPr) {
		this.clie_nadPr = clie_nadPr;
	}

	public String getClie_nombre() {
		return clie_nombre;
	}
	public void setClie_nombre(String clie_nombre) {
		this.clie_nombre = clie_nombre;
	}

	public String getClie_nif() {
		return clie_nif;
	}
	public void setClie_nif(String clie_nif) {
		this.clie_nif = clie_nif;
	}

	public String getClie_direccion() {
		return clie_direccion;
	}
	public void setClie_direccion(String clie_direccion) {
		this.clie_direccion = clie_direccion;
	}
	
	public Cliente() {
		super();
	}
	
	public Cliente(Integer id, String clie_nadBy, String clie_nadPr,
			String clie_nombre, String clie_nif, String clie_direccion) {
		super();
		this.id = id;
		this.clie_nadBy = clie_nadBy;
		this.clie_nadPr = clie_nadPr;
		this.clie_nombre = clie_nombre;
		this.clie_nif = clie_nif;
		this.clie_direccion = clie_direccion;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", clie_nadBy=" + clie_nadBy
				+ ", clie_nadPr=" + clie_nadPr + ", clie_nombre=" + clie_nombre
				+ ", clie_nif=" + clie_nif + ", clie_direccion="
				+ clie_direccion + "]";
	}
	
}
