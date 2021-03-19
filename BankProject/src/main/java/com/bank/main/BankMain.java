package com.bank.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Person;
import com.bank.service.AccountSearchService;
import com.bank.service.AccountService;
import com.bank.service.BankService;
import com.bank.service.PersonCRUDService;
import com.bank.service.PersonSearchService;
import com.bank.service.impl.AccountSearchServiceImpl;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.BankServiceImpl;
import com.bank.service.impl.PersonCRUDServiceImpl;
import com.bank.service.impl.PersonSearchServiceImpl;

public class BankMain {

	private static Logger log = Logger.getLogger(BankMain.class);
	private static Scanner scanner = new Scanner(System.in);
	private static Person currentUser;

	public static void main(String[] args) {
		log.info("Bank Appplication Started.");

		System.out.println("Welcome to Wonderlad Bank WHERE YOU MONEY IS OUR MONEY");
		System.out.println("================================================");
		System.out.println("Teresa's Bank App V1.0");
		System.out.println("================================================");
		int ch = 0;
		do {
			System.out.println("\n-----------------");
			System.out.println("Bank MENU");
			System.out.println("-----------------");
			System.out.println("1)Login");
			System.out.println("2)Signup for User Account");

			System.out.println("0)Logout");

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
		log.info("Bank Appplication Ended.\n------------");
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
				currentUser = person;
				System.out.println("------------Login result---------");
				System.out.println("Person LOGIN Success!");
				System.out.println(person);

				if (person.isEmployee()) {
					employeeMenu();
				} else {
					customerMenu();
				}
			} else {
				System.out.println("Person LOGIN FAILED!");
			}
		} catch (BusinessException e) {
			log.error(e);
			System.out.println(e.getMessage());
			System.out.println("------------RETRY WITH VALID VALUES---------");
		}
	}

	private static void customerMenu() {
		int ch = 0;
		do {
			System.out.println("------------CUSTOMER MENU---------");
			System.out.println("1)Apply for new Bank Account");
			System.out.println("2)View My Accounts");
			System.out.println("3)View Account Balance");
			System.out.println("4)Deposit");
			System.out.println("5)Withdraw");
			System.out.println("6)Transfer Money");
			System.out.println("0)Back to Main Menu");
			System.out.println("-----------------");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {
			case 1:
				System.out.println("1)Apply for new Bank Account");
				applyForNewBankAccount();
				break;
			case 2:
				System.out.println("....2)View My Accounts");
				viewMyAccounts();
				break;
			case 3:
				System.out.println("....3)View Account Balance");
				viewAccountBalance();
				break;
			case 4:
				System.out.println("....4)Deposit");
				deposit();
				break;
			case 5:
				System.out.println("....5)Withdraw");
				withdraw();
				break;
			case 6:
				System.out.println("6)Transfer Money");
				transferMoney();
				break;
			case 0:
				System.out.println("....0)Back to Main Menu");

			default:
				System.out.println("Invalid Choice... Please enter a proper choice.");
				break;
			}

		} while (ch != 0);
	}

	private static void transferMoney() {
		System.out.println("Enter below Details to withdraw.");

		System.out.println("Enter SOURCE account number:");
		String sourceAccount = scanner.nextLine();
		
		System.out.println("Enter DESTINATION account number:");
		String destAccount = scanner.nextLine();

		System.out.println("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Enter valid amount for withdraw:");
			System.out.println("---------------------");
			return;
		}
		
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			//System.out.println("Current Balance: " + account.getBalance());
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.transfer(sourceAccount, destAccount, amount);
			//double b = account.getBalance();
			if (c > 0) {
				System.out.println("Transfer success.");
				Account source = accountSearchService.getAccountByAccountNumber(sourceAccount);
				Account dest = accountSearchService.getAccountByAccountNumber(destAccount);
				System.out.println("Source Account New Balance: " + source.getBalance());
				System.out.println("Dest Account New Balance: " + dest.getBalance());
			}
			else {
				System.out.println("Transfer failed");
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}

	}
///////////////////////////////////////////////////////////
	private static void withdraw() {
		System.out.println("Enter below Details to withdraw.");

		System.out.println("Enter account number:");
		String accountnumber = scanner.nextLine();

		System.out.println("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Enter valid amount for withdraw:");
			System.out.println("---------------------");
			return;
		}
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				System.out.println("....Account NOT found.");
				return;
			}
			//System.out.println("Current Balance: " + account.getBalance());
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.withdraw(accountnumber, amount);
			//double b = account.getBalance();
			if (c > 0) {
				System.out.println("Withdraw success.");
				Account account1 = accountSearchService.getAccountByAccountNumber(accountnumber);
				System.out.println("New Balance: " + account1.getBalance());
			} 
			else if (account.getBalance() < amount) {
				System.out.println( "INSUFFFIENT_BALANCE");
			}else {
				System.out.println("Account failed");
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}
	}

	private static void deposit() {
		System.out.println("Enter below Details to deposit.");

		System.out.println("Enter account number:");
		String accountnumber = scanner.nextLine();

		System.out.println("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Enter valid amount for deposit:");
			System.out.println("---------------------");
			return;
		}
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				System.out.println("....Account NOT found.");
				return;
			}
			System.out.println("Current Balance: " + account.getBalance());
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.deposit(accountnumber, amount);
			if (c > 0) {
				System.out.println("Deposit success.");
				Account account1 = accountSearchService.getAccountByAccountNumber(accountnumber);
				System.out.println("New Balance: " + account1.getBalance());

			} else {
				System.out.println("Deposit failed.");
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}
	}
////////
	private static void viewAccountBalance() {
		// TODO Auto-generated method stub
		//System.out.println("....2)View balance");
		
		System.out.println("Enter account number:");
		String accountnumber = scanner.nextLine();
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				System.out.println("....Account NOT found.");
				return;
			}
			System.out.println("Current Balance: " + account.getBalance());
//			 {
//				System.out.println(".");
//			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("ERROR: " + e.getMessage());

		}
	
	}
			
			//System.out.println("Current Balance: " + account.getBalance());
////	
		
		

	private static void viewMyAccounts() {
		System.out.println("....2)View My Accounts");
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			List<Account> accounts = accountSearchService.getAccountByCustomerId(currentUser.getId());
			if (accounts == null || accounts.size() == 0) {
				System.out.println("...NO Accounts Found....");
				return;
			}
			for (Account account : accounts) {
				System.out.println("Account Number: " + account.getAccountnumber() 
						+ ", AccounType: " + account.getAccountType() 
						+ ", Openingbalance: " + account.getOpeningbalance()
						+ ", Balance: " + account.getBalance()
						+ ", Status: " + account.getStatus()
						);
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println(e);
		}
		
	}

	private static void applyForNewBankAccount() {
		System.out.println("Enter below Details to open new account.");

		System.out.println("Enter account type (SA or CA):");
		String accountType = scanner.nextLine();

		System.out.println("Enter opening deposit:");
		double deposit = 0.0;
		try {
			deposit = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Enter valid amount for deposit:");
			System.out.println("---------------------");
			return;
		}

		try {
			BankService bankService = new BankServiceImpl();
			Account account = new Account();
			account.setAccountType(accountType);
			account.setCustomerid(currentUser.getId());
			account.setOpeningbalance(deposit);

			int c = bankService.applyForNewAccount(account);

			System.out.println("------------Result---------");
			if (c != 0) {
				System.out.println("Account applied Successfully!");
			} else {
				System.out.println("Account application FAILED!");
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	private static void employeeMenu() {
		int ch = 0;
		do {
			System.out.println("\n------------EMPLOYEE MENU---------");
			System.out.println("1)Approve or reject Accounts");
			System.out.println("2)View customer's bank accounts");

			System.out.println("0)Back to Main Menu");
			System.out.println("-----------------");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {
			case 1:
				approveOrRejectAccount();
				break;
			case 2:
				viewCustomerAccounts();
				break;

			case 0:
				System.out.println("....0)Back to Main Menu");

			default:
				System.out.println("Invalid Choice... Please enter a proper choice.");
				break;
			}
		} while (ch != 0);
	}

	private static void approveOrRejectAccount() {
		System.out.println("....1)Approve or reject Accounts");
		BankService bankService = new BankServiceImpl();
		try {
			List<Account> appliedNewAccounts = bankService.getAppliedNewAccounts();
			if (appliedNewAccounts == null || appliedNewAccounts.size() == 0) {
				System.out.println("...NO Accounts pending for Approval....");
				return;
			}
			for (Account account : appliedNewAccounts) {

				System.out.println("NEW Account Request: Customer Id: " + account.getCustomerid() + ", AccounType: "
						+ account.getAccountType() + ", Openingbalance: " + account.getOpeningbalance());
				System.out.println("Enter Approve or Reject");
				String status = scanner.nextLine();
				if (status.equalsIgnoreCase("Approve")) {
					System.out.println("Enter Account Number");
					String accountNumber = scanner.nextLine();
					int c = bankService.approveAccount(account.getId(), accountNumber);
					if (c > 0) {
						System.out.println("...Account Approved....");
					} else {
						System.out.println("Account Approval FAILED.");
					}
				} else if (status.equalsIgnoreCase("Reject")) {
					int c = bankService.rejectAccount(account.getId());
					if (c > 0) {
						System.out.println("...Account REJECTED....");
					} else {
						System.out.println("Account REJECTION FAILED.");
					}
				} else {
					System.out.println("...Account Status NOT Changed....");

				}
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println("...ERROR: " + e.getMessage());
		}
	}

	private static void signup(Scanner scanner) {
		Person person = new Person();

		System.out.println("Enter below Details to Create user Account");

		try {
			System.out.println("Enter email:");
			String email = scanner.nextLine();

			PersonSearchService personSearchService = new PersonSearchServiceImpl();
			Person personByEmail = personSearchService.getPersonByEmail(email);
			if(personByEmail != null) {
				System.out.println("...Customer EXISTS for email: " + email +
						"\n Try with a different email id.");
				return;
			}
			else {
				person.setEmail(email);
			}
			
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

			PersonCRUDService personCrudService = new PersonCRUDServiceImpl();
			if (personCrudService.createPerson(person) == 1) {
				System.out.println("Person Registered Successfully with below details");
				System.out.println(person);
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("------------RETRY WITH VALID VALUES---------");
		}
	}

	private static void viewCustomerAccounts() {
		System.out.println("....2)View customer's bank accounts");
		PersonSearchService personSearchService = new PersonSearchServiceImpl();
		AccountSearchService accountSearchService = new AccountSearchServiceImpl();

		try {
			System.out.println("Enter Customer Email:");
			String email = scanner.nextLine();

			Person person = personSearchService.getPersonByEmail(email);

			if (person == null) {
				System.out.println("...No customer found for email: " + email);
				return;
			} else {
				System.out.println("...Customer found for email: " + person);
			}
			List<Account> accounts = accountSearchService.getAccountByCustomerId(person.getId());
			for (Account account : accounts) {

				System.out.println("Account of Customer Id: " + account.getCustomerid() + ", Accountnumber: "
						+ account.getAccountnumber() + ", AccounType: " + account.getAccountType()
						+ ", Openingbalance: " + account.getOpeningbalance() + ", Balance: " + account.getBalance()
						+ ", Status: " + account.getStatus());
			}
		} catch (BusinessException e) {
			log.error(e);
			e.printStackTrace();
			
		}
	}

}
