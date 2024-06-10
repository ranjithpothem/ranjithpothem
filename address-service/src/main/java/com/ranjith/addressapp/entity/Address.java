package com.ranjith.addressapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	//
	//id, lane1, lan2, state, , zip, employee_id
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "lane1")
	private String lan1;
	
	@Column(name = "lan2")
	private String lan2;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zip")
	private long zip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLan1() {
		return lan1;
	}

	public void setLan1(String lan1) {
		this.lan1 = lan1;
	}

	public String getLan2() {
		return lan2;
	}

	public void setLan2(String lan2) {
		this.lan2 = lan2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

}
