package com.bank.service;

public interface TransferService {

	public boolean Transfer(String sourceAccount, String destAccount, double transactionamount);
}
