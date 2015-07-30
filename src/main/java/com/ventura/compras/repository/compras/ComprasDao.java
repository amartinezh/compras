package com.ventura.compras.repository.compras;

import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.compras.Compras;
import com.ventura.compras.domain.compras.Reporte;

public interface ComprasDao {

	public List<Compras> getCompras(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo);
	public List<Compras> getItems(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo);
	public List<Compras> getClases(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Compras> getCentros(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Compras> getRequisiciones(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo);
	public List<Compras> getOrdenes(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, Compras compra, String campo);
	public List<Compras> getBodegas(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Compras> getEstados(Map<String, String> condiciones, String cond, String fechaAct, String fechaSel, String campo);
	public List<Reporte> getReporte(Map<String, String> condiciones, String cond, String condRep);
	public List<Compras> getHistorico(List<String> condiciones);
	public List<Compras> getOrdenesHistoricos(String condicion);
	public List<Compras> getCompradoresHistoricos(String condicion);
	public List<Compras> getCentrosHistoricos(String condicion);
	public List<Compras> getEstadosHistoricos(String condicion);
	
}
