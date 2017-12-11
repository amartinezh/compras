package com.ventura.compras.domain.compras;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@IdClass(ComprasPKh.class)
@Table(name = "compras_h", schema = "compras")
public class Compras_h implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name = "pano")
	private int pano;

	@Id
	@NotEmpty
	@Column(name = "pmes")
	private int pmes;

	@Id
	@NotEmpty
	@Column(name = "preg")
	private String preg;

	@Id
	@NotEmpty
	@Column(name = "pcia")
	private int pcia;

	@NotEmpty
	@Column(name = "pnom")
	private String pnom;

	@Id
	@NotEmpty
	@Column(name = "plocal")
	private String plocal;

	@NotEmpty
	@Column(name = "plnon")
	private String plnon;

	@NotEmpty
	@Column(name = "pcent")
	private String pcent;

	@NotEmpty
	@Column(name = "pcenn")
	private String pcenn;

	@Id
	@NotEmpty
	@Column(name = "pprov")
	private int pprov;

	@NotEmpty
	@Column(name = "ppnov")
	private String ppnov;

	@NotEmpty
	@Column(name = "ptype")
	private String ptype;

	@NotEmpty
	@Column(name = "ptyno")
	private String ptyno;

	@NotEmpty
	@Column(name = "pnit")
	private String pnit;

	@NotEmpty
	@Column(name = "pcomd")
	private String pcomd;

	@NotEmpty
	@Column(name = "pnomd")
	private String pnomd;

	@NotEmpty
	@Column(name = "ppais")
	private String ppais;

	@NotEmpty
	@Column(name = "pnpas")
	private String pnpas;

	@NotEmpty
	@Column(name = "tipoc")
	private String tipoc;

	@NotEmpty
	@Column(name = "picla")
	private String picla;

	@NotEmpty
	@Column(name = "picln")
	private String picln;

	@Id
	@NotEmpty
	@Column(name = "pipro")
	private String pipro;

	@NotEmpty
	@Column(name = "pides")
	private String pides;

	// @NotEmpty
	@Column(name = "punid")
	private String punid;

	@NotEmpty
	@Column(name = "punin")
	private String punin;

	@NotEmpty
	@Column(name = "pcstp")
	private String pcstp;

	@Id
	@NotEmpty
	@Column(name = "nroor")
	private String nroor;

	@NotEmpty
	@Column(name = "fecre")
	private String fecre;

	@NotEmpty
	@Column(name = "pmond")
	private String pmond;

	@NotEmpty
	@Column(name = "pqtyo")
	private BigDecimal pqtyo;

	@NotEmpty
	@Column(name = "pqtyd")
	private BigDecimal pqtyd;

	@NotEmpty
	@Column(name = "pqtyr")
	private BigDecimal pqtyr;

	@NotEmpty
	@Column(name = "pqtyu")
	private BigDecimal pqtyu;

	@NotEmpty
	@Column(name = "pqtyp")
	private BigDecimal pqtyp;

	@NotEmpty
	@Column(name = "pvalbo")
	private BigDecimal pvalbo;

	@NotEmpty
	@Column(name = "pvalto")
	private BigDecimal pvalto;

	@NotEmpty
	@Column(name = "pvalbd")
	private BigDecimal pvalbd;

	@NotEmpty
	@Column(name = "pvaltd")
	private BigDecimal pvaltd;

	@NotEmpty
	@Column(name = "pvalbr")
	private BigDecimal pvalbr;

	@NotEmpty
	@Column(name = "pvaltr")
	private BigDecimal pvaltr;

	@NotEmpty
	@Column(name = "pvalpo")
	private BigDecimal pvalpo;

	@NotEmpty
	@Column(name = "pvalve")
	private BigDecimal pvalve;

	@NotEmpty
	@Column(name = "ppreac")
	private BigDecimal ppreac;

	@NotEmpty
	@Column(name = "pprep1")
	private BigDecimal pprep1;

	@NotEmpty
	@Column(name = "fecep1")
	private String fecep1;

	@NotEmpty
	@Column(name = "pprep2")
	private BigDecimal pprep2;

	@NotEmpty
	@Column(name = "fecep2")
	private String fecep2;

	@NotEmpty
	@Column(name = "pprep3")
	private BigDecimal pprep3;

	@NotEmpty
	@Column(name = "fecep3")
	private String fecep3;
	
	@Column(name = "fecen")
	private String fecen;
	
	@Column(name="diave")
	private String diave;
	
	@Column(name = "solic")
	private String solic;
	
	@Column(name = "pqori")
	private BigDecimal pqori;

	public String getFecen() {
		return fecen;
	}

	public String getDiave() {
		return diave;
	}

	public String getSolic() {
		return solic;
	}

	public BigDecimal getPqori() {
		return pqori;
	}

	public void setFecen(String fecen) {
		this.fecen = fecen;
	}

	public void setDiave(String diave) {
		this.diave = diave;
	}

	public void setSolic(String solic) {
		this.solic = solic;
	}

	public void setPqori(BigDecimal pqori) {
		this.pqori = pqori;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecep1 == null) ? 0 : fecep1.hashCode());
		result = prime * result + ((fecep2 == null) ? 0 : fecep2.hashCode());
		result = prime * result + ((fecep3 == null) ? 0 : fecep3.hashCode());
		result = prime * result + ((fecre == null) ? 0 : fecre.hashCode());
		result = prime * result + ((nroor == null) ? 0 : nroor.hashCode());
		result = prime * result + pano;
		result = prime * result + ((pcenn == null) ? 0 : pcenn.hashCode());
		result = prime * result + ((pcent == null) ? 0 : pcent.hashCode());
		result = prime * result + pcia;
		result = prime * result + ((pcomd == null) ? 0 : pcomd.hashCode());
		result = prime * result + ((pcstp == null) ? 0 : pcstp.hashCode());
		result = prime * result + ((picla == null) ? 0 : picla.hashCode());
		result = prime * result + ((picln == null) ? 0 : picln.hashCode());
		result = prime * result + ((pides == null) ? 0 : pides.hashCode());
		result = prime * result + ((pipro == null) ? 0 : pipro.hashCode());
		result = prime * result + ((plnon == null) ? 0 : plnon.hashCode());
		result = prime * result + ((plocal == null) ? 0 : plocal.hashCode());
		result = prime * result + pmes;
		result = prime * result + ((pmond == null) ? 0 : pmond.hashCode());
		result = prime * result + ((pnit == null) ? 0 : pnit.hashCode());
		result = prime * result + ((pnom == null) ? 0 : pnom.hashCode());
		result = prime * result + ((pnomd == null) ? 0 : pnomd.hashCode());
		result = prime * result + ((pnpas == null) ? 0 : pnpas.hashCode());
		result = prime * result + ((ppais == null) ? 0 : ppais.hashCode());
		result = prime * result + ((ppnov == null) ? 0 : ppnov.hashCode());
		result = prime * result + ((ppreac == null) ? 0 : ppreac.hashCode());
		result = prime * result + ((pprep1 == null) ? 0 : pprep1.hashCode());
		result = prime * result + ((pprep2 == null) ? 0 : pprep2.hashCode());
		result = prime * result + ((pprep3 == null) ? 0 : pprep3.hashCode());
		result = prime * result + pprov;
		result = prime * result + ((pqtyd == null) ? 0 : pqtyd.hashCode());
		result = prime * result + ((pqtyo == null) ? 0 : pqtyo.hashCode());
		result = prime * result + ((pqtyp == null) ? 0 : pqtyp.hashCode());
		result = prime * result + ((pqtyr == null) ? 0 : pqtyr.hashCode());
		result = prime * result + ((pqtyu == null) ? 0 : pqtyu.hashCode());
		result = prime * result + ((preg == null) ? 0 : preg.hashCode());
		result = prime * result + ((ptyno == null) ? 0 : ptyno.hashCode());
		result = prime * result + ((ptype == null) ? 0 : ptype.hashCode());
		result = prime * result + ((punid == null) ? 0 : punid.hashCode());
		result = prime * result + ((punin == null) ? 0 : punin.hashCode());
		result = prime * result + ((pvalbd == null) ? 0 : pvalbd.hashCode());
		result = prime * result + ((pvalbo == null) ? 0 : pvalbo.hashCode());
		result = prime * result + ((pvalbr == null) ? 0 : pvalbr.hashCode());
		result = prime * result + ((pvalpo == null) ? 0 : pvalpo.hashCode());
		result = prime * result + ((pvaltd == null) ? 0 : pvaltd.hashCode());
		result = prime * result + ((pvalto == null) ? 0 : pvalto.hashCode());
		result = prime * result + ((pvaltr == null) ? 0 : pvaltr.hashCode());
		result = prime * result + ((pvalve == null) ? 0 : pvalve.hashCode());
		result = prime * result + ((tipoc == null) ? 0 : tipoc.hashCode());
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
		Compras_h other = (Compras_h) obj;
		if (fecep1 == null) {
			if (other.fecep1 != null)
				return false;
		} else if (!fecep1.equals(other.fecep1))
			return false;
		if (fecep2 == null) {
			if (other.fecep2 != null)
				return false;
		} else if (!fecep2.equals(other.fecep2))
			return false;
		if (fecep3 == null) {
			if (other.fecep3 != null)
				return false;
		} else if (!fecep3.equals(other.fecep3))
			return false;
		if (fecre == null) {
			if (other.fecre != null)
				return false;
		} else if (!fecre.equals(other.fecre))
			return false;
		if (nroor == null) {
			if (other.nroor != null)
				return false;
		} else if (!nroor.equals(other.nroor))
			return false;
		if (pano != other.pano)
			return false;
		if (pcenn == null) {
			if (other.pcenn != null)
				return false;
		} else if (!pcenn.equals(other.pcenn))
			return false;
		if (pcent == null) {
			if (other.pcent != null)
				return false;
		} else if (!pcent.equals(other.pcent))
			return false;
		if (pcia != other.pcia)
			return false;
		if (pcomd == null) {
			if (other.pcomd != null)
				return false;
		} else if (!pcomd.equals(other.pcomd))
			return false;
		if (pcstp == null) {
			if (other.pcstp != null)
				return false;
		} else if (!pcstp.equals(other.pcstp))
			return false;
		if (picla == null) {
			if (other.picla != null)
				return false;
		} else if (!picla.equals(other.picla))
			return false;
		if (picln == null) {
			if (other.picln != null)
				return false;
		} else if (!picln.equals(other.picln))
			return false;
		if (pides == null) {
			if (other.pides != null)
				return false;
		} else if (!pides.equals(other.pides))
			return false;
		if (pipro == null) {
			if (other.pipro != null)
				return false;
		} else if (!pipro.equals(other.pipro))
			return false;
		if (plnon == null) {
			if (other.plnon != null)
				return false;
		} else if (!plnon.equals(other.plnon))
			return false;
		if (plocal == null) {
			if (other.plocal != null)
				return false;
		} else if (!plocal.equals(other.plocal))
			return false;
		if (pmes != other.pmes)
			return false;
		if (pmond == null) {
			if (other.pmond != null)
				return false;
		} else if (!pmond.equals(other.pmond))
			return false;
		if (pnit == null) {
			if (other.pnit != null)
				return false;
		} else if (!pnit.equals(other.pnit))
			return false;
		if (pnom == null) {
			if (other.pnom != null)
				return false;
		} else if (!pnom.equals(other.pnom))
			return false;
		if (pnomd == null) {
			if (other.pnomd != null)
				return false;
		} else if (!pnomd.equals(other.pnomd))
			return false;
		if (pnpas == null) {
			if (other.pnpas != null)
				return false;
		} else if (!pnpas.equals(other.pnpas))
			return false;
		if (ppais == null) {
			if (other.ppais != null)
				return false;
		} else if (!ppais.equals(other.ppais))
			return false;
		if (ppnov == null) {
			if (other.ppnov != null)
				return false;
		} else if (!ppnov.equals(other.ppnov))
			return false;
		if (ppreac == null) {
			if (other.ppreac != null)
				return false;
		} else if (!ppreac.equals(other.ppreac))
			return false;
		if (pprep1 == null) {
			if (other.pprep1 != null)
				return false;
		} else if (!pprep1.equals(other.pprep1))
			return false;
		if (pprep2 == null) {
			if (other.pprep2 != null)
				return false;
		} else if (!pprep2.equals(other.pprep2))
			return false;
		if (pprep3 == null) {
			if (other.pprep3 != null)
				return false;
		} else if (!pprep3.equals(other.pprep3))
			return false;
		if (pprov != other.pprov)
			return false;
		if (pqtyd == null) {
			if (other.pqtyd != null)
				return false;
		} else if (!pqtyd.equals(other.pqtyd))
			return false;
		if (pqtyo == null) {
			if (other.pqtyo != null)
				return false;
		} else if (!pqtyo.equals(other.pqtyo))
			return false;
		if (pqtyp == null) {
			if (other.pqtyp != null)
				return false;
		} else if (!pqtyp.equals(other.pqtyp))
			return false;
		if (pqtyr == null) {
			if (other.pqtyr != null)
				return false;
		} else if (!pqtyr.equals(other.pqtyr))
			return false;
		if (pqtyu == null) {
			if (other.pqtyu != null)
				return false;
		} else if (!pqtyu.equals(other.pqtyu))
			return false;
		if (preg == null) {
			if (other.preg != null)
				return false;
		} else if (!preg.equals(other.preg))
			return false;
		if (ptyno == null) {
			if (other.ptyno != null)
				return false;
		} else if (!ptyno.equals(other.ptyno))
			return false;
		if (ptype == null) {
			if (other.ptype != null)
				return false;
		} else if (!ptype.equals(other.ptype))
			return false;
		if (punid == null) {
			if (other.punid != null)
				return false;
		} else if (!punid.equals(other.punid))
			return false;
		if (punin == null) {
			if (other.punin != null)
				return false;
		} else if (!punin.equals(other.punin))
			return false;
		if (pvalbd == null) {
			if (other.pvalbd != null)
				return false;
		} else if (!pvalbd.equals(other.pvalbd))
			return false;
		if (pvalbo == null) {
			if (other.pvalbo != null)
				return false;
		} else if (!pvalbo.equals(other.pvalbo))
			return false;
		if (pvalbr == null) {
			if (other.pvalbr != null)
				return false;
		} else if (!pvalbr.equals(other.pvalbr))
			return false;
		if (pvalpo == null) {
			if (other.pvalpo != null)
				return false;
		} else if (!pvalpo.equals(other.pvalpo))
			return false;
		if (pvaltd == null) {
			if (other.pvaltd != null)
				return false;
		} else if (!pvaltd.equals(other.pvaltd))
			return false;
		if (pvalto == null) {
			if (other.pvalto != null)
				return false;
		} else if (!pvalto.equals(other.pvalto))
			return false;
		if (pvaltr == null) {
			if (other.pvaltr != null)
				return false;
		} else if (!pvaltr.equals(other.pvaltr))
			return false;
		if (pvalve == null) {
			if (other.pvalve != null)
				return false;
		} else if (!pvalve.equals(other.pvalve))
			return false;
		if (tipoc == null) {
			if (other.tipoc != null)
				return false;
		} else if (!tipoc.equals(other.tipoc))
			return false;
		return true;
	}

	public Compras_h() {
		// TODO Auto-generated constructor stub
	}

	public Compras_h(BigDecimal pqtyp, String ptype, String ptyno, BigDecimal pqtyd,
			BigDecimal pqtyr, BigDecimal pqtyo, BigDecimal pvalbd,
			BigDecimal pvalpo, BigDecimal ppreac) {
		this.ptype = ptype;
		this.ptyno = ptyno;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pqtyo = pqtyo;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyp = pqtyp;
	}

	// Constructor Proveedor
	public Compras_h(int pprov, String ppnov, BigDecimal pqtyd, BigDecimal pqtyr, BigDecimal pqtyo,
			BigDecimal pvalbd, BigDecimal pvalpo,
			BigDecimal ppreac, String pnit, BigDecimal pqtyp) {
		this.pprov = pprov;
		this.ppnov = ppnov;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pqtyo = pqtyo;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pnit = pnit;
		this.pqtyp = pqtyp;
		//sum(c.pqtyo) as pqtyo, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, c.pnit as pnit, sum(c.pqtyp) as pqtyp
	}

	// Constructor Comprador
	public Compras_h(BigDecimal pqtyd, BigDecimal pqtyr, BigDecimal pvalbd,
			BigDecimal pvalpo, BigDecimal ppreac, String pcomd, String pnomd, BigDecimal pqtyo,
			BigDecimal pqtyp) {
		this.pcomd = pcomd;
		this.pnomd = pnomd;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyo = pqtyo;
		this.pqtyp = pqtyp;
	}

	// Constructor Item
	public Compras_h(BigDecimal pqtyd, BigDecimal pqtyr, String pipro,
			BigDecimal pvalbd, BigDecimal pvalpo, BigDecimal ppreac,
			String pides, BigDecimal pprep1, String fecep1, BigDecimal pprep2,
			String fecep2, BigDecimal pprep3, String fecep3, BigDecimal pqtyo,
			BigDecimal pqtyp, String punid) {
		this.pipro = pipro;
		this.pides = pides;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pprep1 = pprep1;
		this.fecep1 = fecep1;
		this.pprep2 = pprep2;
		this.fecep2 = fecep2;
		this.pprep3 = pprep3;
		this.fecep3 = fecep3;
		this.pqtyo = pqtyo;
		this.pqtyp = pqtyp;
		this.punid = punid;
	}

	// Construtor Clase
	public Compras_h(BigDecimal pqtyd, String picla, BigDecimal pqtyr,
			BigDecimal pvalbd, BigDecimal pvalpo, BigDecimal ppreac,
			String picln, BigDecimal pqtyo, BigDecimal pqtyp) {
		this.picla = picla;
		this.picln = picln;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyo = pqtyo; 
		this.pqtyp = pqtyp;
	}

	// Constructor Centro
	public Compras_h(String pcent, BigDecimal pqtyd, BigDecimal pqtyr,
			BigDecimal pvalbd, BigDecimal pvalpo, BigDecimal ppreac,
			String pcenn, BigDecimal pqtyo, BigDecimal pqtyp) {
		this.pcent = pcent;
		this.pcenn = pcenn;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyo = pqtyo; 
		this.pqtyp = pqtyp;
	}
	
	// Constructor Orden
	public Compras_h(BigDecimal pqtyp,String nroor, BigDecimal pqtyd, BigDecimal pqtyr,
			BigDecimal pvalbd, BigDecimal pvalpo, BigDecimal ppreac, BigDecimal pqtyo) {
		this.nroor = nroor;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyo = pqtyo;
		this.pqtyp = pqtyp;
		//c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp"
		//7 0, 1, 2,3,4,5,6
	}

	// Constructor Requisicion
	public Compras_h(BigDecimal pqtyd,  BigDecimal pqtyr, BigDecimal pvalbd,
			BigDecimal pvalpo, BigDecimal ppreac, BigDecimal pqtyo, BigDecimal pqtyp, String nroor) {
		this.nroor = nroor;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		/*this.tipoc = tipoc;
		if (tipoc.equalsIgnoreCase("r")) {
			this.ptyno = "Requisición";
		} else {
			this.ptyno = "Compra";
		}*/
		this.pqtyo = pqtyo;
		this.pqtyp = pqtyp;
	}

	// Constructor Bodega
	public Compras_h(String plocal, String plnon, BigDecimal pqtyd,
			BigDecimal pqtyr, BigDecimal pvalbd, BigDecimal pvalpo,
			BigDecimal ppreac, BigDecimal pqtyo, BigDecimal pqtyp) {
		this.plocal = plocal;
		this.plnon = plnon;
		this.pqtyd = pqtyd;
		this.pqtyr = pqtyr;
		this.pvalbd = pvalbd;
		this.pvalpo = pvalpo;
		this.ppreac = ppreac;
		this.pqtyo = pqtyo;
		this.pqtyp = pqtyp; 
	}

	@Override
	public String toString() {
		return "Compras [pano=" + pano + ", pmes=" + pmes + ", preg=" + preg
				+ ", pcia=" + pcia + ", pnom=" + pnom + ", plocal=" + plocal
				+ ", plnon=" + plnon + ", pcent=" + pcent + ", pcenn=" + pcenn
				+ ", pprov=" + pprov + ", ppnov=" + ppnov + ", ptype=" + ptype
				+ ", ptyno=" + ptyno + ", pnit=" + pnit + ", pcomd=" + pcomd
				+ ", pnomd=" + pnomd + ", ppais=" + ppais + ", pnpas=" + pnpas
				+ ", tipoc=" + tipoc + ", picla=" + picla + ", picln=" + picln
				+ ", pipro=" + pipro + ", pides=" + pides + ", punid=" + punid
				+ ", punin=" + punin + ", pcstp=" + pcstp + ", nroor=" + nroor
				+ ", fecre=" + fecre + ", pmond=" + pmond + ", pqtyo=" + pqtyo
				+ ", pqtyd=" + pqtyd + ", pqtyr=" + pqtyr + ", pqtyu=" + pqtyu
				+ ", pqtyp=" + pqtyp + ", pvalbo=" + pvalbo + ", pvalto="
				+ pvalto + ", pvalbd=" + pvalbd + ", pvaltd=" + pvaltd
				+ ", pvalbr=" + pvalbr + ", pvaltr=" + pvaltr + ", pvalpo="
				+ pvalpo + ", pvalve=" + pvalve + ", ppreac=" + ppreac
				+ ", pprep1=" + pprep1 + ", fecep1=" + fecep1 + ", pprep2="
				+ pprep2 + ", fecep2=" + fecep2 + ", pprep3=" + pprep3
				+ ", fecep3=" + fecep3 + "]";
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

	public String getPnom() {
		return pnom;
	}

	public String getPlocal() {
		return plocal;
	}

	public String getPlnon() {
		return plnon;
	}

	public String getPcent() {
		return pcent;
	}

	public String getPcenn() {
		return pcenn;
	}

	public int getPprov() {
		return pprov;
	}

	public String getPpnov() {
		return ppnov;
	}

	public String getPtype() {
		return ptype;
	}

	public String getPtyno() {
		return ptyno;
	}

	public String getPnit() {
		return pnit;
	}

	public String getPcomd() {
		return pcomd;
	}

	public String getPnomd() {
		return pnomd;
	}

	public String getPpais() {
		return ppais;
	}

	public String getPnpas() {
		return pnpas;
	}

	public String getTipoc() {
		return tipoc;
	}

	public String getPicla() {
		return picla;
	}

	public String getPicln() {
		return picln;
	}

	public String getPipro() {
		return pipro;
	}

	public String getPides() {
		return pides;
	}

	public String getpunid() {
		return punid;
	}

	public String getPunin() {
		return punin;
	}

	public String getPcstp() {
		return pcstp;
	}

	public String getNroor() {
		return nroor;
	}

	public String getFecre() {
		return fecre;
	}

	public String getPmond() {
		return pmond;
	}

	public BigDecimal getPqtyo() {
		return pqtyo;
	}

	public BigDecimal getPqtyd() {
		return pqtyd;
	}

	public BigDecimal getPqtyr() {
		return pqtyr;
	}

	public BigDecimal getPqtyu() {
		return pqtyu;
	}

	public BigDecimal getPqtyp() {
		return pqtyp;
	}

	public BigDecimal getPvalbo() {
		return pvalbo;
	}

	public BigDecimal getPvalto() {
		return pvalto;
	}

	public BigDecimal getPvalbd() {
		return pvalbd;
	}

	public BigDecimal getPvaltd() {
		return pvaltd;
	}

	public BigDecimal getPvalbr() {
		return pvalbr;
	}

	public BigDecimal getPvaltr() {
		return pvaltr;
	}

	public BigDecimal getPvalpo() {
		return pvalpo;
	}

	public BigDecimal getPvalve() {
		return pvalve;
	}

	public BigDecimal getPpreac() {
		return ppreac;
	}

	public BigDecimal getPprep1() {
		return pprep1;
	}

	public String getFecep1() {
		return fecep1;
	}

	public BigDecimal getPprep2() {
		return pprep2;
	}

	public String getFecep2() {
		return fecep2;
	}

	public BigDecimal getPprep3() {
		return pprep3;
	}

	public String getFecep3() {
		return fecep3;
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

	public void setPnom(String pnom) {
		this.pnom = pnom;
	}

	public void setPlocal(String plocal) {
		this.plocal = plocal;
	}

	public void setPlnon(String plnon) {
		this.plnon = plnon;
	}

	public void setPcent(String pcent) {
		this.pcent = pcent;
	}

	public void setPcenn(String pcenn) {
		this.pcenn = pcenn;
	}

	public void setPprov(int pprov) {
		this.pprov = pprov;
	}

	public void setPpnov(String ppnov) {
		this.ppnov = ppnov;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public void setPtyno(String ptyno) {
		this.ptyno = ptyno;
	}

	public void setPnit(String pnit) {
		this.pnit = pnit;
	}

	public void setPcomd(String pcomd) {
		this.pcomd = pcomd;
	}

	public void setPnomd(String pnomd) {
		this.pnomd = pnomd;
	}

	public void setPpais(String ppais) {
		this.ppais = ppais;
	}

	public void setPnpas(String pnpas) {
		this.pnpas = pnpas;
	}

	public void setTipoc(String tipoc) {
		this.tipoc = tipoc;
	}

	public void setPicla(String picla) {
		this.picla = picla;
	}

	public void setPicln(String picln) {
		this.picln = picln;
	}

	public void setPipro(String pipro) {
		this.pipro = pipro;
	}

	public void setPides(String pides) {
		this.pides = pides;
	}

	public void setpunid(String punid) {
		this.punid = punid;
	}

	public void setPunin(String punin) {
		this.punin = punin;
	}

	public void setPcstp(String pcstp) {
		this.pcstp = pcstp;
	}

	public void setNroor(String nroor) {
		this.nroor = nroor;
	}

	public void setFecre(String fecre) {
		this.fecre = fecre;
	}

	public void setPmond(String pmond) {
		this.pmond = pmond;
	}

	public void setPqtyo(BigDecimal pqtyo) {
		this.pqtyo = pqtyo;
	}

	public void setPqtyd(BigDecimal pqtyd) {
		this.pqtyd = pqtyd;
	}

	public void setPqtyr(BigDecimal pqtyr) {
		this.pqtyr = pqtyr;
	}

	public void setPqtyu(BigDecimal pqtyu) {
		this.pqtyu = pqtyu;
	}

	public void setPqtyp(BigDecimal pqtyp) {
		this.pqtyp = pqtyp;
	}

	public void setPvalbo(BigDecimal pvalbo) {
		this.pvalbo = pvalbo;
	}

	public void setPvalto(BigDecimal pvalto) {
		this.pvalto = pvalto;
	}

	public void setPvalbd(BigDecimal pvalbd) {
		this.pvalbd = pvalbd;
	}

	public void setPvaltd(BigDecimal pvaltd) {
		this.pvaltd = pvaltd;
	}

	public void setPvalbr(BigDecimal pvalbr) {
		this.pvalbr = pvalbr;
	}

	public void setPvaltr(BigDecimal pvaltr) {
		this.pvaltr = pvaltr;
	}

	public void setPvalpo(BigDecimal pvalpo) {
		this.pvalpo = pvalpo;
	}

	public void setPvalve(BigDecimal pvalve) {
		this.pvalve = pvalve;
	}

	public void setPpreac(BigDecimal ppreac) {
		this.ppreac = ppreac;
	}

	public void setPprep1(BigDecimal pprep1) {
		this.pprep1 = pprep1;
	}

	public void setFecep1(String fecep1) {
		this.fecep1 = fecep1;
	}

	public void setPprep2(BigDecimal pprep2) {
		this.pprep2 = pprep2;
	}

	public void setFecep2(String fecep2) {
		this.fecep2 = fecep2;
	}

	public void setPprep3(BigDecimal pprep3) {
		this.pprep3 = pprep3;
	}

	public void setFecep3(String fecep3) {
		this.fecep3 = fecep3;
	}

	public void sumarCompras(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarProveedores(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());		
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarComprador(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarItem(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarClases(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarCentros(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarOrdenes(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarBodegas(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	public void sumarRequesiciones(Compras com) {
		this.pqtyd = pqtyd.add(com.getPqtyd());
		this.pqtyr = pqtyr.add(com.getPqtyr());
		this.pvalbd = pvalbd.add(com.getPvalbd());
		this.pvalpo = pvalpo.add(com.getPvalpo());
		this.ppreac = ppreac.add(com.getPpreac());
		this.pqtyo = pqtyo.add(com.getPqtyo());
		this.pqtyp = pqtyp.add(com.getPqtyp());
	}

	
	
}
