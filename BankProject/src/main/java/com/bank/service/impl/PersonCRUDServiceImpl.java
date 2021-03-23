package com.bank.service.impl;

import com.bank.dao.PersonDAO;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Person;
import com.bank.service.PersonCRUDService;

public class PersonCRUDServiceImpl implements PersonCRUDService {
	// private static Logger log = Logger.getLogger(PersonCRUDServiceImpl.class);

	private PersonDAO personDAO = new PersonDAOImpl();

	@Override
	public int createPerson(Person person) throws BusinessException {
		if (!PersonValidations.isValidDob(person.getDob())) {
			throw new BusinessException("Entered person DOB " + person.getDob() 
			+ " is invalid.\n Valid Format: yyyy-MM-dd");
		}
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

	@Override
	public int deletePersonByEmail(String email) throws BusinessException {
		// do the handshake with DAO
		return personDAO.deletePersonByEmail(email);

	}

}
