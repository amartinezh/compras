package com.ventura.compras.repository.compras.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.repository.compras.ComprasDao;

@Repository
public class ComprasDaoImpl implements ComprasDao {

	@PersistenceContext
	private EntityManager em = null;

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Compras> getCompras() {
		List<Object[]> result = em.createQuery("SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c GROUP BY c.ptype, c.ptyno").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras((String)obj[0], (String)obj[1], new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN)));
		}
		return compras;		
	}
				
	@SuppressWarnings("unchecked")
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for(int i = 0; i <c.length; i++) {
			if(where.isEmpty()) {
				where = condiciones.get(c[i]);
			} else {
				where = where + " and " + condiciones.get(c[i]);				
			}
		}
		List<Object[]> result = em.createQuery("SELECT c.pcomd as pcomd, c.pnomd as pnomd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c "
				+ "WHERE " + where
				+ "GROUP BY c.pcomd, c.pnomd").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[0], (String)obj[1]));
		}
		return compras;		
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for(int i = 0; i <c.length; i++) {
			if(where.isEmpty()) {
				where = condiciones.get(c[i]);
			} else {
				where = where + " and " + condiciones.get(c[i]);				
			}
		}
		List<Object[]> result = em.createQuery("SELECT c.pprov as pprov, c.ppnov as ppnov, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c "
				+ "WHERE " + where
				+ "GROUP BY c.pprov, c.ppnov").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras(Integer.parseInt(obj[0].toString()), (String)obj[1],new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN)));
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<Compras> getItems(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for(int i = 0; i <c.length; i++) {
			if(where.isEmpty()) {
				where = condiciones.get(c[i]);
			} else {
				where = where + " and " + condiciones.get(c[i]);				
			}
		}
		List<Object[]> result = em.createQuery("SELECT c.pipro as pipro, c.pides as pides, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c "
				+ "WHERE " + where
				+ "GROUP BY c.pipro, c.pides").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[0], new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[1]));
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<Compras> getClases(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for(int i = 0; i <c.length; i++) {
			if(where.isEmpty()) {
				where = condiciones.get(c[i]);
			} else {
				where = where + " and " + condiciones.get(c[i]);				
			}
		}
		List<Object[]> result = em.createQuery("SELECT c.picla as picla, c.picln as picln, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c "
				+ "WHERE " + where
				+ "GROUP BY c.picla, c.picln").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[0], new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[1]));
		}
		return compras;
	}
	
	@SuppressWarnings("unchecked")
	public List<Compras> getCentros(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for(int i = 0; i <c.length; i++) {
			if(where.isEmpty()) {
				where = condiciones.get(c[i]);
			} else {
				where = where + " and " + condiciones.get(c[i]);				
			}
		}
		List<Object[]> result = em.createQuery("SELECT c.pcent as pcent, c.pcenn as pcenn, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
				+ " FROM Compras as c "
				+ "WHERE " + where
				+ "GROUP BY c.pcent, c.pcenn").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras((String)obj[0], new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), (String)obj[1]));
		}
		return compras;
	}
	
}
