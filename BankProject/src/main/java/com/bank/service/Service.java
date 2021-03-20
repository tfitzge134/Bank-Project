package com.bank.service;

import java.util.Date;

public interface Service {
	public boolean isValidEmail(String e);
	public boolean isValidPassword(String p);
	public boolean isValidStatus(String s);
	public boolean isValidCustomerType(String ct);
	public boolean isValidAccountNumber(String a);
	public boolean isValidAccountType( String t);
	public boolean isValidAmount(double a);
	public boolean isValidPhoneNumber(String pn);//14 characters
	public boolean isValidDOB(Date dob);
	public boolean isValidOpeningDate(Date od);
	
	

}
