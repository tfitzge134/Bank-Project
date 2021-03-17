package com.bank.service.impl;

public class PersonValidations {

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

	public static boolean isValidPassword(String password) {
		if (password != null && password.matches("[a-zA-Z]{3,20}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidEmail(String email) {
		if (email != null && email.contains("@") && email.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}

}
