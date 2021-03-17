package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Person;

public interface BankService {
	public Person login(String email, String password) throws BusinessException;
}
