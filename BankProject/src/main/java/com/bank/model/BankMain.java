package com.bank.model;

import java.util.Scanner;

import com.bank.exception.BusinessException;
import com.bank.service.BankService;
import com.bank.service.PersonCRUDService;
import com.bank.service.impl.BankServiceImpl;
import com.bank.service.impl.PersonCRUDServiceImpl;

public class BankMain {

	public static void main(String[] args) {

		System.out.println("Welcome to Wonderlad Bank WHERE YOU MONEY IS OUR MONEY");
		System.out.println("================================================");
		System.out.println("Teresa's Bank App V1.0");
		System.out.println("================================================");
		int ch = 0;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Bank MENU");
			System.out.println("-----------------");
			System.out.println("1)Login");
			System.out.println("2)Signup for User Account");

			System.out.println("0)Logout");

//			System.out.println("3)Delete Bank");
//			System.out.println("4)List All Banks");
//			System.out.println("5)Search Bank by any of these id,name,age,city,gender");
//			System.out.println("6)List all Teams");
//			System.out.println("7)Add Team");
//			System.out.println("8)EXIT");
			System.out.println("Please enter an appropriate Search Option(1-8)");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {
			case 1:
				login(scanner);

				break;
			case 2:
				signup(scanner);

				break;
			case 4:
				System.out.println("Under construction");

				break;
			case 0:
				System.out.println("Thankq for using the Bank APP V1.0.....");

				break;

			default:
				System.out.println("Invalid Choice... Please enter a proper choice between 1-? only.......");
				break;
			}
		} while (ch != 0);

	}

	private static void login(Scanner scanner) {
		System.out.println("Enter below Details to Login");

		System.out.println("Enter email:");
		String email = scanner.nextLine();

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		try {
			BankService bankService = new BankServiceImpl();
			Person person = bankService.login(email, password);
			if (person != null) {
				System.out.println("------------Login result---------");
				System.out.println("Person LOGIN Success!");
				System.out.println(person);
				if (person.isEmployee()) {
					System.out.println("------------EMPLOYEE MENU---------");
					System.out.println("1)Approve or reject Accounts");
					System.out.println("2)View customer's bank accounts");

					System.out.println("0)Back to Main Menu");
					System.out.println("-----------------");
				} else {
					System.out.println("------------CUSTOMER MENU---------");
					System.out.println("1)Apply as Bank Customer");
					System.out.println("2)Open new Bank Account");
					System.out.println("3)View Account Balance");
					System.out.println("0)Back to Main Menu");
					System.out.println("-----------------");
				}
			} else {
				System.out.println("Person LOGIN FAILED!");
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
			System.out.println("------------RETRY WITH VALID VALUES---------");
		}
	}

	private static void signup(Scanner scanner) {
		Person person = new Person();

		System.out.println("Enter below Details to Create user Account");

		System.out.println("Enter email:");
		person.setEmail(scanner.nextLine());

		System.out.println("Enter password:");
		person.setPassword(scanner.nextLine());

		System.out.println("Enter DoB:");
		person.setDob(scanner.nextLine());

		System.out.println("Enter First Name:");
		person.setFirstname(scanner.nextLine());

		System.out.println("Enter Last Name:");
		person.setLastname(scanner.nextLine());

		System.out.println("Enter phonenumber:");
		person.setPhonenumber(scanner.nextLine());

		System.out.println("Enter Is Employee:");
		person.setEmployee(Boolean.valueOf(scanner.nextLine()));

		try {
			PersonCRUDService personCrudService = new PersonCRUDServiceImpl();
			if (personCrudService.createPerson(person) == 1) {
				System.out.println("Person Registered Successfully with below details");
				System.out.println(person);
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
			System.out.println("------------RETRY WITH VALID VALUES---------");
		}
	}

}
