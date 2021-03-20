package com.bank.service.impl;

import com.bank.dao.PersonDAO;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Person;
import com.bank.service.PersonCRUDService;

public class PersonCRUDServiceImpl implements PersonCRUDService {
	//private static Logger log = Logger.getLogger(PersonCRUDServiceImpl.class);
	
	private PersonDAO personDAO = new PersonDAOImpl();

	@Override
	public int createPerson(Person person) throws BusinessException {
		if (!PersonValidations.isValidEmail(person.getEmail())) {
			throw new BusinessException("Entered person email " + person.getEmail() + " is invalid");
		}

		if (!PersonValidations.isValidPersonName(person.getFirstname())) {
			throw new BusinessException("Entered person Firstname " + person.getFirstname() + " is invalid");
		}

		if (!PersonValidations.isValidPersonName(person.getLastname())) {
			throw new BusinessException("Entered person Lastname " + person.getLastname() + " is invalid");
		}

		// do the handshake with DAO
		return personDAO.addPerson(person);
	}

	

}
