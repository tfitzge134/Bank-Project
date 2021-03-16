package com.bank.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.app.player.dao.dbutil.PostgresConnection;
import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.service.AccountSearchService;
import com.bank.service.TransferService;

public class TransferServiceImpl implements TransferService {

	@Override
	public String Transfer(String sourceAccountNumber, String destAccountNumber, double amount)
			throws BusinessException {
		Connection connection = null;
		try {
			connection = PostgresConnection.getConnection();
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account sourceAccount = accountSearchService.getAccountByAccountNumber(sourceAccountNumber);
			if (sourceAccount == null) {
				return "INVALID_SOURCE_ACCOUNT_NUMBER";
			}
			if (sourceAccount.getBalance() < amount) {
				return "INSUFFFIENT_BALANACE";
			}
			Account destAccount = accountSearchService.getAccountByAccountNumber(destAccountNumber);
			if (destAccount == null) {
				return "INVALID_DEST_ACCOUNT_NUMBER";
			}

			// DO the transfer as an ATOMIC OPERATION
			connection.setAutoCommit(false);
			AccountDAO accountDAO = new AccountDAOImpl();
			accountDAO.withdrawal(sourceAccountNumber, amount);
			accountDAO.addDeposit(destAccountNumber, amount);
			// Add Txn Log.

			// commit the transaction
			return "SUCCESS";
		} catch (Exception e) {
			// roll back the transaction
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println(e);
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
