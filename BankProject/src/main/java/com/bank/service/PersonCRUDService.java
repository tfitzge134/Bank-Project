package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonCRUDService {

	public int createPerson(Person person)throws BusinessException;
	
}
