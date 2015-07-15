package com.ventura.compras.service.adm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.adm.Level;
import com.ventura.compras.repository.adm.LevelDao;
import com.ventura.compras.service.adm.LevelService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelDao levelDao; 
	
	public List<Level> listLevel() {
		return levelDao.listLevel();
	}

}
