package com.ventura.compras.repository.adm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ventura.compras.domain.adm.Level;
import com.ventura.compras.repository.adm.LevelDao;

@Repository
public class LevelDaoImp implements LevelDao {
	
	@PersistenceContext
	private EntityManager em = null;

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Level> listLevel() {
		return em.createQuery("SELECT C FROM Level as C").getResultList();
	}

}
