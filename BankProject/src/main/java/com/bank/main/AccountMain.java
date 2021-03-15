package com.bank.main;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.model.Account;

public class AccountMain {

	public static void main(String[] args) {
		int acnumber = 51;
		
	AccountDAO adao = new AccountDAOImpl();
		Account a = new Account(
				123,
				"1-23455",
				1,
				10,
				10,
				"10-01-2005",
				false,
				10.00,
				0.00,
				2.0


				);
		int c1 = adao.addAccount(a);
		if(c1>0) {
			System.out.println("acc added");
			System.out.println(a);
		
		}else {
			System.out.println("adding acc failed");
		}


	}

}
