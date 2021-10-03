package com.bridgelabz.addressbooksystem;

import com.bridgelabz.addressbooksystem.AddressBookDBService.BookType;

public class AddressBook {
	private int book_id;
	private String book_name;
	private BookType type;
	
	public AddressBook(int id, String name, BookType type) {
		this.book_id = id;
		this.book_name = name;
		this.type = type;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}
	
	
}
