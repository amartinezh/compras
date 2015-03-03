package com.ventura.compras.domain.adm;

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
@Table(name="company", schema="admin")
public class Company {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "comp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admin.company_comp_id_seq")
	@SequenceGenerator(name="admin.company_comp_id_seq", sequenceName="admin.company_comp_id_seq", allocationSize=1)
    private int id;
	
	@NotEmpty(message = "Por favor ingrese la descripción")
	@Column(name = "comp_desc")
	private String descripcion;
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", descripcion=" + descripcion + "]";
	}

	@OneToMany(mappedBy="comp")
	private Set<User> users;
	
	public Company() {
		// TODO Auto-generated constructor stub
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
