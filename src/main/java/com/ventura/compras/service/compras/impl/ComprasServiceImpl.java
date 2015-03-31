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
	
	public List<Compras> getCompras(Map<String, String> condiciones, String cond) {
		return comprasDao.getCompras(condiciones, cond);
	}
	
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond) {
		return comprasDao.getCompradores(condiciones, cond);
	}
	
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond) {
		return comprasDao.getProveedores(condiciones, cond);
	}
	
	public List<Compras> getItems(Map<String, String> condiciones, String cond) {
		return comprasDao.getItems(condiciones, cond);
	}
	
	public List<Compras> getClases(Map<String, String> condiciones, String cond) {
		return comprasDao.getClases(condiciones, cond);
	}

	public List<Compras> getCentros(Map<String, String> condiciones, String cond) {
		return comprasDao.getCentros(condiciones, cond);
	}
	
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond) {
		return comprasDao.getRequisiciones(condiciones, cond);
	}
	
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond) {
		return comprasDao.getOrdenes(condiciones, cond);
	}
	
	public List<Compras> getOrdenesFiltro(Map<String, String> condiciones, String cond, Compras compra) {
		return comprasDao.getOrdenesFiltro(condiciones, cond, compra);
	}
	
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond) {
		return comprasDao.getBodegas(condiciones, cond);
	}
	
}
