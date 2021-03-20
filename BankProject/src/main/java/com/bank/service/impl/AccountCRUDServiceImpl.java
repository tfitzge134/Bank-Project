package com.bank.service.impl;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.service.AccountCRUDService;

public class AccountCRUDServiceImpl implements AccountCRUDService {

	@Override
	public int createAccount(Account account) throws BusinessException {
		if (!AccountValidations.isValidAccountType(account.getAccountType())) {
			throw new BusinessException("Entered accountType " + account.getAccountType() + " is invalid.");
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.addAccount(account);
	}

}
