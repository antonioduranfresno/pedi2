package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import net.gefco.pedi2.util.JsonResponse;

@Entity
@Table(name="cliente")
public class Cliente extends JsonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 	id						= null;
		
	@NotEmpty
	private String		clie_nadBy				= null; //Buyer = Comprador
	
	@NotEmpty
	private String		clie_nadPr				= null; //Payer = Pagador
	
	@NotEmpty
	private String 		clie_nombre				= null;
	
	@NotEmpty
	private String 		clie_alias				= null;
	
	@NotEmpty
	private String		clie_nif				= null;
	
	@NotEmpty
	private String		clie_direccion			= null;
	
	@NotEmpty
	private String    	clie_codigoSap			= null;
	
	@NotNull
	private Integer		clie_diasVencimiento	= null;

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

	public String getClie_alias() {
		return clie_alias;
	}
	public void setClie_alias(String clie_alias) {
		this.clie_alias = clie_alias;
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
	
	public String getClie_codigoSap() {
		return clie_codigoSap;
	}
	public void setClie_codigoSap(String clie_codigoSap) {
		this.clie_codigoSap = clie_codigoSap;
	}

	public Integer getClie_diasVencimiento() {
		return clie_diasVencimiento;
	}
	public void setClie_diasVencimiento(Integer clie_diasVencimiento) {
		this.clie_diasVencimiento = clie_diasVencimiento;
	}
	
	public Cliente() {
		super();
	}
	
	public Cliente(Integer id, String clie_nadBy, String clie_nadPr,
			String clie_nombre, String clie_alias, String clie_nif,
			String clie_direccion, String clie_codigoSap,
			Integer clie_diasVencimiento) {
		super();
		this.id = id;
		this.clie_nadBy = clie_nadBy;
		this.clie_nadPr = clie_nadPr;
		this.clie_nombre = clie_nombre;
		this.clie_alias = clie_alias;
		this.clie_nif = clie_nif;
		this.clie_direccion = clie_direccion;
		this.clie_codigoSap = clie_codigoSap;
		this.clie_diasVencimiento = clie_diasVencimiento;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", clie_nadBy=" + clie_nadBy
				+ ", clie_nadPr=" + clie_nadPr + ", clie_nombre=" + clie_nombre
				+ ", clie_alias=" + clie_alias + ", clie_nif=" + clie_nif
				+ ", clie_direccion=" + clie_direccion + ", clie_codigoSap="
				+ clie_codigoSap + ", clie_diasVencimiento="
				+ clie_diasVencimiento + "]";
	}
		
}
