package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Person;

public class PersonDAOImpl implements com.bank.dao.PersonDAO {

	public int addPerson(Person person) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "INSERT INTO bank.person(\n"
					+ "firstname, lastname, email, password, phonenumber, dob, isEmployee) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, person.getFirstname());
			preparedStatement.setString(2, person.getLastname());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getPassword());
			preparedStatement.setString(5, person.getPhonenumber());

			Date dobDate = Date.valueOf(person.getDob());
			preparedStatement.setDate(6, dobDate);
			preparedStatement.setBoolean(7, person.isEmployee());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
			new BusinessException(ex.getMessage());
//			String message = ex.getMessage();
//			if (message.contains("ERROR: duplicate key")) {
//				System.out.println("The Primary KEY (Person Id) already exists: " + person.getId());
//			} else {
//				System.out.println(ex);
//			}
//			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}
		return 0;
	}

	@Override
	public boolean isExisting(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int logout(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Person verifyPassword(String email, String password) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.person WHERE email = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Person person = new Person();
				//firstname, lastname, email, password, phonenumber, dob, isEmployee
				person.setId(resultSet.getInt("id"));
				person.setFirstname(resultSet.getString("firstname"));
				person.setLastname(resultSet.getString("lastname"));
				person.setEmail(resultSet.getString("email"));
				person.setEmployee(resultSet.getBoolean("isEmployee"));
				return person;
			}
			return null;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
	}

}
