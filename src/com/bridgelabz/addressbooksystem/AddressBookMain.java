package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBookMain {
	private HashMap<String, AddressBook> addressBooks;
	private static Scanner scanner = new Scanner(System.in);

	public AddressBookMain() {
		this.addressBooks = new HashMap<>();
	}
	
	public static String readBookChoice() {
		System.out.println("Enter name of address book (-1 to exit):");
		String bookChoice = scanner.nextLine();
		return bookChoice;
	}
	
	public static int readActionChoice() {
		System.out.println("Enter 1. Add,2. Edit, 3. Delete, 4. Exit");
		int actionChoice = Integer.parseInt(scanner.nextLine());
		return actionChoice;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addAddressBook("book1");
		addressBookMain.addAddressBook("book2");
		
		String bookChoice = readBookChoice();
		if (bookChoice == "") {
			return;
		}
		while (true) {
			switch (readActionChoice()) {
			case 1:
				addressBookMain.addressBooks.get(bookChoice).addContact();
				break;

			case 2:
				addressBookMain.addressBooks.get(bookChoice).editContact();
				break;

			case 3:
				addressBookMain.addressBooks.get(bookChoice).deleteContact();
				break;

			case 4:
				System.exit(0);
				break;
			}
		}
	}

	public void addAddressBook(String bookName) {
		AddressBook addressBook = new AddressBook();
	}
}
