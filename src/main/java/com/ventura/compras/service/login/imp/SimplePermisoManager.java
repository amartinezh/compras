package com.ventura.compras.service.login.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.login.User;
import com.ventura.compras.repository.login.PermisoDao;
import com.ventura.compras.service.login.PermisoManager;

@Service
public class SimplePermisoManager implements PermisoManager {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermisoDao permisoDao;

	public List<Map<String,String>> getPermisos(User usuario) {
		return permisoDao.getPermisos(usuario);
	}
	
	

}
