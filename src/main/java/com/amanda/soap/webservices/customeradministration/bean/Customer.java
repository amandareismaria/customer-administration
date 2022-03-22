package com.amanda.soap.webservices.customeradministration.bean;

public class Customer {
	
	private Integer id;
	private String name;
	private String phone;
	private String email;
	public Integer getId() {
		return id;
	
	
	}
	public Customer(Integer id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("Customer [id=%s, name=%s, phone=%s, email=%s]", id, name, phone, email);
	}
	
	
}
