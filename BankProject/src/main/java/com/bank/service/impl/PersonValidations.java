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
	
	public static boolean isValidDob(String dob) {
		//Pattern: yyyy-mm-dd
		if (dob != null && !dob.isEmpty()) {
			if ((dob.length() == 10) && (dob.charAt(4) == '-') && (dob.charAt(7) == '-')) {
				for(int i =0; i <10; i++) {
					if((i == 4) || i == 7) {
						continue;
					}
					char c = dob.charAt(i);
					if(!Character.isDigit(c)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

}
