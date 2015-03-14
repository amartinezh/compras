package com.ventura.compras.repository.adm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ventura.compras.domain.adm.Center;
import com.ventura.compras.repository.adm.CenterDao;

@Repository
public class CenterDaoImpl implements CenterDao {
	
	@PersistenceContext
	private EntityManager em = null;

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Center> listCenter() {
		return em.createQuery("SELECT C FROM Center as C").getResultList();
	}

}
