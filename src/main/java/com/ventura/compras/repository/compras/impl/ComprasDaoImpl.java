package com.ventura.compras.repository.compras.impl;

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
	
	public List<Compras> getCompras() {
		// TODO Auto-generated method stub
		return null;
	}

}
