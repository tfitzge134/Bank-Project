package com.bank.model;

import java.sql.Date;

public class SavingsAccount {
	
			
		private int acsavingid;
		private String accountnumber;
		private int branchid;
		private double openingbalance;
		private double balance;
		private Date opendingdate;
		private String accstatus;
		private double deposit;
		private Date depositdate;
		private double withdrawl;
		private Date withdate;
		public int getAcsavingid() {
			return acsavingid;
		}
		@Override
		public String toString() {
			return "savingsAccount [acsavingid=" + acsavingid + ", accountnumber=" + accountnumber + ", branchid="
					+ branchid + ", openingbalance=" + openingbalance + ", balance=" + balance + ", accstatus="
					+ accstatus + ", deposit=" + deposit + ", withdrawl=" + withdrawl + ", interestrate=" + interestrate
					+ ", customerid=" + customerid + "]";
		}
		public void setAcsavingid(int acsavingid) {
			this.acsavingid = acsavingid;
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
		public String getAccstatus() {
			return accstatus;
		}
		public void setAccstatus(String accstatus) {
			this.accstatus = accstatus;
		}
		public double getDeposit() {
			return deposit;
		}
		public void setDeposit(double deposit) {
			this.deposit = deposit;
		}
		public Date getDepositdate() {
			return depositdate;
		}
		public void setDepositdate(Date depositdate) {
			this.depositdate = depositdate;
		}
		public double getWithdrawl() {
			return withdrawl;
		}
		public void setWithdrawl(double withdrawl) {
			this.withdrawl = withdrawl;
		}
		public Date getWithdate() {
			return withdate;
		}
		public void setWithdate(Date withdate) {
			this.withdate = withdate;
		}
		public double getInterestrate() {
			return interestrate;
		}
		public void setInterestrate(double interestrate) {
			this.interestrate = interestrate;
		}
		public int getCustomerid() {
			return customerid;
		}
		public void setCustomerid(int customerid) {
			this.customerid = customerid;
		}
		private double interestrate;
		private int customerid;
			

}

