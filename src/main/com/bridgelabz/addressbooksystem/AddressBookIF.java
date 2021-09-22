package com.bridgelabz.addressbooksystem;

public interface AddressBookIF {
	public Contact createContact();

	public void addContact(Contact newContact);

	public void editContact();

	public void deleteContact();

	public void findContactInCity(String name, String cityName);

	public void findContactInState(String name, String stateName);

	public void sortAddressBook();

	public void sortByCity();

	public void sortByState();

	public void sortByZip();

	public int size();
}