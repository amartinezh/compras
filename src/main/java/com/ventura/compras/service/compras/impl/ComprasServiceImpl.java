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
	
	public List<Compras> getCompras(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getCompras(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getCompradores(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getProveedores(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getItems(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getItems(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getClases(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getClases(condiciones, cond, fechaAct, fechaSel);
	}

	public List<Compras> getCentros(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getCentros(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getRequisiciones(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getOrdenes(condiciones, cond, fechaAct, fechaSel);
	}
	
	public List<Compras> getOrdenesFiltro(Map<String, String> condiciones, String cond, Compras compra, String fechaAct, String fechaSel) {
		return comprasDao.getOrdenesFiltro(condiciones, cond, compra, fechaAct, fechaSel);
	}
	
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel) {
		return comprasDao.getBodegas(condiciones, cond, fechaAct, fechaSel);
	}
	
}
