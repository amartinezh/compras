package com.ventura.compras.domain.adm;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.ventura.compras.domain.login.User;

@Entity
@Table(name="center", schema="admin")
public class Center implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="center_id")
	private int id;
	
	@Column(name="center_desc")
	private String descripcion;
	
	@OneToMany(mappedBy="cent")
	private Set<User> users;

	@Override
	public String toString() {
		return "Center [id=" + id + ", descripcion=" + descripcion
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
