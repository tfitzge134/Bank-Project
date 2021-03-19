package com.bank.service.impl;

import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.PersonDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.dao.impl.PersonDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Person;
import com.bank.service.BankService;

public class BankServiceImpl implements BankService {

	@Override
	public Person login(String email, String password) throws BusinessException {
		if (!PersonValidations.isValidEmail(email)) {
			throw new BusinessException("Entered person email " + email + " is invalid");
		}
		PersonDAO personDAO = new PersonDAOImpl();
		return personDAO.verifyPassword(email, password);
	}

	@Override
	public int addAccount(Account account) throws BusinessException {
		if (!AccountValidations.isValidAccountType(account.getAccountType())) {
			throw new BusinessException("Entered accountType " + account.getAccountType() + " is invalid.");
		}
		if (!AccountValidations.isValidAccountNumber(account.getAccountnumber())) {
			throw new BusinessException("Entered AccountNumber " + account.getAccountnumber() + " is invalid."
					+ "\nFormat must be of: pattern n-nnnnnnnn");
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.addAccount(account);
	}

	@Override
	public int approveAccount(int id, String accountNumber) throws BusinessException {
		if (!AccountValidations.isValidAccountNumber(accountNumber)) {
			throw new BusinessException("Entered AccountNumber " + accountNumber + " is invalid."
					+ "\nFormat must be of: pattern n-nnnnnnnn");
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.approveAccount(id, accountNumber);
	}

	@Override
	public int rejectAccount(int id) throws BusinessException {
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.rejectAccount(id);
	}

	@Override
	public int applyForNewAccount(Account account) throws BusinessException {
		if (!AccountValidations.isValidAccountType(account.getAccountType())) {
			throw new BusinessException("Entered accountType " + account.getAccountType() + " is invalid.");
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.addAccount(account);
	}

	@Override
	public List<Account> getAppliedNewAccounts() throws BusinessException {
		AccountDAO accountDAO = new AccountDAOImpl();
		return accountDAO.getAppliedNewAccounts();
	}

}
