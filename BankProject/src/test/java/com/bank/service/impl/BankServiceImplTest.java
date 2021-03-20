package com.bank.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Person;
import com.bank.service.AccountSearchService;
import com.bank.service.BankService;
import com.bank.service.PersonCRUDService;
import com.bank.service.PersonSearchService;

public class BankServiceImplTest {

	private static int accountNumberSeq = 12359898;
	@Test
	void testApplyForNewAccount() {
		//Create Person
		String email = "a7@example.com";

		Person person = createPerson(email);
		assertNotNull(person, "Create Person Failed.");
		
		//Create Account
		Account account = new Account();
		account.setCustomerid(person.getId());
		String accountType = "SA";
		account.setAccountType(accountType );
		account.setOpeningbalance(100);

		BankService bankService = new BankServiceImpl();
		String accountNumber = "9-" + accountNumberSeq;
		int accountId = 0;
		try {
			//Apply for account
			int count = bankService.applyForNewAccount(account);
			assertEquals(1, count, "Apply for New Account Failed.");
			
			//Get Applied new Accounts
			List<Account> appliedNewAccounts = bankService.getAppliedNewAccounts();
			assertNotNull(appliedNewAccounts, "Getting appliedNewAccounts FAILED.");
			assertTrue(appliedNewAccounts.size() >= 1 , "Getting appliedNewAccounts FAILED.");
			
			for(Account a: appliedNewAccounts) {
				if(a.getCustomerid() == person.getId()) {
					accountNumberSeq++;
					//Approve Account
					bankService.approveAccount(a.getId(), accountNumber);
					break;
				}
			}
			//Search account by account number
			AccountSearchService accountSearch = new AccountSearchServiceImpl();
			Account accountApproved = accountSearch.getAccountByAccountNumber(accountNumber);
			assertNotNull(accountApproved, "accountApproved could not be retrieved.");
			assertEquals(accountNumber, accountApproved.getAccountnumber(), "Account number mismatch.");
			assertEquals("Approved", accountApproved.getStatus(), "Invalid status.");
			accountId = accountApproved.getId();
		} catch (BusinessException e) {
			e.printStackTrace();
			fail("Error: " + e.getMessage());
		} finally {
			try {
				if(accountId != 0) {
					AccountCRUDServiceImpl accountCRUD = new AccountCRUDServiceImpl();
					int count = accountCRUD.deleteById(accountId);
				}
				PersonCRUDService personCRUDService = new PersonCRUDServiceImpl();
				int count = personCRUDService.deletePersonByEmail(person.getEmail());
			} catch (BusinessException e) {
				e.printStackTrace();
				fail("ERROR: " + e.getMessage());
			}
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
	void testLogin() {
//		fail("Not yet implemented");
	}

	@Test
	void testApproveAccount() {
//		fail("Not yet implemented");
	}

	@Test
	void testRejectAccount() {
//		fail("Not yet implemented");
	}

	@Test
	void testGetAppliedNewAccounts() {
//		fail("Not yet implemented");
	}

}
