package com.bank.service.impl;

public class PersonValidations {
	public static boolean isValidId(int id) {
		if (id < 100 || id > 1000) {
			return false;
		} else {
			return true;
		}
	}
	public static boolean isEmployee(boolean isemployee) {
		if (isemployee = false) {
			return false;
			//go to employee screen
			
		} else {
			return true;
			//go to customer screen
		}
	}

	public static boolean isValidPersonName(String name) {
		if (name != null && name.matches("[a-zA-Z]{3,20}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidPersonCity(String city) {
		if (city != null && city.matches("[a-zA-Z]{3,5}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidPersonGender(String gender) {
		if (gender != null && gender.matches("[mMfF]{1}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidPersonAge(int age) {
		if (age > 18 && age < 150) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isValidPassword(String password) {
		if (password != null && password.matches("[a-zA-Z]{3,20}")) {
			return true;
		} else {
			return false;
		}
	}

}
