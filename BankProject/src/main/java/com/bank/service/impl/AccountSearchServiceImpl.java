package com.bank.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.service.AccountSearchService;

public class AccountSearchServiceImpl implements AccountSearchService {

	@Override
	public Account getAccountById(int id) throws BusinessException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "SELECT  (accountType, accountnumber , balance , " + "isactive) "

					+ " FROM bank.account WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setAccountType(resultSet.getString("accountType"));
				account.setAccountnumber(resultSet.getString("accountnumber"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setIsactive(resultSet.getBoolean("isactive"));
				return account;
			}
			return null;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
	}

	@Override
	public Account getAccountByAccountNumber(String accountnumber) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.account WHERE accountnumber = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accountnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setAccountType(resultSet.getString("accountType"));
				account.setAccountnumber(resultSet.getString("accountnumber"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setIsactive(resultSet.getBoolean("isactive"));
				return account;
			}
			return null;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
	}

}
