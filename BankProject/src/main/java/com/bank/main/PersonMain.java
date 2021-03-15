package com.bank.main;

import com.bank.dao.AccountDAO;
import com.bank.dao.PersonDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.model.Account;
import com.bank.model.Person;

public class PersonMain {

	public static void main(String[] args) {
		int id = 51;
		
		PersonDAO dao = new PersonDAOImpl();
		Person p = new Person(id,"Maria", "Smith", "ms.@bank.com", "4132326244",
				"Teacher", "1976-06-09", 
				"PAssord", "FALSE");
		int c = dao.addPerson(p);
		if(c>0) {
			System.out.println("person added");
			System.out.println(p);
		
		}else {
			System.out.println("adding person failed");
		}
		

		

	}

}
