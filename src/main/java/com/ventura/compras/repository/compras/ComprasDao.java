package com.ventura.compras.repository.compras;

import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.compras.Compras;

public interface ComprasDao {

	public List<Compras> getCompras(Map<String, String> condiciones, String cond);
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond);
	public List<Compras> getProveedores(Map<String, String> condiciones, String cond);
	public List<Compras> getItems(Map<String, String> condiciones, String cond);
	public List<Compras> getClases(Map<String, String> condiciones, String cond);
	public List<Compras> getCentros(Map<String, String> condiciones, String cond);
	
}
