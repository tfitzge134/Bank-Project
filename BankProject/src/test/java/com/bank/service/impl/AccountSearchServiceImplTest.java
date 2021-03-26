package com.bank.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Person;
import com.bank.service.AccountCRUDService;
import com.bank.service.AccountSearchService;
import com.bank.service.BankService;
import com.bank.service.PersonCRUDService;
import com.bank.service.PersonSearchService;

class AccountSearchServiceImplTest {

	private static int accountNumberSeq = 32359999;
	private static int emailSeq = 3;
	String emailSuffix = "y@example.com";
	String email = null;
	int customerId = 0;
	private Account accountApproved;

	@BeforeEach
	void setup() {
		emailSeq++;
		email = emailSeq + emailSuffix;
		System.out.println("---setup---begin");
		// Create Person
		Person person = createPerson(email);
		assertNotNull(person, "Create Person Failed.");
		customerId = person.getId();
		
		// Setup Accounts
		setupAcounts();
		
		System.out.println("---setup---end");
	}

	@AfterEach
	void teardown() {
		System.out.println("---teardown---begin");
		boolean cleanupFailed = false;
		try {
			PersonSearchService personSearchService = new PersonSearchServiceImpl();
			AccountSearchService accountSearch = new AccountSearchServiceImpl();
			AccountCRUDService accountCRUD = new AccountCRUDServiceImpl();
			PersonCRUDService personCRUD = new PersonCRUDServiceImpl();

			Person person = personSearchService.getPersonByEmail(email);

			List<Account> accountsList = accountSearch.getAccountByCustomerId(person.getId());
			for (Account account : accountsList) {
				int count = accountCRUD.deleteById(account.getId());
				if (count < 1) {
					System.out.println("Account CLEANUP FAILED: " + account.getAccountnumber());
					cleanupFailed = true;
				}
			}
			int count = personCRUD.deletePersonByEmail(person.getEmail());
			if (count < 1) {
				cleanupFailed = true;
				System.out.println("Person CLEANUP FAILED: " + person.getEmail());
			}
			if(cleanupFailed) {
				fail("CLEANUP FAILED");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("ERROR: " + e.getMessage());
		}
		System.out.println("---teardown---end");
	}

	
	void setupAcounts() {
		try {
			PersonSearchService personSearchService = new PersonSearchServiceImpl();

			Person person = personSearchService.getPersonByEmail(email);

			// Create Account
			Account account = new Account();
			account.setCustomerid(person.getId());
			String accountType = "SA";
			account.setAccountType(accountType);
			account.setOpeningbalance(100);

			BankService bankService = new BankServiceImpl();
			String accountNumber = "9-" + accountNumberSeq;
			// Apply for account
			int count = bankService.applyForNewAccount(account);
			assertEquals(1, count, "Apply for New Account Failed.");

			// Get Applied new Accounts
			List<Account> appliedNewAccounts = bankService.getAppliedNewAccounts();
			assertNotNull(appliedNewAccounts, "Getting appliedNewAccounts FAILED.");
			assertTrue(appliedNewAccounts.size() >= 1, "Getting appliedNewAccounts FAILED.");

			for (Account a : appliedNewAccounts) {
				if (a.getCustomerid() == person.getId()) {
					accountNumberSeq++;
					// Approve Account
					bankService.approveAccount(a.getId(), accountNumber);
					break;
				}
			}
			// Search account by account number
			AccountSearchService accountSearch = new AccountSearchServiceImpl();
			accountApproved = accountSearch.getAccountByAccountNumber(accountNumber);
			assertNotNull(accountApproved, "accountApproved could not be retrieved.");
			assertEquals(accountNumber, accountApproved.getAccountnumber(), "Account number mismatch.");
			assertEquals("Approved", accountApproved.getStatus(), "Invalid status.");
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("Error: " + e.getMessage());
		}
	}

	Person createPerson(String email) {

		Person person = new Person();
		String firstname = "Jill";
		person.setFirstname(firstname);
		String lastname = "Woods";
		person.setLastname(lastname);
		String password = "abc";
		person.setPassword(password);
		String dob = "2000-01-02";
		person.setDob(dob);
		person.setEmail(email);
		person.setEmployee(false);
		String phonenumber = "1122334455";
		person.setPhonenumber(phonenumber);

		PersonCRUDService personCRUDService = new PersonCRUDServiceImpl();
		try {
			int count = personCRUDService.createPerson(person);
			assertEquals(1, count, "Create Person Failed.");

			PersonSearchService search = new PersonSearchServiceImpl();
			Person personByEmail = search.getPersonByEmail(email);
			assertNotNull(personByEmail, "Could not get Person by email: " + email);

			return personByEmail;
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("ERROR: " + e.getMessage());
			return null;
		}
	}

	@Test
	void testGetAccountByAccountNumber() {
		AccountSearchService accountSearchService = new AccountSearchServiceImpl();
		try {
			Account account1 = accountSearchService.getAccountByAccountNumber(accountApproved.getAccountnumber());
			assertNotNull(account1, "Null acccount");
			assertEquals(accountApproved.getAccountnumber(), account1.getAccountnumber()
					, "Account Number mismatch");
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("ERROR: " + e.getMessage());
		}
	}

	@Test
	void testGetAccountByCustomerId() {
		AccountSearchService accountSearchService = new AccountSearchServiceImpl();
		try {
			List<Account> accounts = accountSearchService.getAccountByCustomerId(customerId);
			assertNotNull(accounts, "Empty acccount list");
			assertEquals(1, accounts.size(), "More acccount numbers.");
			Account account1 = accounts.get(0);
			assertEquals(accountApproved.getAccountnumber(), account1.getAccountnumber()
					, "Account Number mismatch");
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("ERROR: " + e.getMessage());
		}
	}

}

