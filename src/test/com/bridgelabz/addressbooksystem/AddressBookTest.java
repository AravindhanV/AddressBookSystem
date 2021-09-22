package com.bridgelabz.addressbooksystem;

import org.junit.Assert;
import org.junit.Test;

public class AddressBookTest {
	@Test
	public void givenContacts_shouldAddToAddressBook() {
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

}
