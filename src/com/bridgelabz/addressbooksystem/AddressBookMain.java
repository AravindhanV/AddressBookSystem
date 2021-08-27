package com.bridgelabz.addressbooksystem;

import com.bridgelabz.addressbooksystem.*;
import java.util.*;

public class AddressBookMain {
	private AddressBook[] addressBooks;
	private int noOfAddressBooks;

	public AddressBookMain() {
		this.addressBooks = new AddressBook[5];
		this.noOfAddressBooks = 0;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addAddressBook();
		addressBookMain.addAddressBook();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ID of address book (-1 to exit):");
		int bookChoice = Integer.parseInt(scanner.nextLine());
		if (bookChoice == -1) {
			return;
		}
		while (true) {
			System.out.println("Enter 1. Add,2. Edit, 3. Delete, 4. Exit");
			int actionChoice = Integer.parseInt(scanner.nextLine());
			switch (actionChoice) {
			case 1:
				addressBookMain.addressBooks[bookChoice].addContact();
				break;

			case 2:
				addressBookMain.addressBooks[bookChoice].editContact();
				break;

			case 3:
				addressBookMain.addressBooks[bookChoice].deleteContact();
				break;

			case 4:
				System.exit(0);
				break;
			}
		}
	}

	public void addAddressBook() {
		addressBooks[noOfAddressBooks++] = new AddressBook(noOfAddressBooks);
	}
}
