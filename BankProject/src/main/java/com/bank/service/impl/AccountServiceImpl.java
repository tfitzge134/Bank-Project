package com.bank.service.impl;

import java.sql.Date;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.TransactionLog;
import com.bank.service.AccountService;
import com.bank.service.TransactionLogCRUDService;
import com.bank.service.TransactionTypes;

public class AccountServiceImpl implements AccountService {

	@Override
	public int deposit(String accountnumber, double amount) throws BusinessException {

		AccountDAO accountDAO = new AccountDAOImpl();
		int c = accountDAO.addDeposit(accountnumber, amount);
		if(c == 1) {
			TransactionLogCRUDService txnLogCRUDService = new TransactionLogCRUDServiceImpl();
			TransactionLog txnLog = new TransactionLog();
			txnLog.setAmount(amount);
			txnLog.setDestAccount(accountnumber);
			txnLog.setTxnDate(new Date(System.currentTimeMillis()));
			txnLog.setTxnType(TransactionTypes.DEPOSIT);
			
			try {
				txnLogCRUDService.createTransactionLog(txnLog);
			} catch (BusinessException e) {
				e.printStackTrace();
				System.out.println("Creating TransactionLog FAILED.");
			}
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int withdraw(String accountnumber, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

}

