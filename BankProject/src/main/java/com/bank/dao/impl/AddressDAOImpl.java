package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.dao.AddressDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.model.Address;

public class AddressDAOImpl implements AddressDAO {

	@Override
	public int addAddress(Address address) {
	String sql = "INSERT INTO person.address(\n"
			+ "	addressid, address, city, state, country, personid)\n"
			+ "	VALUES (?, ?, ?, ?, ?, ?);";
	int c=0;
	try {
		Connection connection = PostgresConnection.openConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, address.getAddressid());
		preparedStatement.setString(2, address.getAddress());
		preparedStatement.setString(3, address.getCity());
		preparedStatement.setString(4, address.getState());
		preparedStatement.setString(5, address.getCountry());
		preparedStatement.setInt(1, address.getPersonid());
		c=preparedStatement.executeUpdate();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return 0;
	}

}
/*
INSERT INTO person.address(
	addressid, address, city, state, country, personid)
	VALUES (?, ?, ?, ?, ?, ?);
*/