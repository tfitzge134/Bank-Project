package com.bank.dao;

import com.bank.model.Person;

public interface PersonDAO {
	public boolean isExisting(String email);

	public int addPerson(Person person);

	public Person getPerson(int id);

	public Person login(String email, String password);

	public int logout(String email);

}
