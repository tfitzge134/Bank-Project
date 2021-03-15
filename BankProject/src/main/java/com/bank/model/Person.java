package com.bank.model;

public class Person {
	private int personid;
	private boolean isEmployee;
	//private boolean isEmployee;
	//private boolean isEmployee;
	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsEmployee() {
		return isEmployee;
	}
	public void setIsEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	
	@Override
	public String toString() {
		return "Person [personid=" + personid + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", phonenumber=" + phonenumber + ", occupation=" + occupation + ", dob=" + dob + ", password="
				+ password + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public Person(int personid, String firstname, String lastname, String email, String phonenumber, String occupation,
			String dob, String password, String string) {
		super();
		this.personid = personid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.occupation = occupation;
		this.dob = dob;
		this.password = password;
		this.isEmployee();
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
	private String occupation;
	private String dob;
	private String  password;
	
}
