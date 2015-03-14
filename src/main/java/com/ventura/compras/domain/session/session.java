package com.ventura.compras.domain.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
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
	private List<Object> informacion;
	private Map<String, String> condiciones;
	private String condicionActual;
	private String condicionUsuario;
	private String historial;
	private Map<String, String> valores;
	private Map<String, String> meses;
	private Map<String, String> anos;
	private Map<String, String> cpias;
	private Map<String, String> levels;
	private Map<String, String>	centers;
		
	public session() {
		// TODO Auto-generated constructor stub
	}
	
	public session(String usuario) {
		this.usuario = usuario;
		this.informacion = new LinkedList<Object>();
		this.condiciones = new HashMap<String, String>();
		this.historial = "";
		this.valores = new HashMap<String, String>();
		this.meses = new HashMap<String, String>();
		this.anos = new HashMap<String, String>();
		this.cpias = new HashMap<String, String>();
		this.levels = new HashMap<String, String>();
		this.centers = new HashMap<String, String>();
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
	
	public List<Object> getInformacion() {
		return informacion;
	}
	
	public Map<String, String> getCondiciones() {
		return condiciones;
	}
	
	public String getCondicionActual() {
		return condicionActual;
	}
	
	public String getCondicionUsuario() {
		return condicionUsuario;
	}
	
	public String getHistorial() {
		return historial;
	}
	
	public Map<String, String> getValores() {
		return valores;
	}
	
	public Map<String, String> getAnos() {
		return anos;
	}
	
	public Map<String, String> getMeses() {
		return meses;
	}

	public Map<String, String> getCpias() {
		return cpias;
	}
	
	public Map<String, String> getLevels() {
		return levels;
	}
	
	public Map<String, String> getCenters() {
		return centers;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setInformacion(List<Object> informacion) {
		this.informacion = informacion;
	}
	
	public void setCondiciones(Map<String, String> condiciones) {
		this.condiciones = condiciones;
	}
	
	public void setCondicionActual(String condicionActual) {
		this.condicionActual = condicionActual;
	}
	
	public void setCondicionUsuario(String condicionUsuario) {
		this.condicionUsuario = condicionUsuario;
	}
	
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	
	public void setValores(Map<String, String> valores) {
		this.valores = valores;
	}
	
	public void setAnos(Map<String, String> anos) {
		this.anos = anos;
	}
	
	public void setMeses(Map<String, String> meses) {
		this.meses = meses;
	}
	
	public void setCpias(Map<String, String> cpias) {
		this.cpias = cpias;
	}
	
	public void setLevels(Map<String, String> levels) {
		this.levels = levels;
	}
	
	public void setCenters(Map<String, String> centers) {
		this.centers = centers;
	}
		
}
