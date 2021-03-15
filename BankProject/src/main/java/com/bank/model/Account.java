package com.bank.model;

import java.sql.Date;

public class Account {
	
	private int acnumber;
	private static String accountnumber;
	private int branchid;
	private double openingbalance;
	@Override
	public String toString() {
		return "Account [acnumber=" + acnumber + ", branchid=" + branchid + ", openingbalance=" + openingbalance
				+ ", balance=" + balance + ", opendingdate=" + opendingdate + ", deposit=" + deposit + ", withdrawl="
				+ withdrawl + ", interesrate=" + interesrate + ", customerid=" + customerid + ", isactive=" + isactive
				+ "]";
	}
	public int getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(int acnumber) {
		this.acnumber = acnumber;
	}
	public static String getAccountnumber() {
		return accountnumber;
	}
	public static void setAccountnumber(String accountnumber) {
		Account.accountnumber = accountnumber;
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
	public boolean isIsactive() {
		return isactive;
	}
	public boolean setIsactive(boolean isactive) {
		return this.isactive = isactive;
	}
	private double balance;
	

	public Account(int acnumber, int string, double openingbalance, double balance, Date i, double string2,
			double b, double interesrate, double d, boolean e) {
		super();
		this.acnumber = acnumber;
		this.branchid = string;
		this.openingbalance = openingbalance;
		this.balance = balance;
		this.opendingdate = i;
		this.deposit = string2;
		this.withdrawl = b;
		this.interesrate = interesrate;
		this.customerid = (int) d;
		this.isactive = e;
	}
	public Account(int acnumber2, String string, int openingbalance2, int balance2, int i, String string2, boolean b,
			double interesrate2, double d, double e) {
		// TODO Auto-generated constructor stub
	}
	private Date opendingdate;
	private double deposit;
	private double withdrawl;
	private double interesrate;
	private int customerid;
	private  boolean isactive;
	
}