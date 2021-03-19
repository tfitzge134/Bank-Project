package com.bank.service;

import com.bank.exception.BusinessException;

public interface AccountService {

	public int deposit(String accountnumber, double amount) throws BusinessException;

	public int withdraw(String accountnumber, double amount) throws BusinessException;

	public int transfer(String sourceAccountNumber, String destAccountNumber, double amount)
			throws BusinessException;
}
