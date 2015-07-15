package com.ventura.compras.service.adm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura.compras.domain.adm.Currency;
import com.ventura.compras.repository.adm.CurrencyDao;
import com.ventura.compras.service.adm.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;
	
	public List<Currency> listCurrency() {
		return currencyDao.listCurrency();
	}

}
