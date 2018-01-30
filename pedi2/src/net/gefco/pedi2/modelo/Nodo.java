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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="nodo")
public class Nodo extends JsonResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer 			id						= null;
	
	@NotEmpty
	private String				nodo_nombre				= null;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor			proveedor				= null;
		
	@NotEmpty
	private String				nodo_direccion 			= null;
	
	@NotEmpty
	private String				nodo_poblacion			= null;
	
	@NotEmpty
	private String				nodo_codigoPostal		= null;
	
	@NotEmpty
	private String				nodo_provincia			= null;
	
	@ManyToOne
	@JoinColumn(name="pais")
	private Pais				pais					= null;
	
	private String  			nodo_observaciones		= null;
	
	@ManyToOne
	@JoinColumn(name="zona")
	private Zona				zona					= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNodo_nombre() {
		return nodo_nombre;
	}
	public void setNodo_nombre(String nodo_nombre) {
		this.nodo_nombre = nodo_nombre;
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
	
	public String getNodo_poblacion() {
		return nodo_poblacion;
	}
	public void setNodo_poblacion(String nodo_poblacion) {
		this.nodo_poblacion = nodo_poblacion;
	}
	
	public String getNodo_codigoPostal() {
		return nodo_codigoPostal;
	}
	public void setNodo_codigoPostal(String nodo_codigoPostal) {
		this.nodo_codigoPostal = nodo_codigoPostal;
	}
	
	public String getNodo_provincia() {
		return nodo_provincia;
	}
	public void setNodo_provincia(String nodo_provincia) {
		this.nodo_provincia = nodo_provincia;
	}
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public String getNodo_observaciones() {
		return nodo_observaciones;
	}
	public void setNodo_observaciones(String nodo_observaciones) {
		this.nodo_observaciones = nodo_observaciones;
	}
		
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	public Nodo() {
		super();	
	}
	
	public Nodo(Integer id, String nodo_nombre, Proveedor proveedor,
			String nodo_direccion, String nodo_poblacion,
			String nodo_codigoPostal, String nodo_provincia, Pais pais,
			String nodo_observaciones, Zona zona) {
		super();
		this.id = id;
		this.nodo_nombre = nodo_nombre;
		this.proveedor = proveedor;
		this.nodo_direccion = nodo_direccion;
		this.nodo_poblacion = nodo_poblacion;
		this.nodo_codigoPostal = nodo_codigoPostal;
		this.nodo_provincia = nodo_provincia;
		this.pais = pais;
		this.nodo_observaciones = nodo_observaciones;
		this.zona = zona;
	}
	
	@Override
	public String toString() {
		return "Nodo [id=" + id + ", nodo_nombre=" + nodo_nombre
				+ ", proveedor=" + proveedor + ", nodo_direccion="
				+ nodo_direccion + ", nodo_poblacion=" + nodo_poblacion
				+ ", nodo_codigoPostal=" + nodo_codigoPostal
				+ ", nodo_provincia=" + nodo_provincia + ", pais=" + pais
				+ ", nodo_observaciones=" + nodo_observaciones + ", zona="
				+ zona + "]";
	}
	
	public String toStringCodigo(){
		return proveedor.getProv_nombre() + " - " + nodo_nombre;
	}
		
}
