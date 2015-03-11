package com.ventura.compras.repository.compras.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
		List<Object[]> result = em.createQuery("SELECT c.ptype as ptype, c.ptyno as ptyno, sum(c.pqtyd) as pqtyd FROM Compras as c GROUP BY c.ptype, c.ptyno").getResultList();
		List<Compras> compras = new LinkedList<Compras>();
		for(Object[] obj: result) {
			compras.add(new Compras((String)obj[0], (String)obj[1], new BigDecimal(obj[2].toString()).setScale(0, BigDecimal.ROUND_HALF_EVEN)));
		}
		return compras;		
	}

}
