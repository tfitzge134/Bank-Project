package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonDAO {

	public int addPerson(Person person) throws BusinessException;

	public Person verifyPassword(String email, String password) throws BusinessException;

}
