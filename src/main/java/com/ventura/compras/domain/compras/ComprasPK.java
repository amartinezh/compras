package com.ventura.compras.domain.compras;

import java.io.Serializable;

import javax.persistence.Column;

public class ComprasPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	private int pano;
	@Column
	private int pmes;
	@Column
	private String preg;
	@Column
	private int pcia;
	@Column
	private String plocal;
	@Column
	private int pprov;
	@Column
	private String pipro;
	@Column
	private String nroor;
	
	@Override
	public String toString() {
		return "ComprasPK [pano=" + pano + ", pmes=" + pmes + ", preg=" + preg
				+ ", pcia=" + pcia + ", plocal=" + plocal + ", pprov=" + pprov
				+ ", pipro=" + pipro + ", nroor=" + nroor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nroor == null) ? 0 : nroor.hashCode());
		result = prime * result + pano;
		result = prime * result + pcia;
		result = prime * result + ((pipro == null) ? 0 : pipro.hashCode());
		result = prime * result + ((plocal == null) ? 0 : plocal.hashCode());
		result = prime * result + pmes;
		result = prime * result + pprov;
		result = prime * result + ((preg == null) ? 0 : preg.hashCode());
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
		ComprasPK other = (ComprasPK) obj;
		if (nroor == null) {
			if (other.nroor != null)
				return false;
		} else if (!nroor.equals(other.nroor))
			return false;
		if (pano != other.pano)
			return false;
		if (pcia != other.pcia)
			return false;
		if (pipro == null) {
			if (other.pipro != null)
				return false;
		} else if (!pipro.equals(other.pipro))
			return false;
		if (plocal == null) {
			if (other.plocal != null)
				return false;
		} else if (!plocal.equals(other.plocal))
			return false;
		if (pmes != other.pmes)
			return false;
		if (pprov != other.pprov)
			return false;
		if (preg == null) {
			if (other.preg != null)
				return false;
		} else if (!preg.equals(other.preg))
			return false;
		return true;
	}

	public int getPano() {
		return pano;
	}

	public int getPmes() {
		return pmes;
	}

	public String getPreg() {
		return preg;
	}

	public int getPcia() {
		return pcia;
	}

	public String getPlocal() {
		return plocal;
	}

	public int getPprov() {
		return pprov;
	}

	public String getPipro() {
		return pipro;
	}

	public String getNroor() {
		return nroor;
	}

	public void setPano(int pano) {
		this.pano = pano;
	}

	public void setPmes(int pmes) {
		this.pmes = pmes;
	}

	public void setPreg(String preg) {
		this.preg = preg;
	}

	public void setPcia(int pcia) {
		this.pcia = pcia;
	}

	public void setPlocal(String plocal) {
		this.plocal = plocal;
	}

	public void setPprov(int pprov) {
		this.pprov = pprov;
	}

	public void setPipro(String pipro) {
		this.pipro = pipro;
	}

	public void setNroor(String nroor) {
		this.nroor = nroor;
	}

	public ComprasPK() {
		// TODO Auto-generated constructor stub
	}
	
}
