package com.ventura.compras.repository.compras;

import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.domain.compras.Reporte;

public interface ComprasDao {

	public List<Compras> getCompras(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getItems(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getClases(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getCentros(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra);
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Compras> getEstados(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel);
	public List<Reporte> getReporte(Map<String, String> condiciones, String cond, String condRep);
	
}
