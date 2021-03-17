package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonSearchService {
	public Person getIsEmployee(boolean id) throws BusinessException;
	public Person getPersonById(int id) throws BusinessException;
	//find person for email
	public Person getPersonByEmail(String email) throws BusinessException;//added
	public Person getPersonByPassword(String password) throws BusinessException;//added
	
	public List<Person> getPersonsByEmail(String email) throws BusinessException;
}
