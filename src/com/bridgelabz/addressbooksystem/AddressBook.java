package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBook {
	private List<Contact> contacts;
	private static Scanner scanner = new Scanner(System.in);

	public AddressBook(int id) {
		this.contacts = new LinkedList<Contact>();
	}

	public void editContact() {
		System.out.println("Enter first name of person you want edit:");
		String firstName = scanner.nextLine();
		Contact contactToEdit = contacts.stream().filter(c -> c.getFirstName().equals(firstName)).findFirst().orElse(null);
		if(contactToEdit == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		System.out
				.println("Select Option:\n1. First Name\n2. Last Name\n 3. City\n4. State\n5. Zip\n6. Phone\n7. Email");
		int choice = Integer.parseInt(scanner.nextLine());

		switch (choice) {
		case 1:
			System.out.println("Enter new first name:");
			String newFirstName = scanner.nextLine();
			contactToEdit.setFirstName(newFirstName);
			for(int i=0;i<contacts.size();i++) {
				if(contacts.get(i).getFirstName().equals(firstName)) {
					contacts.remove(i);
					break;
				}
			}
			contacts.add(contactToEdit);
			break;

		case 2:
			System.out.println("Enter new last name:");
			contactToEdit.setLastName(scanner.nextLine());
			break;

		case 3:
			System.out.println("Enter new city");
			contactToEdit.setCity(scanner.nextLine());
			break;

		case 4:
			System.out.println("Enter new State");
			contactToEdit.setState(scanner.nextLine());
			break;

		case 5:
			System.out.println("Enter new Zip");
			contactToEdit.setZip(scanner.nextLine());
			break;

		case 6:
			System.out.println("Enter new Phone");
			contactToEdit.setPhoneNumber(scanner.nextLine());
			break;

		case 7:
			System.out.println("Enter new email");
			contactToEdit.setEmail(scanner.nextLine());
			break;

		default:
			System.err.println("Invalid Option");
		}
	}

	public void deleteContact() {
		System.out.println("Enter first name number of person you want to delete:");
		String firstName = scanner.nextLine();
		for(int i=0;i<contacts.size();i++) {
			if(contacts.get(i).getFirstName().equals(firstName)) {
				contacts.remove(i);
				System.out.println("Successfully Deleted");
				return;
			}
		}
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

		Contact contact = new Contact(firstName, lastName, city, state, zip, phoneNumber, email);
		if(!checkIfContactExists(contact)) {
			contacts.add(contact);
		}
	}
	
	private boolean checkIfContactExists(Contact contact) {
		return contacts.stream().filter(c -> c.equals(contact)).findFirst().orElse(null) != null;
	}
}
