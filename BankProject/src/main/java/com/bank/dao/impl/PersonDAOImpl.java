package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.dao.dbutil.PostgresConnection;
import com.bank.model.Person;

public class PersonDAOImpl implements com.bank.dao.PersonDAO {

	public int addPerson(Person person) {
//		String sql = "INSERT INTO person(\n"
//				+ "	personid, firstname, lastname, email, phonenumber, occupation, dob, password)\n"
//				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "INSERT INTO bank.person(\n"
					+ "personid, firstname, lastname, email, phonenumber, occupation, dob, password, isEmployee)\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getFirstname());
			preparedStatement.setString(3, person.getLastname());
			preparedStatement.setString(4, person.getEmail());
			preparedStatement.setString(5, person.getPhonenumber());

			Date dobDate = Date.valueOf(person.getDob());
			preparedStatement.setDate(7, dobDate);
			preparedStatement.setString(8, person.getPassword());
			preparedStatement.setBoolean(9, person.isEmployee());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException ex) {
			String message = ex.getMessage();
			if (message.contains("ERROR: duplicate key")) {
				System.out.println("The Primary KEY (Person Id) already exists: " + person.getId());
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
	public Person login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int logout(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

}
