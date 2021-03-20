package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Account;

public class AccountDAOImpl implements AccountDAO {
	private static Logger log = Logger.getLogger(AccountDAOImpl.class);

	@Override
	public int addAccount(Account account) throws BusinessException {

		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "insert into bank.account (accountType, accountnumber "
					+ ", openingbalance , "
					+ "opendingdate , isactive"
					+ ", customerid ) \n"
					+ "   values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getAccountType());

			preparedStatement.setString(2, account.getAccountnumber());
			preparedStatement.setDouble(3, account.getOpeningbalance());
			preparedStatement.setDate(4, account.getOpendingdate());
			preparedStatement.setBoolean(5, account.getIsactive());
			preparedStatement.setDouble(6, account.getCustomerid());

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception ex) {
			log.error(ex);
			ex.printStackTrace();
			throw new BusinessException("Internal error");
		}

	}

	@Override
	public int addDeposit(String accountNumber, double newDeposit) throws BusinessException {

		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "update bank.account set balance = (balance + ?) where accountnumber = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newDeposit);

			preparedStatement.setString(2, accountNumber);

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception ex) {
			log.error(ex);
			ex.printStackTrace();
			throw new BusinessException("Internal error");
		}
	}

	@Override
	public int withdraw(String accountNumber, double amount) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "update bank.account set balance = (balance - ?) where accountnumber = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, amount);

			preparedStatement.setString(2, accountNumber);

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception ex) {
			log.error(ex);
			ex.printStackTrace();
			throw new BusinessException("Internal error");
		}		
		
	}

	@Override
	public List<Account> getAppliedNewAccounts() throws BusinessException {
		List<Account> list = new ArrayList<>();
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.account WHERE  "
					+ "status is NULL or status = ' '";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("id"));
				account.setCustomerid(resultSet.getInt("customerid"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setOpeningbalance(resultSet.getDouble("openingbalance"));
				list.add(account);
			}
			return list;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}
	}

	@Override
	public int approveAccount(int id, String accountNumber) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "UPDATE bank.account set accountnumber = ?, status = ?, "
					+ "isactive = ? "
					+ ", balance = openingbalance WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accountNumber);
			preparedStatement.setString(2, "Approved");
			preparedStatement.setBoolean(3, true);
			preparedStatement.setInt(4, id);
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}

	}

	@Override
	public int rejectAccount(int id) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "UPDATE bank.account set status = ? "
					+ " WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Rejected");
			preparedStatement.setInt(2, id);
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}
	}

}
