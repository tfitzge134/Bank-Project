package com.bank.service.impl;

import com.bank.dao.TransactionLogDAO;
import com.bank.dao.impl.TransactionLogDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.TransactionLog;
import com.bank.service.TransactionLogCRUDService;

public class TransactionLogCRUDServiceImpl implements TransactionLogCRUDService {

	@Override
	public int createTransactionLog(TransactionLog transactionLog) throws BusinessException {
		TransactionLogDAO txnLogDao = new TransactionLogDAOImpl();
		return txnLogDao.addTransactionLog(transactionLog);
	}

}
