package com.bridgelabz.addressbooksystem;

import java.time.LocalDate;

import com.bridgelabz.addressbooksystem.AddressBookDBService.BookType;
import com.opencsv.bean.CsvBindByName;

public class Contact implements Comparable<Contact>{
	@CsvBindByName(column="fname")
	private String firstName;
	@CsvBindByName(column="lname")
	private String lastName;
	@CsvBindByName(column="city")
	private String city;
	@CsvBindByName(column="state")
	private String state;
	@CsvBindByName(column="zip")
	private String zip;
	@CsvBindByName(column="phone")
	private String phoneNumber;
	@CsvBindByName(column="email")
	private String email;
	private int id;
	private LocalDate dateAdded;

	public Contact(String firstName, String lastName, String city, String state, String zip, String phoneNumber,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Contact(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Contact(int id, String firstName, String lastName, LocalDate dateAdded) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateAdded = dateAdded;
	}
	
	
	public String toString() {
		return firstName+", "+lastName+", "+city+", "+state+", "+zip+", "+phoneNumber+", "+email;
	}
	
	public boolean equals(Contact c) {
		return this.firstName.equals(c.getFirstName());
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int compareTo(Contact c) {
		return this.firstName.compareTo(c.getFirstName());
	}
}
