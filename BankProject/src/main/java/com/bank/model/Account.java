package com.bank.model;

import java.sql.Date;

public class Account {

	private int id;
	private String accountnumber;
	private int branchid;
	private double openingbalance;
	private double balance;

	private Date opendingdate;
	private double deposit;
	private double withdrawl;
	private double interesrate;
	private int customerid;
	private boolean isactive;
	private String accountType;
	private String status;

	public Account(String accountType, int branchid, String accountnumber, double openingbalance, double balance,
			Date opendingdate, double deposit, double withdrawl, double interesrate, int customerid, boolean isactive) {
		super();
		this.accountType = accountType;
		this.branchid = branchid;
		this.accountnumber = accountnumber;
		this.openingbalance = openingbalance;
		this.balance = balance;
		this.opendingdate = opendingdate;
		this.deposit = deposit;
		this.withdrawl = withdrawl;
		this.interesrate = interesrate;
		this.customerid = customerid;
		this.isactive = isactive;
	}

	public Account() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpendingdate() {
		return opendingdate;
	}

	public void setOpendingdate(Date opendingdate) {
		this.opendingdate = opendingdate;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdrawl() {
		return withdrawl;
	}

	public void setWithdrawl(double withdrawl) {
		this.withdrawl = withdrawl;
	}

	public double getInteresrate() {
		return interesrate;
	}

	public void setInteresrate(double interesrate) {
		this.interesrate = interesrate;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountnumber=" + accountnumber + ", branchid=" + branchid + ", openingbalance="
				+ openingbalance + ", balance=" + balance + ", opendingdate=" + opendingdate + ", deposit=" + deposit
				+ ", withdrawl=" + withdrawl + ", interesrate=" + interesrate + ", customerid=" + customerid
				+ ", isactive=" + isactive + ", accountType=" + accountType + ", status=" + status + "]";
	}

}