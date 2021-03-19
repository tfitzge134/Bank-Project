package com.bank.dao;

import java.sql.Date;
import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Account;

public interface AccountDAO {
	// CUSTOMER CAN APPLY X NEW ACC
	public int applyForAccount(int customerid, String accountType, double deposit, Date appliedDate);

	// CUSTOMER CAN APPLY X NEW ACC
	public int addAccount(Account account) throws BusinessException;

	// CUSTOMER CAN VIEW BALANCE OF SPECIFIC ACC
	public double viewBalancebyAccNumber(String accountNumber);

	// as a customer I can make a deposit
	public int addDeposit(String accountNumber, double newDeposit) throws BusinessException;

	// as a customer I can make a withdrawl
	public int withdraw(String accountNumber, double amount) throws BusinessException;

	// employee can active account
	public int updateAccount(String accountNumber, String isactive);

	public List<Account> getAllAccounts();

	// employee can get NEWLY APPLIED accounts
	public List<Account> getAppliedNewAccounts() throws BusinessException;

	public int approveAccount(int id, String accountNumber) throws BusinessException;
	
	public int rejectAccount(int id) throws BusinessException;

}
