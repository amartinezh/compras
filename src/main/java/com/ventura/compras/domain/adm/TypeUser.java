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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import com.ventura.compras.domain.login.User;

@Entity
@Table(name="type_users", schema="admin")
public class TypeUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admin.type_users_id_seq")
	@SequenceGenerator(name="admin.type_users_id_seq", sequenceName="admin.type_users_id_seq", allocationSize=1)
    private int id;
	
	@NotEmpty(message = "Por favor ingrese la descripción")
	@Column(name = "descripcion")	
	private String descripcion;
	
	@OneToMany(mappedBy="tip_usuario")
	private Set<User> users;
	
	public TypeUser() {
		// TODO Auto-generated constructor stub
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public TypeUser(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
        return "Descripción: " + descripcion + ";";
    }
	
}
