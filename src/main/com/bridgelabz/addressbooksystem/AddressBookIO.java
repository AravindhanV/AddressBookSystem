package com.bridgelabz.addressbooksystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookIO {
	
	public void writeDataToFile(List<Contact> addressBook, String name) {
		StringBuffer contactBuffer = new StringBuffer();
		addressBook.forEach(contact -> {
			String contactString = contact.toString().concat("\n");
			contactBuffer.append(contactString);
		});
		
		try {
			Files.write(Paths.get(name),contactBuffer.toString().getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void writeDataToCsv(List<Contact> addressBook, String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try(Writer writer=Files.newBufferedWriter(Paths.get(name));
			){
				StatefulBeanToCsv<Contact> beanToCsv=new StatefulBeanToCsvBuilder(writer)
															.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
															.build();
				beanToCsv.write(addressBook);			
			}
	}
	
	public void writeDataToJson(List<Contact> addressBook, String name) throws IOException {
		Gson gson=new Gson();
		String json=gson.toJson(addressBook);
		FileWriter writer =new FileWriter(name);
		writer.write(json);		
		writer.close();
	}
	
	public List<String> readFromFile(String name) {
		List<String> listOfContacts=new ArrayList<String>();
		try {
			Files.lines(new File(name).toPath())
			.map(contact-> contact.trim())
			.forEach(contact -> {
								listOfContacts.add(contact);});
		}catch(IOException e) {
			e.printStackTrace();
		}
		return listOfContacts;
	}
	
	public int readFromCsv(String name) {
		String[] nextRecord= {};
		int noOfRecords=0;
		try {
            Reader reader = Files.newBufferedReader(Paths.get(name));
            CSVReader csvReader = new CSVReader(reader);
            while (((nextRecord = csvReader.readNext())) != null) {
            	noOfRecords++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return noOfRecords-1;
	}
	
	public List<Contact> readFromJson(String name) throws FileNotFoundException {
		Gson gson=new Gson();
		BufferedReader br=new BufferedReader(new FileReader(name));
		Contact[] contactsFile= gson.fromJson(br, Contact[].class);
		List<Contact> addressbook=Arrays.asList(contactsFile);
		return addressbook;
	}

	public void readFromDB() {
		// TODO Auto-generated method stub
		
	}

}