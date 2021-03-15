package com.bank.model;

import java.sql.Date;

public class Transfer {
	
			private int transnumber;
			private Date dot;
			private String sourceaccountnumber;
			private String destinationaccountnumber;
			private double transactionamount;
			private int acsavingid;
			private int acnumber;
			public int getTransnumber() {
				return transnumber;
			}
			public void setTransnumber(int transnumber) {
				this.transnumber = transnumber;
			}
			public Date getDot() {
				return dot;
			}
			public void setDot(Date dot) {
				this.dot = dot;
			}
			public String getSourceaccountnumber() {
				return sourceaccountnumber;
			}
			public void setSourceaccountnumber(String sourceaccountnumber) {
				this.sourceaccountnumber = sourceaccountnumber;
			}
			public String getDestinationaccountnumber() {
				return destinationaccountnumber;
			}
			public void setDestinationaccountnumber(String destinationaccountnumber) {
				this.destinationaccountnumber = destinationaccountnumber;
			}
			public double getTransactionamount() {
				return transactionamount;
			}
			public void setTransactionamount(double transactionamount) {
				this.transactionamount = transactionamount;
			}
			public int getAcsavingid() {
				return acsavingid;
			}
			public void setAcsavingid(int acsavingid) {
				this.acsavingid = acsavingid;
			}
			public int getAcnumber() {
				return acnumber;
			}
			public void setAcnumber(int acnumber) {
				this.acnumber = acnumber;
			}
			@Override
			public String toString() {
				return "Transfer [transnumber=" + transnumber + ", sourceaccountnumber=" + sourceaccountnumber
						+ ", destinationaccountnumber=" + destinationaccountnumber + ", transactionamount="
						+ transactionamount + ", acsavingid=" + acsavingid + ", acnumber=" + acnumber + "]";
			}
		

}
