package net.gefco.pedi2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id					= null;
	
	private String usua_matricula 		= null;
	
	private String usua_nombre 			= null;
	
	private String usua_password 		= null;
	
	private String usua_correo 			= null;
	
	@ManyToOne
	@JoinColumn(name="agencia")
	private Agencia	agencia				= null;
	
	private Boolean usua_administrador 	= null;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsua_matricula() {
		return usua_matricula;
	}
	public void setUsua_matricula(String usua_matricula) {
		this.usua_matricula = usua_matricula;
	}
	
	public String getUsua_nombre() {
		return usua_nombre;
	}
	public void setUsua_nombre(String usua_nombre) {
		this.usua_nombre = usua_nombre;
	}
	
	public String getUsua_password() {
		return usua_password;
	}
	public void setUsua_password(String usua_password) {
		this.usua_password = usua_password;
	}
	
	public String getUsua_correo() {
		return usua_correo;
	}
	public void setUsua_correo(String usua_correo) {
		this.usua_correo = usua_correo;
	}
	
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	public Boolean getUsua_administrador() {
		return usua_administrador;
	}
	public void setUsua_administrador(Boolean usua_administrador) {
		this.usua_administrador = usua_administrador;
	}
	
	public Usuario() {
		super();	
	}
		
	public Usuario(Integer id, String usua_matricula, String usua_nombre,
			String usua_password, String usua_correo, Agencia agencia,
			Boolean usua_administrador) {
		super();
		this.id = id;
		this.usua_matricula = usua_matricula;
		this.usua_nombre = usua_nombre;
		this.usua_password = usua_password;
		this.usua_correo = usua_correo;
		this.agencia = agencia;
		this.usua_administrador = usua_administrador;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usua_matricula=" + usua_matricula
				+ ", usua_nombre=" + usua_nombre + ", usua_password="
				+ usua_password + ", usua_correo=" + usua_correo + ", agencia="
				+ agencia + ", usua_administrador=" + usua_administrador + "]";
	}
	
	public String toStringUsuario(){
		return usua_matricula + " / " + agencia.getAgen_codigo();
	}
	
}
