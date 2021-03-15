package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface PersonCRUDService {

	public int createPerson(Person person)throws BusinessException;
	public int updatePerson(int id,String email)throws BusinessException;
	public int deletePerson(int id)throws BusinessException;
	public List<Person> getAllPersons()throws BusinessException;
}
