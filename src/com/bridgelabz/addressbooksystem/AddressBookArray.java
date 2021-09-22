package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBookArray {
	
	HashMap<String, AddressBook> addressBookArray;
	
	public AddressBookArray() {
		this.addressBookArray=new HashMap<>();
	}
	
	public void addAddressBook(String name) {
		AddressBook newAddressBook=new AddressBook(name);
		addressBookArray.put(name, newAddressBook);	
		
	}
	
	public AddressBook getAddressBook(String name) {
		return addressBookArray.get(name);
	}
	
	public void searchAcrossCity(String name,String cityName) {
		for(AddressBook addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(name,cityName);
		}
	}
	public void searchAcrossState(String name, String stateName) {
		for(AddressBook addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(name, stateName);
		}
	}
	
	public void viewContactsInCity() {
		for(String city: AddressBook.contactsInCities.keySet()) {
			System.out.println(city);
			List<String> contacts=AddressBook.contactsInCities.get(city);
			contacts.stream().forEach(contactName -> System.out.print(contactName+" "));
		}
	}
	public void viewContactsInState() {
		
		for(String state: AddressBook.contactsInStates.keySet()) {
			System.out.println(state);
			List<String> contacts=AddressBook.contactsInStates.get(state);
			contacts.stream().forEach(contactName -> System.out.print(contactName+" "));
		}
	}
	public void numberOfContactsInCity() {
		for(String city: AddressBook.contactsInCities.keySet()) {
			System.out.println(city+":");
			List<String> contacts=AddressBook.contactsInStates.get(city);
			long count=contacts.stream().count();
			System.out.print(count);
		}
	}
	public void numberOfContactsInState() {
		for(String state: AddressBook.contactsInStates.keySet()) {
			System.out.println(state+":");
			List<String> contacts=AddressBook.contactsInStates.get(state);
			long count=contacts.stream().count();
			System.out.print(count);
		}
	}
	

}
