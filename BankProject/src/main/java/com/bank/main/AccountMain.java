package com.bank.main;

import java.sql.Date;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.service.AccountSearchService;
import com.bank.service.impl.AccountSearchServiceImpl;

public class AccountMain {

	public static void main(String[] args) {
		// createAccount();
		

	}

	private static void depositMoney() {
		try {
			String accountnumber = "3-12345674";

			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			System.out.println("Current Balance: " + account.getBalance());
			AccountDAO adao = new AccountDAOImpl();
			int c = adao.addDeposit(accountnumber, 100);
			if (c > 0) {
				System.out.println("Deposit success.");
				Account account1 = accountSearchService.getAccountByAccountNumber(accountnumber);
				System.out.println("New Balance: " + account1.getBalance());

			} else {
				System.out.println("Deposit failed.");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}

	}

	private static void createAccount() {
		Date openingDate = Date.valueOf("2020-02-15");

		AccountDAO adao = new AccountDAOImpl();

//	Account(String accountType, int branchid, String accountnumber, double openingbalance, 
//			double balance,
//			Date opendingdate, double deposit, double withdrawl, double interesrate, 
//			int customerid, boolean isactive) {
//		
//	}
		Account a = new Account("CA", 1, "", 25.0, 25.0, openingDate, 10.00, 0.00, 2.0, 3, false);
		int c1 = 0;
		try {
			c1 = adao.addAccount(a);
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}
		if (c1 > 0) {
			System.out.println("acc added");
			System.out.println(a);

		} else {
			System.out.println("adding acc failed");
		}
	}

}
