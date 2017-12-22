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
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			
			if (c[i] != null && condiciones.get(c[i]) != null){
					if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
						if (where.length() == 0) {
							where.append(condiciones.get(c[i]).toString());
						} else {
							where.append(" and " + condiciones.get(c[i]));
						}
					}
			}
		}
		System.out.print("WHERE"+where);
		// if (where.length() == 0) {
		// where.append("c.tipoc='O'");
		// } else {
		// where.append(" and c.tipoc='O'");
		// }
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
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd"; // Valor Trans Recibido USD
		} else {
			camp = "pvalbd"; // Valor Base Recibido COP
		}
		// Ordenado:  PVALTO: Valor Trans Ordenado USD
		//            PVALBO Valor Base Ordenado COP
		String sql = "SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd, sum(c.pqori) as pqori, sum(c.pqtyr) as pqtyr, sum(c."
				+ camp
				+ ") as "
				+ camp
				+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
				+ " FROM "
				+ tab
				+ " as c "
				+ "WHERE "
				+ where.toString()
				+ " GROUP BY c.ptype, c.ptyno "
				+ "ORDER BY "
				+ camp + " desc";
		System.out.println("sql "+sql);
		List<Object[]> result = em
				.createQuery(
						"SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd, sum(c.pqori) as pqori, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ " GROUP BY c.ptype, c.ptyno "
								+ "ORDER BY "
								+ camp + " desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		System.out.println("init "+result.toString());
		for (Object[] obj : result) {
			System.out.println("bit "+tab);
			compras.add(new Compras(
						new BigDecimal(obj[8].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
						(String) obj[0],
						(String) obj[1], 
					    new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					    new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					    new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					    new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					    new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					    new BigDecimal(obj[7].toString()).setScale(0,BigDecimal.ROUND_HALF_EVEN), 
					    new BigDecimal(obj[9].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			com.sumarCompras(compras.get(compras.size() - 1));
		}
		compras.add(com);
		return compras;
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<Compras> getCompradores(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcomd as pcomd, c.pnomd as pnomd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pcomd, c.pnomd "
								+ "ORDER BY "
								+ camp + " desc").getResultList();
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
			String cond, String fechaAct, String fechaSel, Compras compra,
			String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() != 0) {
						where.append(" and ");
					}
					where.append(condiciones.get(c[i]));
				}
			}
		}
		if (compra != null) {
			where.append(" and c.ppnov LIKE '%"
					+ compra.getPpnov().toUpperCase() + "%'");

		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		StringBuilder ordenes = new StringBuilder();
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pprov as pprov, c.ppnov as ppnov, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqori) as pqori, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, c.pnit as pnit, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pprov, c.ppnov, c.pnit "
								+ "ORDER BY " + camp + " desc").getResultList();
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
			compras.add(new Compras(
					Integer.parseInt(obj[0].toString()),
					(String) obj[1], 
					new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[3].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					new BigDecimal(obj[4].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[5].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					new BigDecimal(obj[6].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
					new BigDecimal(obj[7].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					(String) obj[8],
					new BigDecimal(obj[9].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN), 
					new BigDecimal(obj[10].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN)));
			
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
			String fechaAct, String fechaSel, Compras compra, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		if (compra != null) {
			where.append(" and c.pipro LIKE '%"
					+ compra.getPipro().toUpperCase() + "%'");
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pipro as pipro, c.pides as pides, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, max(c.pprep1) as pprep1, max(c.fecep1) as fecep1, max(c.pprep2) as pprep2, max(c.fecep2) as fecep2, max(c.pprep3) as pprep3, max(c.fecep3) as fecep3, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, c.punid as punid, c.pprov as pprov, c.ppnov as ppnov, c.pcstp as pcstp, c.tipoc as tipoc, c.fecen as fecen, Min(c.diave) as diave, c.solic as solic, sum(c.pqtyo) as pqtyo, c.fecre as fecre, sum(c.pvalbo) as pvalbo "
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pipro, c.pides, c.punid, c.pprov, c.ppnov, c.pcstp, c.tipoc, c.fecen, c.solic, c.fecre "
								+ "ORDER BY " + camp + " desc").getResultList();
		StringBuilder ordenes = new StringBuilder("[");
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("@@@@@", new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(2,
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
			String valorEst = "";
			if (obj[19].toString().equals("O")) {
				if (obj[18].toString().equals("0")) {
					valorEst = "Abierta";
				} else if (obj[18].toString().equals("1")) {
					valorEst = "Recibida";
				} else if (obj[18].toString().equals("2")) {
					valorEst = "Parcialmente costeada";
				} else if (obj[18].toString().equals("3")) {
					valorEst = "Cerrada";
				} else {
					valorEst = "";
				}
			} else {
				if (obj[18].toString().equals("0")) {
					valorEst = "Emitido";
				} else if (obj[18].toString().equals("1")) {
					valorEst = "Pendiente";
				} else if (obj[18].toString().equals("2")) {
					valorEst = "Tramitada";
				} else if (obj[18].toString().equals("3")) {
					valorEst = "Ordenada";
				} else {
					valorEst = "";
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
					Integer.parseInt(obj[16].toString()), (String) obj[17],
					(String) obj[18], valorEst, (String) obj[20],
					(String) obj[21], (String) obj[22], new BigDecimal(obj[23]
							.toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[24], new BigDecimal(obj[25].toString())
							.setScale(2, BigDecimal.ROUND_HALF_EVEN)));
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
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.picla as picla, c.picln as picln, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.picla, c.picln "
								+ "ORDER BY "
								+ camp + " desc").getResultList();
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
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcent as pcent, c.pcenn as pcenn, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pcent, c.pcenn "
								+ "ORDER BY "
								+ camp + " desc").getResultList();
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
			String cond, String fechaAct, String fechaSel, Compras compra,
			String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !c[i].equals("ordeCond")
					&& !condiciones.get(c[i]).isEmpty()) {
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
			where.append(" and c.nroor LIKE '%"
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
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				"");
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, min(c.fecre) as fecre, sum(c.pvalbo) as pvalbo, min(c.pcstp) as pcstp, solic as solic"
								+ " FROM " + tab + " as c " + "WHERE "
								+ where.toString()
								+ "GROUP BY c.nroor, c.solic " + "ORDER BY "
								+ camp + " desc").getResultList();
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
							.setScale(0, BigDecimal.ROUND_HALF_EVEN), obj[10]
							.toString(), obj[11].toString()));
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
			String cond, String fechaAct, String fechaSel, Compras compra,
			String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
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
			where.append(" and c.nroor LIKE '%"
					+ compra.getNroor().toUpperCase() + "%'");

		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		StringBuilder ordenes = new StringBuilder("[");
		List<Object[]> result = em
				.createQuery(
						"SELECT c.nroor as nroor, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, min(c.fecre) as fecre, sum(c.pvalbo) as pvalbo, MIN(c.pcstp) as pcstp, sum(c.pqtyo) as pqtyo, max(c.fecen) as fecen, max(c.diave) as diave, c.solic as solic"
								+ " FROM " + tab + " as c " + "WHERE "
								+ where.toString()
								+ "GROUP BY c.nroor, c.solic " + "ORDER BY "
								+ camp + " desc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras(new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), "@@@@@",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN),
				"-1",
				new BigDecimal(0).setScale(0, BigDecimal.ROUND_HALF_EVEN), "",
				"", "");
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
							.toString(), new BigDecimal(obj[11].toString())
							.setScale(0, BigDecimal.ROUND_HALF_EVEN),
					(String) obj[12], (String) obj[13], (String) obj[14]));
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
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.plocal as plocal, c.plnon as plnon, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalpo) as pvalpo, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, sum(c.pvalbo) as pvalbo"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.plocal, c.plnon "
								+ "ORDER BY "
								+ camp + " desc").getResultList();
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
	public List<Compras> getEstados(Map<String, String> condiciones,
			String cond, String fechaAct, String fechaSel, String campo) {
		String[] c = cond.split(",");
		StringBuilder where = new StringBuilder();
		if (campo.toUpperCase().equals("ORD")) {
			where.append("c.pqtyo <> 0");
		} else {
			where.append("(c.pqtyd <> 0 or c.pqtyr <> 0 or c.pqtyp <> 0)");
		}
		for (int i = 0; i < c.length; i++) {
			if (!c[i].isEmpty() && !condiciones.get(c[i]).isEmpty()) {
				if (!cond.contains("Req") || !c[i].equals("ordeCond")) {
					if (where.length() == 0) {
						where.append(condiciones.get(c[i]));
					} else {
						where.append(" and " + condiciones.get(c[i]));
					}
				}
			}
		}
		String tab = "";
		if (fechaAct.equals(fechaSel)) {
			tab = "Compras";
		} else {
			tab = "Compras_h";
		}
		String camp = null;
		if (where.toString().contains("USD")) {
			camp = "pvaltd";
		} else {
			camp = "pvalbd";
		}
		List<Object[]> result = em
				.createQuery(
						"SELECT c.pcstp as pcstp, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqori) as pqori, sum(c.pqtyp) as pqtyp, sum(c."
								+ camp
								+ ") as "
								+ camp
								+ ", sum(c.pvalbo) as pvalbo, c.tipoc as tipoc"
								+ " FROM "
								+ tab
								+ " as c "
								+ "WHERE "
								+ where.toString()
								+ "GROUP BY c.pcstp, c.tipoc "
								+ "ORDER BY c.pcstp asc").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		Compras comp = new Compras("10", "Total", new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN), new BigDecimal(0).setScale(0,
				BigDecimal.ROUND_HALF_EVEN));
		for (Object[] obj : result) {
			String valorEst = "";
			if (obj[7].toString().equals("O")) {
				if (obj[0].toString().equals("0")) {
					valorEst = "Abierta";
				} else if (obj[0].toString().equals("1")) {
					valorEst = "Recibida";
				} else if (obj[0].toString().equals("2")) {
					valorEst = "Parcialmente costeada";
				} else if (obj[0].toString().equals("3")) {
					valorEst = "Cerrada";
				} else {
					valorEst = "";
				}
			} else {
				if (obj[0].toString().equals("0")) {
					valorEst = "Emitido";
				} else if (obj[0].toString().equals("1")) {
					valorEst = "Pendiente";
				} else if (obj[0].toString().equals("2")) {
					valorEst = "Tramitada";
				} else if (obj[0].toString().equals("3")) {
					valorEst = "Ordenada";
				} else {
					valorEst = "";
				}
			}
			compras.add(new Compras((String) obj[0], valorEst, new BigDecimal(
					obj[1].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN),
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
			comp.sumarEstados(compras.get(compras.size() - 1));
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
				"Select " + select.toString() + ", c.pmes as pmes, Sum(c."
						+ condiciones.get(condRep.split(",")[2]) + ") as "
						+ condiciones.get(condRep.split(",")[2]) + " "
						+ "From Compras_h as c " + "Where " + where.toString()
						+ " " + "Group by c.pmes, " + group.toString() + " "
						+ "order by 1, 3 asc").getResultList();
		result.addAll(em.createQuery(
				"Select " + select.toString() + ", c.pmes as pmes, Sum(c."
						+ condiciones.get(condRep.split(",")[2]) + ") as "
						+ condiciones.get(condRep.split(",")[2]) + " "
						+ "From Compras as c " + "Where " + where.toString()
						+ " " + "Group by c.pmes, " + group.toString() + " "
						+ "order by 1, 3 asc").getResultList());
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
		if (ret.size() > 0) {
			ret.get(ret.size() - 1).getCompras().get(12).setPvalbd(t);
		}
		// rep.getCompras().get(12).setPvalbd(rep.getCompras().get(12).getPvalbd().subtract(t1));
		ret.add(rep);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getOrdenesHistoricos(String condicion) {
		List<Compras> ret = new LinkedList<Compras>();
		List<Object[]> result = em
				.createQuery(
						"Select c.nroor as nroor, sum(c.pvalbd) as pvalbd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyp) as pqtyp, sum(c.ppreac) as ppreac, sum(c.pqori) as pqori, max(c.fecen) as fecen, max(c.fecre) as fecre, max(c.diave) as diave, c.solic as solic, "
								+ "max(c.pprep1) as pprep1, max(c.pprep2) as pprep2, max(c.pprep3) as pprep3, max(c.fecep1) as fecep1, max(c.fecep2) as fecep2, max(c.fecep3) as fecep3, min(c.pcstp) as pcstp "
								+ "From Compras_h as c "
								+ "Where c.tipoc = 'O' and c.pqtyd <> 0 and "
								+ condicion
								+ " Group by c.nroor, c.solic order by 9 desc")
				.getResultList();
		Compras comp = new Compras("Total", new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), "", "", "", "",
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP), "",
				"", "", "-1");
		for (Object[] obj : result) {
			ret.add(new Compras(obj[0].toString(), new BigDecimal(obj[1]
					.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[2].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[3]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[4].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[5]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[6].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), obj[7].toString(),
					obj[8].toString(), obj[9].toString(), obj[10].toString(),
					new BigDecimal(obj[11].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[12]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[13].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), obj[14].toString(),
					obj[15].toString(), obj[16].toString(), obj[17].toString()));
			comp.sumarOrdenesHistorico(ret.get(ret.size() - 1));
		}
		ret.add(comp);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getCompradoresHistoricos(String condicion) {
		List<Compras> ret = new LinkedList<Compras>();
		List<Object[]> result = em
				.createQuery(
						"Select c.pcomd as pcomd, c.pnomd as pnomd, sum(c.pvalbd) as pvalbd, max(c.pvalpo) as pvalpo, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyp) as pqtyp "
								+ "From Compras_h as c "
								+ "Where c.tipoc = 'O' and c.pqtyd <> 0 and "
								+ condicion
								+ " Group by c.pcomd, c.pnomd order by 1 asc")
				.getResultList();
		Compras comp = new Compras("@@@@@", "Total",
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));
		for (Object[] obj : result) {
			ret.add(new Compras(obj[0].toString(), obj[1].toString(),
					new BigDecimal(obj[2].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[3]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[4].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[5]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[6].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP)));
			comp.sumarCompradoresHistorico(ret.get(ret.size() - 1));
		}
		ret.add(comp);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getCentrosHistoricos(String condicion) {
		List<Compras> ret = new LinkedList<Compras>();
		List<Object[]> result = em
				.createQuery(
						"Select c.pcent as pcent, c.pcenn as pcenn, sum(c.pvalbd) as pvalbd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyp) as pqtyp "
								+ "From Compras_h as c "
								+ "Where c.tipoc = 'O' and c.pqtyd <> 0 and "
								+ condicion
								+ " Group by c.pcent, c.pcenn order by 1 asc")
				.getResultList();
		Compras comp = new Compras(new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), "@@@@@", "Total");
		for (Object[] obj : result) {
			ret.add(new Compras(new BigDecimal(obj[2].toString()).setScale(2,
					BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[3].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[4]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[5].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), obj[0].toString(),
					obj[1].toString()));
			comp.sumarCentrosHistorico(ret.get(ret.size() - 1));
		}
		ret.add(comp);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getEstadosHistoricos(String condicion) {
		List<Compras> ret = new LinkedList<Compras>();
		List<Object[]> result = em
				.createQuery(
						"Select c.pcstp as pcstp, sum(c.pvalbd) as pvalbd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyp) as pqtyp "
								+ "From Compras_h as c "
								+ "Where c.tipoc = 'O' and c.pqtyd <> 0 and "
								+ condicion
								+ " Group by c.pcstp order by 1 asc")
				.getResultList();
		Compras comp = new Compras("-1", new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP), "Total");
		for (Object[] obj : result) {
			String valorEst;
			if (obj[0].toString().equals("0")) {
				valorEst = "Abierta";
			} else if (obj[0].toString().equals("1")) {
				valorEst = "Recibida";
			} else if (obj[0].toString().equals("2")) {
				valorEst = "Parcialmente costeada";
			} else if (obj[0].toString().equals("3")) {
				valorEst = "Cerrada";
			} else {
				valorEst = "";
			}
			ret.add(new Compras(obj[0].toString(), new BigDecimal(obj[1]
					.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[2].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[3]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[4].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), valorEst));
			comp.sumarEstadosHistorico(ret.get(ret.size() - 1));
		}
		ret.add(comp);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> getHistorico(List<String> condiciones, boolean ind) {
		List<Compras> compras = new LinkedList<Compras>();
		String g = "";
		String s = "";
		if (ind) {
			g = "c.pprov, c.ppnov, ";
			s = ", c.pprov as pprov, c.ppnov as ppnov";
		} else {
			g = "c.pipro, c.pides, ";
			s = ", c.pipro as pipro, c.pides as pides, max(c.pprep1) as pprep1, max(c.pprep2) as pprep2, max(c.pprep3) as pprep3, max(c.fecep1) as fecep1, max(c.fecep2) as fecep2, max(c.fecep3) as fecep3";
		}
		List<Object[]> result = em
				.createQuery(
						"Select "
								+ condiciones.get(1)
								+ ", sum(c.pvalbd) as pvalbd, sum(c.pqtyd) as pqtyd, sum(c.pqtyr) as pqtyr, sum(c.pqtyp) as pqtyp"
								+ s
								+ " From Compras_h as c Where c.tipoc = 'O' and c.pqtyd <> 0 and "
								+ condiciones.get(0) + " Group by " + g
								+ condiciones.get(2) + " Order by 1 asc")
				.getResultList();
		Compras comp = new Compras("@@@@@", "Total",
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP), "",
				"", new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP),
				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP), "",
				"", "", 0.0);
		for (Object[] obj : result) {
			String cod = "";
			String nom = "";
			BigDecimal p1 = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			BigDecimal p2 = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			BigDecimal p3 = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			String f1 = "";
			String f2 = "";
			String f3 = "";
			cod = obj[6].toString();
			nom = obj[7].toString();
			if (!ind) {
				p1= new BigDecimal(obj[8].toString()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				p2= new BigDecimal(obj[9].toString()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				p3= new BigDecimal(obj[10].toString()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				f1 = obj[11].toString();
				f2 = obj[12].toString();
				f3 = obj[13].toString();
			}
			compras.add(new Compras(obj[0].toString(), obj[1].toString(),
					new BigDecimal(obj[2].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[3]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(obj[4].toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP), new BigDecimal(obj[5]
							.toString()).setScale(2, BigDecimal.ROUND_HALF_UP),
					cod, nom, p1, p2, p3, f1, f2, f3, 0.0));
			comp.sumarHistorico(compras.get(compras.size() - 1));
		}
		compras.add(comp);
		return compras;
	}
}
