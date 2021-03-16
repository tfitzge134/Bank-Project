package com.bank.main;

import java.sql.Date;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.model.Account;

public class AccountMain {

	public static void main(String[] args) {
		Date openingDate = Date.valueOf("2020-02-15");

		AccountDAO adao = new AccountDAOImpl();

//	Account(String accountType, int branchid, String accountnumber, double openingbalance, 
//			double balance,
//			Date opendingdate, double deposit, double withdrawl, double interesrate, 
//			int customerid, boolean isactive) {
//		
//	}
		Account a = new Account("CA", 1, "", 25.0, 25.0, openingDate, 10.00, 0.00, 2.0, 3, false);
		int c1 = adao.addAccount(a);
		if (c1 > 0) {
			System.out.println("acc added");
			System.out.println(a);

		} else {
			System.out.println("adding acc failed");
		}

	}

}
