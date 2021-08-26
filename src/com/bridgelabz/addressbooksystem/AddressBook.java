package com.bridgelabz.addressbooksystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
	private ArrayList<Contact> contacts;
	private static Scanner scanner = new Scanner(System.in);;

	public AddressBook() {
		this.contacts = new ArrayList<Contact>();
	}

	public void addContact() {
		System.out.println("Enter the contact details:");
		System.out.println("Enter first name:");
		String firstName = scanner.nextLine();
		System.out.println("Enter last name");
		String lastName = scanner.nextLine();
		System.out.println("Enter city");
		String city = scanner.nextLine();
		System.out.println("Enter state");
		String state = scanner.nextLine();
		System.out.println("Enter Zip");
		String zip = scanner.nextLine();
		System.out.println("Enter Phone");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter email");
		String email = scanner.nextLine();

		contacts.add(new Contact(firstName, lastName, city, state, zip, phoneNumber, email));
	}
}
