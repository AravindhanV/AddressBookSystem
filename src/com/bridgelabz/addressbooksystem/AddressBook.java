package com.bridgelabz.addressbooksystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class AddressBook {
	private HashMap<String, LinkedList<Contact>> contactsByCity;
	private HashMap<String, LinkedList<Contact>> contactsByState;
	private List<Contact> contacts;
	private static Scanner scanner = new Scanner(System.in);

	public AddressBook() {
		this.contacts = new LinkedList<Contact>();
		this.contactsByCity = new HashMap<>();
		this.contactsByState = new HashMap<>();
	}
	
	public void findContactInCity(String cityName) {
		contacts.stream().filter(c -> c.getCity().equals(cityName)).peek(c -> {
			System.out.println(c.getFirstName()+" : "+cityName);
		});
	}
	
	public void writeToFile(String fileName) {
		StringBuffer contactBuffer = new StringBuffer();
		contacts.forEach(contact -> {
			String contactString = contact.toString().concat("\n");
			contactBuffer.append(contactString);
		});
		
		try {
			Files.write(Paths.get(fileName),contactBuffer.toString().getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printSummary() {
		System.out.println("Summary by City:");
		printCitySummary();
		System.out.println("\nSummary by state:");
		printStateSummary();
	}
	
	public void getSortedContacts()
	{
		contacts.stream().sorted((c1,c2) -> c1.compareTo(c2)).peek(c -> {
			System.out.println(c.getFirstName());
		});
	}
	public void printCitySummary() {
		contactsByCity.keySet().stream().peek(c -> {
			System.out.println(c+" : "+contactsByCity.get(c).stream().count());
		});
	}
	
	public void printStateSummary() {
		contactsByState.keySet().stream().peek(s -> {
			System.out.println(s+" : "+contactsByState.get(s).stream().count());
		});
	}
	
	public void findContactInState(String stateName) {
		contacts.stream().filter(c -> c.getState().equals(stateName)).peek(c -> {
			System.out.println(c.getFirstName()+" : "+stateName);
		});
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
		if(contactsByCity.get(city)==null) {
			contactsByCity.put(city, new LinkedList<>());
		}
		contactsByCity.get(city).add(contact);
		
		if(contactsByState.get(state)==null) {
			contactsByState.put(state, new LinkedList<>());
		}
		contactsByState.get(state).add(contact);
		
		
	}
	
	private boolean checkIfContactExists(Contact contact) {
		return contacts.stream().filter(c -> c.equals(contact)).findFirst().orElse(null) != null;
	}

	public void readFromCsv(String fileName) throws IOException{
		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));) {
			CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();
			CSVReader csvReader = new CSVReaderBuilder(reader)
					.withCSVParser(parser)
                    .withSkipLines(1)
                    .build();
			List<String[]> allData = csvReader.readAll();
			contacts = new LinkedList<>();
			System.out.println(allData.size());

			for(int index=0;index<allData.size()-1;index++) {
				String[] row = allData.get(index);

				System.out.println("First Name: " + row[0]);
				System.out.println("Last Name: " + row[1]);
				System.out.println("City: " + row[2]);
				System.out.println("State: " + row[3]);
				System.out.println("zip: "+row[4]);
				System.out.println("Phone: "+row[5]);
				System.out.println("Email: "+row[6]);
				System.out.println("===============");
				contacts.add(new Contact(row[0],row[1],row[2],row[3],row[4],row[5],row[6]));
			}
			
			reader.close();
		}
	}

	public void writeToCsv(String fileName) {
		File file = new File("write.csv");
		System.out.println("---");
		
		try {
	        FileWriter outputfile = new FileWriter(file);
	        CSVWriter writer = new CSVWriter(outputfile);
	        
	        List<String[]> data = new ArrayList();
	        for(Contact c : contacts) {
	        	String[] row = {c.getFirstName(),c.getLastName(),c.getCity(),c.getState(),c.getZip(),c.getPhoneNumber(),c.getEmail()};
	        	data.add(row);
	        }
	        
	        writer.writeAll(data);
	        writer.close();
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		
	}
}
