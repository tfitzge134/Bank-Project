package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Account;

public interface AccountSearchService {

	public Account getAccountById(int id) throws BusinessException;

	public Account getAccountByAccountNumber(String accountnumber) throws BusinessException;

	public List<Account> getAccountByCustomerId(int customerid) throws BusinessException;
}
