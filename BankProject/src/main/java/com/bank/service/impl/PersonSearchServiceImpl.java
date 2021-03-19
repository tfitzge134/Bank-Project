package com.bank.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Person;
import com.bank.service.PersonSearchService;

public class PersonSearchServiceImpl implements PersonSearchService {
	private static Logger log = Logger.getLogger(PersonSearchServiceImpl.class);

	@Override
	public Person getPersonById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByEmail(String email) throws BusinessException {
		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "SELECT * FROM bank.person WHERE email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Person person = new Person();
				// firstname, lastname, email, password, phonenumber, dob, isEmployee
				person.setId(resultSet.getInt("id"));
				person.setFirstname(resultSet.getString("firstname"));
				person.setLastname(resultSet.getString("lastname"));
				person.setEmail(resultSet.getString("email"));
				person.setEmployee(resultSet.getBoolean("isEmployee"));
				return person;
			}
			return null;
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
	}

}
