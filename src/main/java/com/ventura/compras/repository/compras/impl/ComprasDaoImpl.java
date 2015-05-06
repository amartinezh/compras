package com.ventura.compras.repository.compras.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.domain.compras.Reporte;
import com.ventura.compras.repository.compras.ComprasDao;

@Repository
public class ComprasDaoImpl implements ComprasDao {

	@PersistenceContext
	private EntityManager em = null;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Compras> getCompras(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.length() == 0) {
					where.append(condiciones.get(c[i]).toString());
				} else {
					where.append(" and " + condiciones.get(c[i]));
				}
			}
		}
		if (where.length() == 0) {
			where.append("c.tipoc='O'");
		} else {
			where.append(" and c.tipoc='O'");
		}
		Compras com = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@", "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd, sum(c.pqtyo) as pqtyo, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ " GROUP BY c.ptype, c.ptyno "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[8].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), (String) obj[0],
					(String) obj[1], new BigDecimal(obj[2].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[7].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			com.sumarCompras(compras.get(compras.size() - 1));
		}
		compras.add(com);
		return compras;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Compras> getCompradores(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel) {
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
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcomd as pcomd, c.pnomd as pnomd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pcomd, c.pnomd "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@", "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[6].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[0],
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[8].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarComprador(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Compras> getProveedores(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel, Compras compra) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (where.length() != 0) {
					where.append(" and ");
				}
				where.append(condiciones.get(c[i]));
			}
		}
		if (compra != null) {
			where.append(" and c.ppnov LIKE '"
					+ compra.getPpnov().toUpperCase() + "%'");

		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		StringBuilder ordenes = new StringBuilder();
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pprov as pprov, c.ppnov as ppnov, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyo) as pqtyo, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, c.pnit as pnit, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pprov, c.ppnov, c.pnit "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(-1000, "@@@@@", new BigDecimal(0).setScale(
				0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			if (compra == null) {
				if (!ordenes.toString().contains((String) obj[1])) {
					if (ordenes.length() == 0) {
						ordenes.append("['" + (String) obj[1] + "'");
					} else {
						ordenes.append(", '" + (String) obj[1] + "'");
					}
				}
			}
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
							BigDecimal.ROUND_HALF_EVEN), (String) obj[8],
					new BigDecimal(obj[9].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[10]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarProveedores(compras.get(compras.size() - 1));
		}
		if (compra == null) {
			ordenes.append("]");
			comp.setNroor(ordenes.toString());
		}
		compras.add(comp);
		return compras;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Compras> getItems(Map<String, String> condiciones, String cond,
			String fechaAct, String fechaSel, Compras compra) {
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
		if (compra != null) {
			where.append(" and c.pipro LIKE '" + compra.getPipro().toUpperCase()
					+ "%'");
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pipro as pipro, c.pides as pides, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, max(c.pprep1) as pprep1, max(c.fecep1) as fecep1, max(c.pprep2) as pprep2, max(c.fecep2) as fecep2, max(c.pprep3) as pprep3, max(c.fecep3) as fecep3, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, c.punid as punid, c.pprov as pprov, c.ppnov as ppnov"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pipro, c.pides, c.punid, c.pprov, c.ppnov "
								+ "ORDER BY pvalbd desc").getResultList();
		StringBuilder ordenes = new StringBuilder("[");
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			if (compra == null) {
				if (!ordenes.toString().contains((String) obj[0])) {
					if (ordenes.length() == 1) {
						ordenes.append("'" + (String) obj[0] + "'");
					} else {
						ordenes.append(", '" + (String) obj[0] + "'");
					}
				}
			}
			compras.add(new Compras(new BigDecimal(obj[2].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[3]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[0], new BigDecimal(obj[4].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(2,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[8], new BigDecimal(obj[9].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[10], new BigDecimal(obj[11].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[12], new BigDecimal(obj[13].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[14].toString()).setScale(2,
							BigDecimal.ROUND_HALF_EVEN), (String) obj[15],
					Integer.parseInt(obj[16].toString()), (String) obj[17]));
			comp.sumarItem(compras.get(compras.size() - 1));
		}
		if (compra == null) {
			ordenes.append("]");
			comp.setNroor(ordenes.toString());
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getClases(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel) {
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
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.picla as picla, c.picln as picln, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.picla, c.picln "
								+ "ORDER BY pvalbd desc").getResultList();
		if (fechaAct.equals(fechaSel)) {

		} else {

		}
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				"Total", new BigDecimal(0).setScale(0,
						BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
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
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[8].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarClases(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getCentros(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel) {
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
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcent as pcent, c.pcenn as pcenn, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.pcent, c.pcenn "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
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
					(String) obj[1], new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[8].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarCentros(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getRequisiciones(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel, Compras compra) {
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
		if (where.length() > 0) {
			where.append(" and ");
		}
		where.append("c.tipoc = 'R'");
		if (compra != null) {
			where.append(" and c.nroor LIKE '"
					+ compra.getNroor().toUpperCase() + "%'");
		}
		StringBuilder ordenes = new StringBuilder("[");
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN));
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, c.fecre as fecre, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.nroor, c.fecre "
								+ "ORDER BY pvalbd desc").getResultList();
		for (Object[] obj : result) {
			if (compra == null) {
				if (ordenes.length() == 1) {
					ordenes.append("'" + (String) obj[0] + "'");
				} else {
					ordenes.append(", '" + (String) obj[0] + "'");
				}
			}
			compras.add(new Compras(new BigDecimal(obj[1].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[2]
					.toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[4]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[6]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN), obj[8]
							.toString(), new BigDecimal(obj[7].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[0], new BigDecimal(obj[9].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarRequesiciones(compras.get(compras.size() - 1));
		}
		if (compra == null) {
			ordenes.append("]");
			comp.setNroor(comp.getNroor() + "-" + ordenes.toString());
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getOrdenes(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel, Compras compra) {
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
		if (where.length() > 0) {
			where.append(" and ");
		}
		where.append("c.tipoc = 'O'");
		if (compra != null) {
			where.append(" and c.nroor LIKE '"
					+ compra.getNroor().toUpperCase() + "%'");

		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		StringBuilder ordenes = new StringBuilder("[");
		List<Object[]> result = em
				.createQuery(
						"SELECT c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, c.fecre as fecre, sum(c.pvalbo) as pvalbo, c.pcstp as pcstp"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.nroor, c.fecre, c.pcstp "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "-1");
		for (Object[] obj : result) {
			if (compra == null) {
				if (ordenes.length() == 1) {
					ordenes.append("'" + (String) obj[0] + "'");
				} else {
					ordenes.append(", '" + (String) obj[0] + "'");
				}
			}
			compras.add(new Compras(new BigDecimal(obj[7].toString()).setScale(
					0, BigDecimal.ROUND_HALF_EVEN), (String) obj[0],
					new BigDecimal(obj[1].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[2]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), obj[8].toString(),
					new BigDecimal(obj[4].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[5]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[6].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN), obj[10]
							.toString()));
			comp.sumarOrdenes(compras.get(compras.size() - 1));
		}
		if (compra == null) {
			ordenes.append("]");
			comp.setNroor(comp.getNroor() + "-" + ordenes.toString());
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getBodegas(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel) {
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
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.plocal as plocal, c.plnon as plnon, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pvalbd) as pvalbd, sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyo) as pqtyo, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where
								+ "GROUP BY c.plocal, c.plnon "
								+ "ORDER BY pvalbd desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", "Total",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
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
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[7]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[8].toString()).setScale(0,
							BigDecimal.ROUND_HALF_EVEN), new BigDecimal(obj[9]
							.toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			comp.sumarBodegas(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Reporte> getReporte(Map<String, String> condiciones,
			String cond, String condRep) {
		StringBuilder where = new StringBuilder();
		for (int i = 2; i < cond.split(",").length; i++) {
			if (!condiciones.get(cond.split(",")[i]).isEmpty()) {
				if (where.length() > 0) {
					where.append(" and ");
				}
				where.append(condiciones.get(cond.split(",")[i]));
			}
		}
		if (where.length() > 0) {
			where.append(" and ");
		}
		where.append(condiciones.get(condRep.split(",")[0]));
		String valores = condiciones.get(condRep.split(",")[1]);
		StringBuilder select = new StringBuilder();
		StringBuilder group = new StringBuilder();
		for (String val : valores.split(",")) {
			if (select.length() > 0) {
				select.append(", ");
				group.append(", ");
			}
			select.append("c." + val + " as " + val);
			group.append("c." + val);
		}
		List<Object[]> result = em.createQuery(
				"Select " + select.toString()
						+ ", c.pmes as pmes, Sum(c."+condiciones.get(condRep.split(",")[2])+") as "+condiciones.get(condRep.split(",")[2])+" "
						+ "From Compras_h as c " + "Where " + where.toString()
						+ " " + "Group by c.pmes, " + group.toString() + " "
						+ "order by 1, 3 asc").getResultList();
		List<Reporte> ret = new LinkedList<Reporte>();
		String marcado = "";
		BigDecimal t = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		// BigDecimal t1 = new BigDecimal(0).setScale(2,
		// BigDecimal.ROUND_HALF_UP);
		Reporte rep = new Reporte("Total", "Total");
		for (Object[] obj : result) {
			if (marcado.isEmpty() || !marcado.equals(obj[1].toString())) {
				if (!marcado.isEmpty() && !marcado.equals(obj[1].toString())) {
					ret.get(ret.size() - 1).getCompras().get(12).setPvalbd(t);
					// t1 = t1.add(t);
					t = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				ret.add(new Reporte(obj[0].toString(), obj[1].toString()));
				marcado = obj[1].toString();
			}
			ret.get(ret.size() - 1)
					.getCompras()
					.get(Integer.parseInt(obj[2].toString()) - 1)
					.setPvalbd(
							new BigDecimal(obj[3].toString()).setScale(2,
									BigDecimal.ROUND_HALF_UP));
			rep.getCompras()
					.get(Integer.parseInt(obj[2].toString()) - 1)
					.setPvalbd(
							rep.getCompras()
									.get(Integer.parseInt(obj[2].toString()) - 1)
									.getPvalbd()
									.add(new BigDecimal(obj[3].toString())
											.setScale(2,
													BigDecimal.ROUND_HALF_UP)));
			rep.getCompras()
					.get(12)
					.setPvalbd(
							rep.getCompras()
									.get(12)
									.getPvalbd()
									.add(new BigDecimal(obj[3].toString())
											.setScale(2,
													BigDecimal.ROUND_HALF_UP)));
			t = t.add(ret.get(ret.size() - 1).getCompras()
					.get(Integer.parseInt(obj[2].toString()) - 1).getPvalbd());
		}
		if(ret.size() > 0) {
			ret.get(ret.size() - 1).getCompras().get(12).setPvalbd(t);
		}
		// rep.getCompras().get(12).setPvalbd(rep.getCompras().get(12).getPvalbd().subtract(t1));
		ret.add(rep);
		return ret;
	}
}
