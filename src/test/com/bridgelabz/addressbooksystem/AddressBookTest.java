package com.bridgelabz.addressbooksystem;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookTest {
	@Test
	public void givenContacts_ShouldAddToAddressBook() {
		AddressBook addressBook= new AddressBook("addressbook1");
		String firstName="Alice";
		String lastName="Bob";
		String address="abc";
		String city="city1";
		String state="state1";
		String zipCode="123456";
		String phoneNumber="1234567890";
		String emailId="abc@gmail.com";
		Contact newContact=new Contact(firstName,lastName,city,state,zipCode,phoneNumber,emailId);
		addressBook.addContact(newContact);
		Assert.assertEquals(1, addressBook.size());
	}
	
	@Test
	public void givenAddressBook_WhenWrittenToFile_ShouldMatchEntries() {
		AddressBook addressBook= new AddressBook("addressbook1");
		String firstName="Alice";
		String lastName="Bob";
		String address="abc";
		String city="city1";
		String state="state1";
		String zipCode="123456";
		String phoneNumber="1234567890";
		String emailId="abc@gmail.com";
		Contact newContact=new Contact(firstName,lastName,city,state,zipCode,phoneNumber,emailId);
		addressBook.addContact(newContact);
		
		new AddressBookIO().writeDataToFile(addressBook.addressBook, "book_file.txt");
		
		List<String> contacts= new AddressBookIO().readFromFile("book_file.txt");
		Assert.assertEquals( 1,contacts.size());
		
	}

}
