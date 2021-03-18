package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.model.TransactionLog;

public interface TransactionLogCRUDService {
	public int createTransactionLog(TransactionLog transactionLog) throws BusinessException;

	public List<TransactionLog> getAllTransactionLogs() throws BusinessException;
}
