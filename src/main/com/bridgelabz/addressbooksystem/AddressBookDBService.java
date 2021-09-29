package com.bridgelabz.addressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddressBookDBService {
	private Connection getConnection() throws SQLException {
		String jdbcURL="jdbc:mysql://localhost:3306/addressBookService?useSSL=false";
		String userName="user1";
		String password="pass";
		Connection connection;
		System.out.println("Connecting to database"+ jdbcURL);
		connection=DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successfull"+ connection);
		return connection;
	}

	public List<Contact> readData(String name) {
		String sql = "select * from contact";
		List<Contact> contactList= new ArrayList<>();
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	private List<Contact> getcontactData(ResultSet resultSet) {
		List<Contact> contactList = new LinkedList<>();
		try {
			while(resultSet.next()) {
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				int id = resultSet.getInt("contact_id");
				contactList.add(new Contact(id,firstName,lastName)); 		
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	public int updateAddress(String firstName, String city, String state, int zip) {
		String sql = String.format("update address set city='%s',state='%s',zipcode=%d where contact_id ="
				+ "(select contact_id from contact where first_name='%s');", city,state,zip,firstName);
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Contact> readDataInDateRange(String name, LocalDate dateAdded) {
		String sql = "select * from contact where date_added between cast('2021-01-01' as date) and date(now())";
		List<Contact> contactList= new ArrayList<>();
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public List<Contact> readDataByCity(String city) {
		String sql = "select * from contact as c, address as a where c.contact_id=a.contact_id and city='"+city+"'";
		List<Contact> contactList= new ArrayList<>();
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

}
