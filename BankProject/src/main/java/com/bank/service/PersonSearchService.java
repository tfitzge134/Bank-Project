package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonSearchService {

	public Person getPersonById(int id) throws BusinessException;
	public List<Person> getPersonsByEmail(String email) throws BusinessException;
}
