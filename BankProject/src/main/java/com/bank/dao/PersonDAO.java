package com.bank.dao;

import com.bank.model.Person;

public interface PersonDAO {
//	public boolean isExisting(int id);

	public int addPerson(Person person);

	public int addAccount(Person p);

}
