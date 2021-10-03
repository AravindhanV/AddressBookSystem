package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBookArray {
	
	HashMap<String, AddressBookImpl> addressBookArray;
	
	public AddressBookArray() {
		this.addressBookArray=new HashMap<>();
	}
	
	public void addAddressBook(String name) {
		AddressBookImpl newAddressBook=new AddressBookImpl(name);
		addressBookArray.put(name, newAddressBook);	
		
	}
	
	public AddressBookImpl getAddressBook(String name) {
		return addressBookArray.get(name);
	}
	
	public void searchAcrossCity(String name,String cityName) {
		for(AddressBookImpl addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(name,cityName);
		}
	}
	public void searchAcrossState(String name, String stateName) {
		for(AddressBookImpl addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(name, stateName);
		}
	}
	
	public void viewContactsInCity() {
		for(String city: AddressBookImpl.contactsInCities.keySet()) {
			System.out.println(city);
			List<String> contacts=AddressBookImpl.contactsInCities.get(city);
			contacts.stream().forEach(contactName -> System.out.print(contactName+" "));
		}
	}
	public void viewContactsInState() {
		
		for(String state: AddressBookImpl.contactsInStates.keySet()) {
			System.out.println(state);
			List<String> contacts=AddressBookImpl.contactsInStates.get(state);
			contacts.stream().forEach(contactName -> System.out.print(contactName+" "));
		}
	}
	public void numberOfContactsInCity() {
		for(String city: AddressBookImpl.contactsInCities.keySet()) {
			System.out.println(city+":");
			List<String> contacts=AddressBookImpl.contactsInStates.get(city);
			long count=contacts.stream().count();
			System.out.print(count);
		}
	}
	public void numberOfContactsInState() {
		for(String state: AddressBookImpl.contactsInStates.keySet()) {
			System.out.println(state+":");
			List<String> contacts=AddressBookImpl.contactsInStates.get(state);
			long count=contacts.stream().count();
			System.out.print(count);
		}
	}
	

}
