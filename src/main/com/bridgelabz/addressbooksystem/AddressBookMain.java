package com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.util.*;

import com.bridgelabz.addressbooksystem.AddressBookImpl.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookMain {
static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		System.out.println("Welcome to Address Book");
		getMenu();
	}
	
	public static void getMenu() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		AddressBookArray addressBookArray=new AddressBookArray();
		
		
		while(true) {
			System.out.println("1.Add new Address Book\n2.Edit Existing\n3.Search in City\n4.Search in State\n5.Display Contacts in city\n6.Display Contacts in state\n7.Display Number of Contacts in Cities\n8.Display number of Contacts in States\n9.Write to File\n10.Read From File\n11.Exit");
			int option=sc.nextInt();
			contactMenu:
			switch(option) {
			
				case 1: System.out.println("Enter name of Address Book");
						String name=sc.next();
						addressBookArray.addAddressBook(name);
						break;
				
				case 2:	System.out.println("Enter name of address book to be edited");
						String addressBookName=sc.next();
						AddressBookImpl addressBookToBeEdited=addressBookArray.getAddressBook(addressBookName);
						while(true) {
							System.out.println("1.Add Contact\n2.Edit Contact\n3.Delete Contact\n4.Exit\nSelect option");
							int choice=sc.nextInt();
							switch(choice) {

							case 1: Contact contactDetails=addressBookToBeEdited.createContact();
									addressBookToBeEdited.addContact(contactDetails);
									break;
							case 2: addressBookToBeEdited.editContact();
									break;
							case 3: addressBookToBeEdited.deleteContact();
									break;
							case 4: break contactMenu;
				
			
							}
						}
						
				case 3: System.out.println("Enter name of person");
						String searchNameInCity=sc.next();
						System.out.println("Enter name of city");
						String cityName=sc.next();
						addressBookArray.searchAcrossCity(searchNameInCity,cityName);
						break;
				
				case 4: System.out.println("Enter name of person");
						String searchNameInState=sc.next();
						System.out.println("Enter name of state");
						String stateName=sc.next();
						addressBookArray.searchAcrossState(searchNameInState,stateName);
						break;
				
				case 5: addressBookArray.viewContactsInCity();
						break;
				
				case 6: addressBookArray.viewContactsInState();
						break;
						
				case 7: addressBookArray.numberOfContactsInCity();
						break;
				
				case 8: addressBookArray.numberOfContactsInState();
						break;
				
				case 9: System.out.println("Enter name of AddressBook to be written to file");
						String nameOfAddressBook=sc.next();
						AddressBookImpl addressBook=addressBookArray.getAddressBook(nameOfAddressBook);
						addressBook.writeData(nameOfAddressBook+".json",IOService.JSON_IO);
						break;
				
				case 10: System.out.println("Enter name of AddressBook to be read from file");
						String nameOfAddressBookRead=sc.next();
						System.out.println(addressBookArray.getAddressBook(nameOfAddressBookRead));
						AddressBookImpl.readData(nameOfAddressBookRead+".json", IOService.JSON_IO);
						break;
				
				case 11: System.exit(0);
		
			
			}
		}
	}
}
