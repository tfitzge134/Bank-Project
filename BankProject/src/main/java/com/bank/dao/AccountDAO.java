package com.bank.dao;
import java.util.List;

import com.bank.model.Account;

public interface AccountDAO {
	//CUSTOMER CAN APPLY X NEW ACC
	public int addAccount(Account account);
	//CUSTOMER CAN VIEW BALANCE OF SPECIFIC ACC
	public int viewBalancebyAccNumber(String accountNumber, double balance);
	//as a customer I can make a deposit
	public int addDeposit(String accountNumber, double newDeposit);
	//as a customer I can make a withdrawl
	public int withdrawal(String accountNumber, double newWithdrawl);
	//employee can active account
	public int updateAccount(String accountNumber, 
			String isactive);
	public List<Account>getAllAccounts();
	
	
	
	
		
	

}
