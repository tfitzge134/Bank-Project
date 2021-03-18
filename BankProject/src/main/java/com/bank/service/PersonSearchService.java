package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonSearchService {

	public Person getPersonById(int id) throws BusinessException;

	// find person for email
	public Person getPersonByEmail(String email) throws BusinessException;// added

}
