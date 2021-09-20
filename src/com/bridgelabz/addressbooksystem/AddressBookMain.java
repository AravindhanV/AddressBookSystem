package com.bridgelabz.addressbooksystem;

import java.io.IOException;
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

	public static String readCity() {
		System.out.println("Enter City:");
		String city = scanner.nextLine();
		return city;
	}
	
	public static String readState() {
		System.out.println("Enter State:");
		String state = scanner.nextLine();
		return state;
	}

	public static int readActionChoice() {
		System.out.println(
				"Enter 1. Add,2. Edit, 3. Delete, 4. Search by City across books, 5. Search by state across books, 6. Contact summary,7. Sorted Contacts,8. Write book to file,9. Read/Write contacts with CSV 10. Exit");
		int actionChoice = Integer.parseInt(scanner.nextLine());
		return actionChoice;
	}
	
	public void searchAcrossByCity(String city) {
		for(AddressBook addressBook : addressBooks.values()) {
			addressBook.findContactInCity(city);
		}
	}
	
	public void searchAcrossByState(String state) {
		for(AddressBook addressBook : addressBooks.values()) {
			addressBook.findContactInState(state);
		}
	}
	
	public void printTotalSummary() {
		for(AddressBook addressBook : addressBooks.values()) {
			addressBook.printSummary();
			System.out.println("--------------------------------");
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addAddressBook("book1");
		addressBookMain.addAddressBook("book2");

		String bookChoice = readBookChoice();
		if (bookChoice == "" || addressBookMain.addressBooks.get(bookChoice) == null) {
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
				String city = readCity();
				addressBookMain.searchAcrossByCity(city);
				break;

			case 5:
				String state = readState();
				addressBookMain.searchAcrossByState(state);
				break;
				
			case 6:
				addressBookMain.printTotalSummary();
				break;
				
			case 7: addressBookMain.addressBooks.get(bookChoice).getSortedContacts();
			break;
			
			case 8: addressBookMain.writeAddressBookToFile(bookChoice);
			break;
			
			case 9: addressBookMain.readAndWriteCSV(bookChoice);
			break;

			case 10:
				System.exit(0);
				break;
			}
		}
	}
	
	public void readAndWriteCSV(String bookName) {
		try {
			addressBooks.get(bookName).readFromCsv("read.csv");
			addressBooks.get(bookName).writeToCsv("write.csv");
		} catch (Exception e) {
		}
	}
	
	public void writeAddressBookToFile(String bookName) {
		addressBooks.get(bookName).writeToFile(bookName+".txt");
	}

	public void addAddressBook(String bookName) {
		AddressBook addressBook = new AddressBook();
		addressBooks.put(bookName, addressBook);
	}
}
