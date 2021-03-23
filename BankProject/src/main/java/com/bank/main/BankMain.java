package com.bank.main;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

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

		log.info("Welcome to Wonderlad Bank WHERE YOU MONEY IS OUR MONEY");
		log.info("================================================");
		log.info("Teresa's Bank App V1.0");
		log.info("================================================");
		int ch = 0;
		do {
			log.info("\n-----------------");
			log.info("Bank MENU");
			log.info("-----------------");
			log.info("1)Login");
			log.info("2)Signup for User Account");

			log.info("0)Exit");

			log.info("Please enter an appropriate Search Option(1-8)");
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
				log.info("Under construction");

				break;
			case 0:
				log.info("Thank You for using the Bank APP V1.0.....");
				break;

			default:
				log.info("Invalid Choice... Please enter a proper choice between 1-? only.......");
				break;
			}
		} while (ch != 0);
		log.info("Bank Appplication Ended.\n------------");
	}

	private static void login(Scanner scanner) {
		log.info("Enter below Details to Login");

		log.info("Enter email:");
		String email = scanner.nextLine();

		log.info("Enter password:");
		String password = scanner.nextLine();

		try {
			BankService bankService = new BankServiceImpl();
			Person person = bankService.login(email, password);
			if (person != null) {
				currentUser = person;
				log.info("------------Login result---------");
				log.info("Person LOGIN Success!");
				log.info(person);

				if (person.isEmployee()) {
					employeeMenu();
				} else {
					customerMenu();
				}
			} else {
				log.info("Person LOGIN FAILED!");
			}
		} catch (BusinessException e) {
			//log.error(e);
			log.error("....ERROR: " + e.getMessage());
			log.info("------------RETRY WITH VALID VALUES---------");
		}
	}

	private static void customerMenu() {
		int ch = 0;
		do {
			log.info("------------CUSTOMER MENU---------");
			log.info("1)Apply for new Bank Account");
			log.info("2)View My Accounts");
			log.info("3)View Account Balance");
			log.info("4)Deposit");
			log.info("5)Withdraw");
			log.info("6)Transfer Money");
			log.info("0)Back to Main Menu");
			log.info("-----------------");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {
			case 1:
				log.info("1)Apply for new Bank Account");
				applyForNewBankAccount();
				break;
			case 2:
				log.info("....2)View My Accounts");
				viewMyAccounts();
				break;
			case 3:
				log.info("....3)View Account Balance");
				viewAccountBalance();
				break;
			case 4:
				log.info("....4)Deposit");
				deposit();
				break;
			case 5:
				log.info("....5)Withdraw");
				withdraw();
				break;
			case 6:
				log.info("6)Transfer Money");
				transferMoney();
				break;
			case 0:
				log.info("....0)Back to Main Menu");

			default:
				log.info("Invalid Choice... Please enter a proper choice.");
				break;
			}

		} while (ch != 0);
	}
	
	private static void transferMoney() {
		log.info("Enter below Details to withdraw.");

		log.info("Enter SOURCE account number:");
		String sourceAccount = scanner.nextLine();
		
		log.info("Enter DESTINATION account number:");
		String destAccount = scanner.nextLine();

		log.info("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			log.info("Enter valid amount for withdraw:");
			log.info("---------------------");
			return;
		}
		
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			//log.info("Current Balance: " + account.getBalance());
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.transfer(sourceAccount, destAccount, amount);
			//double b = account.getBalance();
			if (c > 0) {
				log.info("Transfer success.");
				Account source = accountSearchService.getAccountByAccountNumber(sourceAccount);
				Account dest = accountSearchService.getAccountByAccountNumber(destAccount);
				log.info("Source Account New Balance: " + source.getBalance());
				log.info("Dest Account New Balance: " + dest.getBalance());
			}
			else {
				log.info("Transfer failed");
			}
		} catch (BusinessException e) {
			//log.error(e);
			//e.printStackTrace();
			log.error("....ERROR: " + e.getMessage());
		}

	}
///////////////////////////////////////////////////////////
	private static void withdraw() {
		log.info("Enter below Details to withdraw.");

		log.info("Enter account number:");
		String accountnumber = scanner.nextLine();

		log.info("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			log.info("Enter valid amount for withdraw:");
			log.info("---------------------");
			return;
		}
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				log.info("....Account NOT found.");
				return;
			}
			//log.info("Current Balance: " + account.getBalance());
			if (account.getBalance() < amount) {
				log.info( "INSUFFFIENT_BALANCE");
				return;
				
			}
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.withdraw(accountnumber, amount);
			if (c > 0) {
				log.info("Withdrawal success.");
				Account account1 = accountSearchService.getAccountByAccountNumber(accountnumber);
				log.info("New Balance: " + account1.getBalance());
			} 
			else {
				log.info("Withdrawal failed");
			}
		} catch (BusinessException e) {
			log.error("....ERROR: " + e.getMessage());
			//log.error(e);
			//e.printStackTrace();
		}
	}

	private static void deposit() {
		log.info("Enter below Details to deposit.");

		log.info("Enter account number:");
		String accountnumber = scanner.nextLine();

		log.info("Enter amount:");
		double amount = 0.0;
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			log.info("Enter valid amount for deposit:");
			log.info("---------------------");
			return;
		}
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				log.info("....Account NOT found.");
				return;
			}
			log.info("Current Balance: " + account.getBalance());
			AccountService accountService = new AccountServiceImpl();
			int c = accountService.deposit(accountnumber, amount);
			if (c > 0) {
				log.info("Deposit success.");
				Account account1 = accountSearchService.getAccountByAccountNumber(accountnumber);
				log.info("New Balance: " + account1.getBalance());

			} else {
				log.info("Deposit failed.");
			}
		} catch (BusinessException e) {
			log.error("....ERROR: " + e.getMessage());
			//log.error(e);
			//e.printStackTrace();

		}
	}
////////
	private static void viewAccountBalance() {
		// TODO Auto-generated method stub
		//log.info("....2)View balance");
		
		log.info("Enter account number:");
		String accountnumber = scanner.nextLine();
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			Account account = accountSearchService.getAccountByAccountNumber(accountnumber);
			if(account == null) {
				log.info("....Account NOT found.");
				return;
			}
			log.info("Current Balance: " + account.getBalance());
		} catch (BusinessException e) {
			log.error("....ERROR: " + e.getMessage());
			//log.error(e);
			//e.printStackTrace();
		}
	
	}

	private static void viewMyAccounts() {
		log.info("....2)View My Accounts");
		try {
			AccountSearchService accountSearchService = new AccountSearchServiceImpl();
			List<Account> accounts = accountSearchService.getAccountByCustomerId(currentUser.getId());
			if (accounts == null || accounts.size() == 0) {
				log.info("...NO Accounts Found....");
				return;
			}
			for (Account account : accounts) {
				if(account.getAccountnumber() == null) {
					log.info("Account Number: PENDING"
							+ ", AccounType: " + account.getAccountType() 
							+ ", Openingbalance: " + account.getOpeningbalance()
							+ ", Status: " + account.getStatus()
							);
				}
				else {
					
					log.info("Account Number: " + account.getAccountnumber() 
					+ ", AccounType: " + account.getAccountType() 
					+ ", Balance: " + account.getBalance()
					+ ", Status: " + account.getStatus()
							);
				}
			}
		} catch (BusinessException e) {
			log.error("....ERROR: " + e.getMessage());
			//log.error(e);
			//e.printStackTrace();
		}
		
	}

	private static void applyForNewBankAccount() {
		log.info("Enter below Details to open new account.");

		log.info("Enter account type (SA or CA):");
		String accountType = scanner.nextLine();

		log.info("Enter opening deposit:");
		double deposit = 0.0;
		try {
			deposit = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException ex) {
			log.info("Enter valid amount for deposit:");
			log.info("---------------------");
			return;
		}

		try {
			BankService bankService = new BankServiceImpl();
			Account account = new Account();
			account.setAccountType(accountType);
			account.setCustomerid(currentUser.getId());
			account.setOpeningbalance(deposit);

			int c = bankService.applyForNewAccount(account);

			log.info("------------Result---------");
			if (c != 0) {
				log.info("Account applied Successfully!");
			} else {
				log.info("Account application FAILED!");
			}
		} catch (BusinessException e) {
			log.error("....ERROR: " + e.getMessage());
			//log.error(e);
			//e.printStackTrace();
		}

	}

	private static void employeeMenu() {
		int ch = 0;
		do {
			log.info("\n------------EMPLOYEE MENU---------");
			log.info("1)Approve or reject Accounts");
			log.info("2)View customer's bank accounts");

			log.info("0)Back to Main Menu");
			log.info("-----------------");
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
				log.info("....0)Back to Main Menu");

			default:
				log.info("Invalid Choice... Please enter a proper choice.");
				break;
			}
		} while (ch != 0);
	}

	private static void approveOrRejectAccount() {
		log.info("....1)Approve or reject Accounts");
		BankService bankService = new BankServiceImpl();
		try {
			List<Account> appliedNewAccounts = bankService.getAppliedNewAccounts();
			if (appliedNewAccounts == null || appliedNewAccounts.size() == 0) {
				log.info("...NO Accounts pending for Approval....");
				return;
			}
			for (Account account : appliedNewAccounts) {

				log.info("NEW Account Request: Customer Id: " + account.getCustomerid() + ", AccounType: "
						+ account.getAccountType() + ", Openingbalance: " + account.getOpeningbalance());
				log.info("Enter Approve or Reject");
				String status = scanner.nextLine();
				if (status.equalsIgnoreCase("Approve")) {
					log.info("Enter Account Number");
					String accountNumber = scanner.nextLine();
					int c = bankService.approveAccount(account.getId(), accountNumber);
					if (c > 0) {
						log.info("...Account Approved....");
					} else {
						log.info("Account Approval FAILED.");
					}
				} else if (status.equalsIgnoreCase("Reject")) {
					int c = bankService.rejectAccount(account.getId());
					if (c > 0) {
						log.info("...Account REJECTED....");
					} else {
						log.info("Account REJECTION FAILED.");
					}
				} else {
					log.info("...Account Status NOT Changed....");

				}
			}
		} catch (BusinessException e) {
			//log.error(e);
			//e.printStackTrace();
			log.info("...ERROR: " + e.getMessage());
		}
	}

	private static void signup(Scanner scanner) {
		Person person = new Person();

		log.info("Enter below Details to Create user Account");

		try {
			log.info("Enter email:");
			String email = scanner.nextLine();

			PersonSearchService personSearchService = new PersonSearchServiceImpl();
			Person personByEmail = personSearchService.getPersonByEmail(email);
			if(personByEmail != null) {
				log.info("...Customer EXISTS for email: " + email +
						"\n Try with a different email id.");
				return;
			}
			else {
				person.setEmail(email);
			}
			
			log.info("Enter password:");
			person.setPassword(scanner.nextLine());

			log.info("Enter DoB in the format yyyy-mm-dd");
			person.setDob(scanner.nextLine());

			log.info("Enter First Name:");
			person.setFirstname(scanner.nextLine());

			log.info("Enter Last Name:");
			person.setLastname(scanner.nextLine());

			log.info("Enter phonenumber:");
			person.setPhonenumber(scanner.nextLine());

			log.info("Enter Is Employee:");
			person.setEmployee(Boolean.valueOf(scanner.nextLine()));

			PersonCRUDService personCrudService = new PersonCRUDServiceImpl();
			if (personCrudService.createPerson(person) == 1) {
				log.info("Person Registered Successfully with below details");
				log.info(person);
			}
		} catch (BusinessException e) {
			//log.error(e);
			//e.printStackTrace();
			log.info(e.getMessage());
			log.info("------------RETRY WITH VALID VALUES---------");
		}
	}

	private static void viewCustomerAccounts() {
		log.info("....2)View customer's bank accounts");
		PersonSearchService personSearchService = new PersonSearchServiceImpl();
		AccountSearchService accountSearchService = new AccountSearchServiceImpl();

		try {
			log.info("Enter Customer Email:");
			String email = scanner.nextLine();

			Person person = personSearchService.getPersonByEmail(email);

			if (person == null) {
				log.info("...No customer found for email: " + email);
				return;
			} else {
				log.info("...Customer found for email: " + person);
			}
			List<Account> accounts = accountSearchService.getAccountByCustomerId(person.getId());
			for (Account account : accounts) {

				log.info("Account of Customer Id: " + account.getCustomerid() + ", Accountnumber: "
						+ account.getAccountnumber() + ", AccounType: " + account.getAccountType()
						+ ", Openingbalance: " + account.getOpeningbalance() + ", Balance: " + account.getBalance()
						+ ", Status: " + account.getStatus());
			}
		} catch (BusinessException e) {
			//log.error(e);
			//e.printStackTrace();
			
		}
	}

}
