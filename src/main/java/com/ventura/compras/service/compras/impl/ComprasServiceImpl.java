package com.ventura.compras.service.compras.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.repository.compras.ComprasDao;
import com.ventura.compras.service.compras.ComprasService;

@Service
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	private ComprasDao comprasDao;
	
	public List<Compras> getCompras() {
		return comprasDao.getCompras();
	}
	
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond) {
		return comprasDao.getCompradores(condiciones, cond);
	}

	
}
