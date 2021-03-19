package com.bank.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.TransactionLog;
import com.bank.service.AccountSearchService;
import com.bank.service.AccountService;
import com.bank.service.TransactionLogCRUDService;
import com.bank.service.TransactionTypes;

public class AccountServiceImpl implements AccountService {
	private static Logger log = Logger.getLogger(AccountServiceImpl.class);
	
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
				log.error(e);
				System.out.println("Creating TransactionLog FAILED.");
			}
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int withdraw(String accountnumber, double amount) throws BusinessException {
		AccountDAO accountDAO = new AccountDAOImpl();
		int c = accountDAO.withdraw(accountnumber, amount);
		if(c == 1) {
			TransactionLogCRUDService txnLogCRUDService = new TransactionLogCRUDServiceImpl();
			TransactionLog txnLog = new TransactionLog();
			txnLog.setAmount(amount);
			txnLog.setDestAccount(accountnumber);
			txnLog.setTxnDate(new Date(System.currentTimeMillis()));
			txnLog.setTxnType(TransactionTypes.WITHDRAW);
			
			try {
				txnLogCRUDService.createTransactionLog(txnLog);
			} catch (BusinessException e) {
				log.error(e);
				e.printStackTrace();
				System.out.println("Creating TransactionLog FAILED.");
			}
			return 1;
		}else {
			return 0;
		}
	}


	@Override
	public int transfer(String sourceAccountNumber, String destAccountNumber, double amount)
			throws BusinessException {
		Connection connection = null;
		try {
			connection = PostgresConnection.openConnection();
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account sourceAccount = accountSearchService.getAccountByAccountNumber(sourceAccountNumber);
			if (sourceAccount == null) {
				throw new BusinessException("INVALID_SOURCE_ACCOUNT_NUMBER");
			}
			if (sourceAccount.getBalance() < amount) {
				throw new BusinessException( "INSUFFFIENT_BALANCE");
			}
			//check if account active
			if(!sourceAccount.getIsactive()) {
				throw new BusinessException( "SOURCE_ACCOUNT_NOT_ACTIVE");
			}
			Account destAccount = accountSearchService.getAccountByAccountNumber(destAccountNumber);
			if (destAccount == null) {
				throw new BusinessException( "INVALID_DEST_ACCOUNT_NUMBER");
			}
			//check if account active
			if(!destAccount.getIsactive()) {
				throw new BusinessException( "DEST_ACCOUNT_NOT_ACTIVE");
			}
			// DO the transfer as an ATOMIC OPERATION
			connection.setAutoCommit(false);
			boolean success = false;
			AccountDAO accountDAO = new AccountDAOImpl();
			int c = accountDAO.withdraw(sourceAccountNumber, amount);
			if(c == 1) {
				//Withdrawal Success
				int c2 = accountDAO.addDeposit(destAccountNumber, amount);
				if(c2 == 1) {
					//Deposit Success
					success = true;
				}
			}
			if(success) {
				// commit the transaction
				connection.commit();
			}
			else {
				//failed. Rollback
				connection.rollback();
			}
			// Add Txn Log.
			TransactionLogCRUDService txnLogCRUDService = new TransactionLogCRUDServiceImpl();
			TransactionLog txnLog = new TransactionLog();
			txnLog.setAmount(amount);
			txnLog.setSourceAccount(sourceAccountNumber);
			txnLog.setDestAccount(destAccountNumber);
			txnLog.setTxnDate(new Date(System.currentTimeMillis()));
			txnLog.setTxnType(TransactionTypes.TRANSFER);
			
			try {
				txnLogCRUDService.createTransactionLog(txnLog);
			} catch (BusinessException e) {
				log.error(e);
				e.printStackTrace();
				System.out.println("Creating TransactionLog FAILED.");
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			// roll back the transaction
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
			}
			throw new BusinessException("Internal error");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}

