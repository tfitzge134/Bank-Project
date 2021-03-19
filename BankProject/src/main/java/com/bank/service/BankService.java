package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Person;

public interface BankService {
	public Person login(String email, String password) throws BusinessException;

	public int addAccount(Account account) throws BusinessException;
	
	public int applyForNewAccount(Account account) throws BusinessException;

	public int approveAccount(int id, String accountNumber) throws BusinessException;

	public int rejectAccount(int id) throws BusinessException;

	// employee can get NEWLY APPLIED accounts
	public List<Account> getAppliedNewAccounts() throws BusinessException;

}
