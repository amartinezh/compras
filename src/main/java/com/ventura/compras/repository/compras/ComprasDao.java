package com.ventura.compras.repository.compras;

import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.compras.Compras;

public interface ComprasDao {

	public List<Compras> getCompras();
	public List<Compras> getCompradores(Map<String, String> condiciones, String cond);
	
}
