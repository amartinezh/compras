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
	public List<Compras> getCompras(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		Compras com = new Compras("@@@@@", "Total", new BigDecimal(0).setScale(
				0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN));
		List<Object[]> result = em
				.createQuery(
						"SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd, sum(c.pqtyo) as pqtyo, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ " GROUP BY c.ptype, c.ptyno").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for (Object[] obj : result) {
			compras.add(new Compras((String) obj[0], (String) obj[1],
					new BigDecimal(obj[2].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[6].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[7]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			com.sumarCompras(compras.get(compras.size() - 1));
		}
		compras.add(com);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getCompradores(Map<String, String> condiciones,
			String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcomd as pcomd, c.pnomd as pnomd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pcomd, c.pnomd").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@", "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),

			new BigDecimal(obj[4].toString()).setScale(0,
					BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[6].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[0],
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[8].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarComprador(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getProveedores(Map<String, String> condiciones,
			String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pprov as pprov, c.ppnov as ppnov, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, c.pnit as pnit"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pprov, c.ppnov, c.pnit")
				.getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(-1000, "@@@@@", new BigDecimal(0).setScale(
				0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "");
		for (Object[] obj : result) {
			compras.add(new Compras(Integer.parseInt(obj[0].toString()),
					(String) obj[1], new BigDecimal(obj[2].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[7].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[7]));
			comp.sumarProveedores(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getItems(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pipro as pipro, c.pides as pides, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, avg(c.pprep1) as pprep1, min(c.fecep1) as fecep1, avg(c.pprep2) as pprep2, min(c.fecep2) as fecep2, avg(c.pprep3) as pprep3, min(c.fecep3) as fecep3, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, c.punin as punin"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pipro, c.pides, c.punin")
				.getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				"Total", new BigDecimal(0).setScale(0,
						BigDecimal.ROUND_HALF_EVEN), "",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "");
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[0], new BigDecimal(obj[4].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[8], new BigDecimal(obj[9].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[10], new BigDecimal(obj[11].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[12], new BigDecimal(obj[13].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[14].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[15]));
			comp.sumarItem(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getClases(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.picla as picla, c.picln as picln, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.picla, c.picln").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				"Total");
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), (String) obj[0],
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[1]));
			comp.sumarClases(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getCentros(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.isEmpty()) {
					where = condiciones.get(c[i]);
				} else {
					where = where + " and " + condiciones.get(c[i]);
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcent as pcent, c.pcenn as pcenn, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pcent, c.pcenn").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "Total");
		for (Object[] obj : result) {
			compras.add(new Compras((String) obj[0], new BigDecimal(obj[2]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[1]));
			comp.sumarCentros(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getRequisiciones(Map<String, String> condiciones,
			String cond) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.length() == 0) {
					where.append(condiciones.get(c[i]));
				} else {
					where.append(" and " + condiciones.get(c[i]));
				}
			}
		}
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@");
		List<Object[]> result = em
				.createQuery(
						"SELECT c.tipoc as tipoc, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.tipoc").getResultList();
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[1].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[2]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[0]));
			comp.sumarRequesiciones(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.length() == 0) {
					where.append(condiciones.get(c[i]));
				} else {
					where.append(" and " + condiciones.get(c[i]));
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.nroor").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			compras.add(new Compras((String) obj[0], new BigDecimal(obj[1]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[2].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarOrdenes(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.length() == 0) {
					where.append(condiciones.get(c[i]));
				} else {
					where.append(" and " + condiciones.get(c[i]));
				}
			}
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.plocal as plocal, c.plnon as plnon, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac"
								+ " FROM Compras as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.plocal, c.plnon").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			compras.add(new Compras((String) obj[0], (String) obj[1],
					new BigDecimal(obj[2].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[6].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarBodegas(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

}
