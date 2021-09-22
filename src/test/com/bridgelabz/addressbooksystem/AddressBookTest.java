package com.bridgelabz.addressbooksystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookTest {
	static AddressBook addressBook;
	
	@BeforeClass
    public static void init() {
		addressBook= new AddressBook("addressbook1");
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
    }
	
	@Test
	public void givenContacts_ShouldAddToAddressBook() {
		Assert.assertEquals(1, addressBook.size());
	}
	
	@Test
	public void givenAddressBook_WhenWrittenToFile_ShouldMatchEntries() {
		new AddressBookIO().writeDataToFile(addressBook.addressBook, "book_file.txt");
		
		List<String> contacts= new AddressBookIO().readFromFile("book_file.txt");
		Assert.assertEquals( 1,contacts.size());
		
	}
	
	@Test
	public void givenAddressBook_whenWrittenToCSVFile_shouldMatchEntries() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		new AddressBookIO().writeDataToCsv(addressBook.addressBook, "book_csv.csv");
		
		int noOfRows=new AddressBookIO().readFromCsv("book_csv.csv");
		Assert.assertEquals( 1,noOfRows);
	}
	
	@Test
	public void givenAddressBook_whenWrittenToJSONFile_shouldMatchEntries() throws IOException {
		new AddressBookIO().writeDataToJson(addressBook.addressBook, "book_json.json");
		
		List<Contact> contacts=new AddressBookIO().readFromJson("book_json.json");
		Assert.assertEquals( 1,contacts.size());
	}

}