package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Account;

public interface AccountCRUDService {
	//* As a customer, I can apply for a new bank account with a starting balance.
	public int createAccount(Account account)throws BusinessException;
	public int updateAccount(int acnumber,String email)throws BusinessException;
	public int deleteAccount(int id)throws BusinessException;
	public List<Account> getAllAccounts()throws BusinessException;
}


