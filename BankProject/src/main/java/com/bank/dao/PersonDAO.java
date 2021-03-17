package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonDAO {
	public boolean isExisting(String email);

	public int addPerson(Person person);

	public Person getPerson(int id);

	public Person verifyPassword(String email, String password) throws BusinessException;

	public int logout(String email);

}
