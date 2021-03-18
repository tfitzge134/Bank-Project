package com.bank.model;

import java.sql.Date;

public class TransactionLog {

	private int id;
	private String sourceAccount;
	private String destAccount;
	private double amount;
	private Date txnDate;
	// Deposit or Withdraw or Transfer
	private String txnType;

	public TransactionLog() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public String getDestAccount() {
		return destAccount;
	}

	public void setDestAccount(String destAccount) {
		this.destAccount = destAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	@Override
	public String toString() {
		return "TransactionLog [id=" + id + ", sourceAccount=" + sourceAccount + ", destAccount=" + destAccount
				+ ", amount=" + amount + ", txnDate=" + txnDate + ", txnType=" + txnType + "]";
	}

}
