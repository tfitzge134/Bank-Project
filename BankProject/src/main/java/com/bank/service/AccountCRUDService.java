package com.bank.service;

import com.bank.exception.BusinessException;

public interface AccountCRUDService {

	public int deleteById(int id) throws BusinessException;

}
