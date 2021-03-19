package com.bank.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.main.BankMain;
import com.bank.model.Account;
import com.bank.service.AccountSearchService;

public class AccountSearchServiceImpl implements AccountSearchService {
	private static Logger log = Logger.getLogger(AccountSearchServiceImpl.class);
	
	@Override
	public Account getAccountById(int id) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
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
			e.printStackTrace();
			log.error(e);
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
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}
	}

	@Override
	public List<Account> getAccountByCustomerId(int customerid) throws BusinessException {
		List<Account> accounts = new ArrayList<>();
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.account WHERE customerid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setCustomerid(resultSet.getInt("customerid"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setAccountnumber(resultSet.getString("accountnumber"));
				account.setStatus(resultSet.getString("status"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setOpeningbalance(resultSet.getDouble("openingbalance"));
				account.setIsactive(resultSet.getBoolean("isactive"));
				accounts.add(account);
			}
			return accounts;
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}
	}

}
