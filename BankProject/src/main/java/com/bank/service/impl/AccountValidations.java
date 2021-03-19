package com.bank.service.impl;

public class AccountValidations {

	public static boolean isValidAccountType(String accountType) {
		if (accountType != null) {
			if (accountType.equals("SA") || accountType.equals("CA")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidAccountNumber(String accountNumber) {
		if (accountNumber != null && !accountNumber.isEmpty()) {
			if (accountNumber.length() == 10) {
				//PENDING check for pattern n-nnnnnnnn using regex
				return true;
			}
		}
		return false;
	}

}
