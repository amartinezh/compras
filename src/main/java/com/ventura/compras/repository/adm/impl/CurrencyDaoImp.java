package com.ventura.compras.repository.adm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ventura.compras.domain.adm.Currency;
import com.ventura.compras.repository.adm.CurrencyDao;

@Repository
public class CurrencyDaoImp implements CurrencyDao
{
	
	@PersistenceContext
	private EntityManager em = null;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Currency> listCurrency() {
		return em.createQuery("SELECT C FROM Currency as C").getResultList();
	}

}
