package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Account;

public interface AccountCRUDService {
	// * As a customer, I can apply for a new bank account with a starting balance.
	public int createAccount(Account account) throws BusinessException;
}
