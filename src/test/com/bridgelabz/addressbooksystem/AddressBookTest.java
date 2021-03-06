package com.bridgelabz.addressbooksystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bridgelabz.addressbooksystem.AddressBookImpl.IOService;
import com.bridgelabz.addressbooksystem.AddressBookDBService.BookType;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookTest {
	static AddressBookImpl addressBook;
	static Contact newContact;
	
	@BeforeClass
    public static void init() {
		addressBook= new AddressBookImpl("addressbook1");
		String firstName="Alice";
		String lastName="Bob";
		String address="abc";
		String city="city1";
		String state="state1";
		String zipCode="123456";
		String phoneNumber="1234567890";
		String emailId="abc@gmail.com";
		newContact=new Contact(firstName,lastName,city,state,zipCode,phoneNumber,emailId);
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
	
	@Test
	public void givenAddressBookDB_WhenRetrieved_ShouldMatchRowCount() {
		List<Contact> contactList=new AddressBookIO().readFromDB();
		Assert.assertEquals(1, contactList.size());
	}
	
	@Test
	public void givenAddressBookName_whenUpdated_shouldSyncWithDB() throws SQLException{
		boolean result = new AddressBookIO().updateAddressBook("test1@abc.com","city1new","state1new",9876, IOService.DB_IO);
		List<Contact> contactList=new AddressBookIO().readFromDB();
		Assert.assertTrue(result);

	}
	
	@Test
	public void givenDateRange_WhenRetrievedInDateRange_ShouldMatchRowCount() {
		List<Contact> contactList=new AddressBookIO().readFromDB("book1",LocalDate.now());
		Assert.assertEquals(1, contactList.size());
	}	
	
	@Test
	public void givenCity_WhenRetrievedByCity_ShouldMatchRowCount() {
		List<Contact> contactList=new AddressBookIO().readFromDBByCity("city1new");
		Assert.assertEquals(1, contactList.size());
	}	
	
	@Test
	public void givenContact_WhenInserted_ShouldSyncWithDB() {
		AddressBookIO addressBookIO = new AddressBookIO();
		int size = addressBookIO.readFromDB().size();
		List<Contact> contactList = new AddressBookIO().addContact("test_first", "test_last", "test_city", "test_state", 456799, 147258369,"test_book",BookType.FRIEND);
		Assert.assertEquals(size+1,contactList.size());
	}

}
