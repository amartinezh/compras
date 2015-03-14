package com.ventura.compras.service.adm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.adm.Center;
import com.ventura.compras.repository.adm.CenterDao;
import com.ventura.compras.service.adm.CenterService;

@Service
public class CenterServiceImpl implements CenterService {

	@Autowired
	private CenterDao centerDao;
	
	public List<Center> listCenter() {
		return centerDao.listCenter();
	}
	
}
