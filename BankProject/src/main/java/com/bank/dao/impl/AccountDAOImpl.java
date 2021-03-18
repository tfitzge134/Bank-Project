package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public int addAccount(Account account) {

		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "insert into bank.account (accountType, accountnumber , openingbalance , balance , "
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

		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "update bank.account set balance = (balance + ?) where accountnumber = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newDeposit);

			preparedStatement.setString(2, accountNumber);

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			return 0;
		}
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

	@Override
	public int applyForAccount(int customerid, String accountType, double deposit, Date appliedDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Account> getAppliedNewAccounts() throws BusinessException {
		List<Account> list = new ArrayList<>();
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.account WHERE  "
					+ "status is NULL or status = 'Rejected' ";
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
			System.out.println(e);
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
			e.printStackTrace();
			System.out.println(e);
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
			e.printStackTrace();
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
	}
}
