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
		//pattern: n-nnnnnnnn
		if (accountNumber != null && !accountNumber.isEmpty()) {
			if ((accountNumber.length() == 10) && (accountNumber.charAt(1) == '-')) {
				for(int i =0; i <10; i++) {
					if(i == 1) {
						continue;
					}
					char c = accountNumber.charAt(i);
					if(!Character.isDigit(c)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

}
