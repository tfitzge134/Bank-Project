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

}
