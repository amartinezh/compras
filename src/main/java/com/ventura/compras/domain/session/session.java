package com.ventura.compras.domain.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class session implements Serializable{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anos == null) ? 0 : anos.hashCode());
		result = prime * result + ((centers == null) ? 0 : centers.hashCode());
		result = prime * result
				+ ((condicionActual == null) ? 0 : condicionActual.hashCode());
		result = prime
				* result
				+ ((condicionUsuario == null) ? 0 : condicionUsuario.hashCode());
		result = prime * result
				+ ((condiciones == null) ? 0 : condiciones.hashCode());
		result = prime * result + ((cpias == null) ? 0 : cpias.hashCode());
		result = prime * result
				+ ((currencys == null) ? 0 : currencys.hashCode());
		result = prime * result
				+ ((historial == null) ? 0 : historial.hashCode());
		result = prime * result
				+ ((informacion == null) ? 0 : informacion.hashCode());
		result = prime * result + ((levels == null) ? 0 : levels.hashCode());
		result = prime * result + ((meses == null) ? 0 : meses.hashCode());
		result = prime * result + tipoUsuario;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valores == null) ? 0 : valores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		session other = (session) obj;
		if (anos == null) {
			if (other.anos != null)
				return false;
		} else if (!anos.equals(other.anos))
			return false;
		if (centers == null) {
			if (other.centers != null)
				return false;
		} else if (!centers.equals(other.centers))
			return false;
		if (condicionActual == null) {
			if (other.condicionActual != null)
				return false;
		} else if (!condicionActual.equals(other.condicionActual))
			return false;
		if (condicionUsuario == null) {
			if (other.condicionUsuario != null)
				return false;
		} else if (!condicionUsuario.equals(other.condicionUsuario))
			return false;
		if (condiciones == null) {
			if (other.condiciones != null)
				return false;
		} else if (!condiciones.equals(other.condiciones))
			return false;
		if (cpias == null) {
			if (other.cpias != null)
				return false;
		} else if (!cpias.equals(other.cpias))
			return false;
		if (currencys == null) {
			if (other.currencys != null)
				return false;
		} else if (!currencys.equals(other.currencys))
			return false;
		if (historial == null) {
			if (other.historial != null)
				return false;
		} else if (!historial.equals(other.historial))
			return false;
		if (informacion == null) {
			if (other.informacion != null)
				return false;
		} else if (!informacion.equals(other.informacion))
			return false;
		if (levels == null) {
			if (other.levels != null)
				return false;
		} else if (!levels.equals(other.levels))
			return false;
		if (meses == null) {
			if (other.meses != null)
				return false;
		} else if (!meses.equals(other.meses))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valores == null) {
			if (other.valores != null)
				return false;
		} else if (!valores.equals(other.valores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "session [usuario=" + usuario + ", tipoUsuario=" + tipoUsuario
				+ ", informacion=" + informacion + ", condiciones="
				+ condiciones + ", condicionActual=" + condicionActual
				+ ", condicionUsuario=" + condicionUsuario + ", historial="
				+ historial + ", valores=" + valores + ", meses=" + meses
				+ ", anos=" + anos + ", cpias=" + cpias + ", levels=" + levels
				+ ", centers=" + centers + ", currencys=" + currencys + "]";
	}

	private String usuario;
	private int tipoUsuario;
	private List<Object> informacion;
	private Map<String, String> condiciones;
	private String condicionActual;
	private String condicionUsuario;
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	private String historial;
	private Map<String, String> valores;
	private Map<String, String> meses;
	private Map<String, String> anos;
	private Map<String, String> cpias;
	private Map<String, String> levels;
	private Map<String, String>	centers;
	private Map<String, String> currencys;
		
	public session() {
		// TODO Auto-generated constructor stub
	}
	
	public session(String usuario, int tipoUsuario) {
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
		this.informacion = new LinkedList<Object>();
		this.condiciones = new HashMap<String, String>();
		this.historial = "";
		this.valores = new HashMap<String, String>();
		this.meses = new HashMap<String, String>();
		this.anos = new HashMap<String, String>();
		this.cpias = new HashMap<String, String>();
		this.levels = new HashMap<String, String>();
		this.centers = new HashMap<String, String>();
		this.currencys = new HashMap<String, String>();
	}
	public Map<String, String> getCurrencys() {
		return currencys;
	}

	public void setCurrencys(Map<String, String> currencys) {
		this.currencys = currencys;
	}

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
