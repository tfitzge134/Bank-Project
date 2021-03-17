package com.bank.service.impl;

import com.bank.dao.PersonDAO;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Person;
import com.bank.service.BankService;

public class BankServiceImpl implements BankService {

	@Override
	public Person login(String email, String password) throws BusinessException {
		if (!PersonValidations.isValidEmail(email)) {
			throw new BusinessException("Entered person email " + email + " is invalid");
		}
		PersonDAO personDAO = new PersonDAOImpl();
		return personDAO.verifyPassword(email, password);
	}

}
