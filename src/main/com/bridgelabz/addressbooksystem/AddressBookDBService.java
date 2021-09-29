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
	public static enum BookType {
		FRIEND,
		FAMILY,
		PROFESSION
	}
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

	public void addContactToDB(String firstName, String lastName, String city, String state, int zip,
			long phoneNumber, String bookName, BookType type) {
		Connection connection = null;
		int contactId = -1;
		int addressBookId = -1;
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Insert into contact
		try (Statement statement = connection.createStatement()){
			String sql = String.format("insert into contact (first_name,last_name,date_added) values ('%s','%s',date(now()))",firstName,lastName);
			int rowsAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(rowsAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) contactId = resultSet.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//insert into address
		try (Statement statement = connection.createStatement()){
			String sql = String.format("insert into address (contact_id,city,state,zipcode) values (%d,'%s','%s',%d)",contactId,city,state,zip);
			statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//insert into address_book
		try (Statement statement = connection.createStatement()){
			String sql = String.format("insert into address_book (book_name,book_type) values ('%s','%s')",bookName,getBookType(type));
			int rowsAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(rowsAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) addressBookId = resultSet.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//insert into contact_book
		try (Statement statement = connection.createStatement()){
			String sql = String.format("insert into contact_book values (%d,%d)",contactId,addressBookId);
			int rowsAffected = statement.executeUpdate(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//Insert into phone_number
		try (Statement statement = connection.createStatement()){
			String sql = String.format("insert into phone_number values (%d,%d)",contactId,phoneNumber);
			int rowsAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
			if(rowsAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) contactId = resultSet.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//Commit changes to DB
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String getBookType(BookType type) {
		if(type == BookType.FRIEND)
			return "Friend";
		else if(type == BookType.FAMILY)
			return "Family";
		else
			return "Profession";
	}

}
