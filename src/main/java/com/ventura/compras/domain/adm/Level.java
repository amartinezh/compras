package com.ventura.compras.domain.adm;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.ventura.compras.domain.login.User;

@Entity
@Table(name="level", schema="admin")
public class Level implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "level_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admin.level_level_id_seq")
	@SequenceGenerator(name="admin.level_level_id_seq", sequenceName="admin.level_level_id_seq", allocationSize=1)
    private int id;
	
	@NotEmpty(message = "Por favor ingrese la descripción")
	@Column(name = "level_desc")
	private String descripcion;
	
	@OneToMany(mappedBy="level")
	private Set<User> users;
	
	public Level() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Level [id=" + id + ", descripcion=" + descripcion
				+ ", toString()=" + super.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
