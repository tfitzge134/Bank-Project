package com.bank.service.impl;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.service.AccountCRUDService;

public class AccountCRUDServiceImpl implements AccountCRUDService {

	@Override
	public int deleteById(int id) throws BusinessException {
		AccountDAO dao = new AccountDAOImpl();
		return dao.deleteById(id);
	}

}
