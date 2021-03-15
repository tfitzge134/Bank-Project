package com.bank.model;

import com.bank.dao.AddressDAO;

public class Address {
	int addressid;
	String address;
	String City;
	String state;
	String country;
	int personid;
	
	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", address=" + address + ", City=" + City + ", state=" + state
				+ ", country=" + country + ", personid=" + personid + "]";
	}

	public Address() {
		
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public Address(int addressid, String address, String city, String state, String country, int personid) {
		super();
		this.addressid = addressid;
		this.address = address;
		City = city;
		this.state = state;
		this.country = country;
		this.personid = personid;
	}
	
	

}
