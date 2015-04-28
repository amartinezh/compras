package com.ventura.compras.domain.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Reporte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String ppnov;
	private List<Compras> compras;
	
	public Reporte(String ppnov) {
		this.ppnov = ppnov;
		this.compras = new LinkedList<Compras>();
		for(int i =0; i<13; i++) {
			Compras c = new Compras();
			c.setPvalbd(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));
			compras.add(c);
		}
	}
	
	@Override
	public String toString() {
		return "Reporte [ppnov=" + ppnov + ", compras=" + compras + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compras == null) ? 0 : compras.hashCode());
		result = prime * result + ((ppnov == null) ? 0 : ppnov.hashCode());
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
		Reporte other = (Reporte) obj;
		if (compras == null) {
			if (other.compras != null)
				return false;
		} else if (!compras.equals(other.compras))
			return false;
		if (ppnov == null) {
			if (other.ppnov != null)
				return false;
		} else if (!ppnov.equals(other.ppnov))
			return false;
		return true;
	}

	public String getPpnov() {
		return ppnov;
	}
	public List<Compras> getCompras() {
		return compras;
	}
	public void setPpnov(String ppnov) {
		this.ppnov = ppnov;
	}
	public void setCompras(List<Compras> compras) {
		this.compras = compras;
	}

}
