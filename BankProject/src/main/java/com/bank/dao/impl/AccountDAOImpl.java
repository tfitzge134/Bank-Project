package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public int addAccount(Account account) {

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into account.account (accountType, accountnumber , openingbalance , balance , "
					+ "opendingdate , isactive , deposit, withdrawl, interestrate ) \n"
					+ "   values (?, ?, ?, ?, ?, ?, ?,  ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getAccountType());

			preparedStatement.setString(2, account.getAccountnumber());
			preparedStatement.setDouble(3, account.getOpeningbalance());
			preparedStatement.setDouble(4, account.getBalance());
			preparedStatement.setDate(5, account.getOpendingdate());
			preparedStatement.setBoolean(6, account.getIsactive());
			preparedStatement.setDouble(7, account.getDeposit());
			preparedStatement.setDouble(8, account.getWithdrawl());
			preparedStatement.setDouble(9, account.getInteresrate());

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			if (message.contains("ERROR: duplicate key")) {
				System.out.println("The Primary KEY (Person Id) already exists: " + account.getAccountnumber());
			} else {
				System.out.println(ex);
			}
			return 0;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public double viewBalancebyAccNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDeposit(String accountNumber, double newDeposit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int withdrawal(String accountNumber, double newWithdrawl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccount(String accountNumber, String isactive) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
}
