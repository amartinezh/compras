package com.ventura.compras.domain.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class session implements Serializable{
	/*
	@Override
	public String toString() {
		return "session [usuario=" + usuario + ", permisos=" + permisos.toString()
				+ ", tipo=" + tipo + ", nivel=" + nivel + ", company="
				+ company + ", view=" + view + "]";
	}*/

	@Override
	public String toString() {
		return "session [usuario=" + usuario + "]";
	}
	
	private String usuario;
//	private List<Map<String,String>> permisos;
//	private String tipo;
//	private String nivel;
//	private String company;
//	private String view;
		
	public session() {
		// TODO Auto-generated constructor stub
	}
	
	public session(String usuario) {
		this.usuario = usuario;		
	}
	/*
	public session(String usuario, List<Map<String,String>> permisos, String tipo, String nivel, String company) {
		this.usuario = usuario;
		this.permisos = permisos;
		this.tipo = tipo;
		this.nivel = nivel;
		this.company = company;		
	}
	public List<Map<String,String>> getPermisos() {
		return permisos;
	}
	
	public String getTipo() {
		return tipo;
	}
	*/
	public String getUsuario() {
		return usuario;
	}
	/*
	public String getCompany() {
		return company;
	}
	
	public String getNivel() {
		return nivel;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setPermisos(List<Map<String,String>> permisos) {
		this.permisos = permisos;
	}
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
		/*
	public void setCompany(String company) {
		this.company = company;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}*/
	
}
