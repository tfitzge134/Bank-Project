package com.bank.service.impl;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.service.AccountSearchService;
import com.bank.service.TransferService;

public class TransferServiceImpl implements TransferService {

	@Override
	public String Transfer(String sourceAccountNumber, String destAccountNumber, double amount)
			throws BusinessException {
		AccountSearchService accountSearchService = new AccountSearchServiceImpl();
		try {
			Account sourceAccount = accountSearchService.getAccountByAccountNumber(sourceAccountNumber);
			if (sourceAccount == null) {
				return "INVALID_SOURCE_ACCOUNT_NUMBER";
			}
			if (sourceAccount.getBalance() < amount) {
				return "INSUFFFIENT_BALANACE";
			}
			Account destAccount = accountSearchService.getAccountByAccountNumber(destAccountNumber);
			if (destAccount == null) {
				return "INVALID_DEST_ACCOUNT_NUMBER";
			}

			//
			return "SUCCESS";
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new BusinessException("Internal error");
		}

	}

}
