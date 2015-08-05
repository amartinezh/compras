package com.ventura.compras.service.compras.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.domain.compras.Reporte;
import com.ventura.compras.repository.compras.ComprasDao;
import com.ventura.compras.service.compras.ComprasService;

@Service
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	private ComprasDao comprasDao;
	
	public List<Compras> getCompras(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getCompras(condiciones, cond, fechaAct, fechaSel, campo);
	}
	
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getCompradores(condiciones, cond, fechaAct, fechaSel, campo);
	}
	
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo) {
		return comprasDao.getProveedores(condiciones, cond, fechaAct, fechaSel, compra, campo);
	}
	
	public List<Compras> getItems(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo) {
		return comprasDao.getItems(condiciones, cond, fechaAct, fechaSel, compra, campo);
	}
	
	public List<Compras> getClases(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getClases(condiciones, cond, fechaAct, fechaSel, campo);
	}

	public List<Compras> getCentros(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getCentros(condiciones, cond, fechaAct, fechaSel, campo);
	}
	
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo) {
		return comprasDao.getRequisiciones(condiciones, cond, fechaAct, fechaSel, compra, campo);
	}
	
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo) {
		return comprasDao.getOrdenes(condiciones, cond, fechaAct, fechaSel, compra, campo);
	}
		
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getBodegas(condiciones, cond, fechaAct, fechaSel, campo);
	}
	
	public List<Compras> getEstados(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo) {
		return comprasDao.getEstados(condiciones, cond, fechaAct, fechaSel, campo);
	}
	
	public List<Reporte> getReporte(Map<String, String> condiciones, String cond, String condRep) {
		return comprasDao.getReporte(condiciones, cond, condRep);		
	}
	
	public List<Compras> getHistorico(List<String> condiciones, boolean ind) {
		return comprasDao.getHistorico(condiciones, ind);
	}
	
	public List<Compras> getOrdenesHistoricos(String condicion) {
		return comprasDao.getOrdenesHistoricos(condicion);
	}
	
	public List<Compras> getCompradoresHistoricos(String condicion) {
		return comprasDao.getCompradoresHistoricos(condicion);
	}
	
	public List<Compras> getCentrosHistoricos(String condicion) {
		return comprasDao.getCentrosHistoricos(condicion);
	}
	public List<Compras> getEstadosHistoricos(String condicion) {
		return comprasDao.getEstadosHistoricos(condicion);
	}
}
