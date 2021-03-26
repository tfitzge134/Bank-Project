package com.bank.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountValidationsTest {

	@Test
	void testIsValidAccountType() {
		String accountType = "SA";
		boolean result = AccountValidations.isValidAccountType(accountType);
		assertTrue(result, "Valid Account Type: " + accountType);
		
		accountType = "CA";
		result = AccountValidations.isValidAccountType(accountType);
		assertTrue(result, "Valid Account Type: " + accountType);
	}
	
	@Test
	void testIsValidAccountType_InvalidAccountType() {
		String accountType = "Xyz";
		boolean result = AccountValidations.isValidAccountType(accountType);
		assertFalse(result, "Invalid Account Type: " + accountType);
	}

	@Test
	void testIsValidAccountNumber() {
		//fail("Not yet implemented");
	}

}
