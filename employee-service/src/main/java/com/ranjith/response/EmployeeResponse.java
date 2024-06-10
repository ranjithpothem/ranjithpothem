package com.ranjith.response;

public class EmployeeResponse {

	private int id;

	private String name;

	private String email;

	private String boold;

	public int getId() {
		return id;
	}

	private AddressRespone addressResponse;
	
	public AddressRespone getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressRespone addressResponse) {
		this.addressResponse = addressResponse;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBoold() {
		return boold;
	}

	public void setBoold(String boold) {
		this.boold = boold;
	}

}
