package com.ventura.compras.service.login;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ventura.compras.domain.login.User;

public interface PermisoManager extends Serializable {

	public List<Map<String,String>> getPermisos(User usuario);
	
}
