package com.bridgelabz.addressbooksystem;

public class Phone {
	private long phoneNumber;
	
	public Phone(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public long getNumber() {
		return phoneNumber;
	}
	
	public void setNumber(long number) {
		this.phoneNumber = number;
	}
}
