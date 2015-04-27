package com.ventura.compras.service.compras;

import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.compras.Compras;

public interface ComprasService {

	public List<Compras> getCompras(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getItems(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getClases(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);	
	public List<Compras> getCentros(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	
}
