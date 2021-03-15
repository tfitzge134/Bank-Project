package com.bank.main;

import com.bank.dao.AddressDAO;
import com.bank.dao.impl.AddressDAOImpl;
import com.bank.model.Address;

public class AddressMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddressDAO dao = new AddressDAOImpl();
		Address a = new Address(1, "South First Street",
				"Westfield", "MA", "USA", 1);
		 int c = dao.addAddress(a);
		 //int c = dao.addPerson(p);
			if(c>0) {
				System.out.println("person added");
				//System.out.println(p);
			
			}else {
				System.out.println("adding person failed");
			}
			
		

	}

}
//int addressid;
//String address;
//String City;
//String state;
//String country;
//int personid;