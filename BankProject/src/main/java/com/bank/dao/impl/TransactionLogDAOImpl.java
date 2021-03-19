package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.TransactionLogDAO;
import com.bank.dao.dbutil.PostgresConnection;
import com.bank.exception.BusinessException;
import com.bank.model.TransactionLog;

public class TransactionLogDAOImpl implements TransactionLogDAO {
	private static Logger log = Logger.getLogger(TransactionLogDAOImpl.class);

	@Override
	public int addTransactionLog(TransactionLog txnLog) throws BusinessException {

		try (Connection connection = PostgresConnection.openConnection()) {
			String sql = "insert into bank.transactionlog " 
						+ "(txntype, sourceaccount " + ", destinationaccount , "
					+ "txndate , amount ) " + "   values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, txnLog.getTxnType());
			preparedStatement.setString(2, txnLog.getSourceAccount());
			preparedStatement.setString(3, txnLog.getDestAccount());
			preparedStatement.setDate(4, txnLog.getTxnDate());
			preparedStatement.setDouble(5, txnLog.getAmount());

			int count = preparedStatement.executeUpdate();
			return count;
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e);
			e.printStackTrace();
			throw new BusinessException("Internal error");
		}

	}

	@Override
	public List<TransactionLog> getAllTransactionLogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
