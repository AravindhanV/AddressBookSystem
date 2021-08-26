package com.bridgelabz.addressbooksystem;

public class Contacts {
	private final String firstName;
	private final String lastName;
	private final String city;
	private final String state;
	private final String zip;
	private final String phoneNumber;
	private final String email;

	public Contacts(String firstName, String lastName, String city, String state, String zip, String phoneNumber,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getState() {
		return state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getZip() {
		return zip;
	}
}
