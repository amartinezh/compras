package com.ventura.compras.domain.login;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="permisos", schema="adm") 
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="adm.permisos_id_seq")
	@SequenceGenerator(name="adm.permisos_id_seq", sequenceName="adm.permisos_id_seq", allocationSize=1)
	private String id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "permiso")
	private String permiso;
	
	public String getId() {
		return id;
	}
	
	public String getPermiso() {
		return permiso;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String toString() {
        return "id: " + this.id + ";";
    }
}
