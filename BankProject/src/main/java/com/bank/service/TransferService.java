package com.bank.service;

import com.bank.exception.BusinessException;

public interface TransferService {

	public String Transfer(String sourceAccountNumber, String destAccountNumber, double amount) throws BusinessException;
}
