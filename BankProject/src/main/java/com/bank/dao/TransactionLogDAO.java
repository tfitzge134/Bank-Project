package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.TransactionLog;

public interface TransactionLogDAO {
	public int addTransactionLog(TransactionLog account) throws BusinessException;

}
