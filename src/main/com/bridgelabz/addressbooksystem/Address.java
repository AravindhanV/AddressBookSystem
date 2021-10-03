package com.bridgelabz.addressbooksystem;

public class Address {
	private int address_id;
	private String city;
	private String state;
	private int zipcode;
	
	public Address(int id, String city, String state, int zip) {
		this.address_id = id;
		this.city = city;
		this.state = state;
		this.zipcode = zip;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
}
