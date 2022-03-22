package com.amanda.soap.webservices.customeradministration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amanda.soap.webservices.customeradministration.bean.Customer;

@Component
public class CustomerDetailService {

	private static List<Customer> customers = new ArrayList<>();

	static {
		Customer customer1 = new Customer(1, "Bob", "999999999", "bob@gmail.com");
		customers.add(customer1);

		Customer customer2 = new Customer(1, "Mark", "888888888", "mark@gmail.com");
		customers.add(customer2);

		Customer customer3 = new Customer(1, "Jose", "777777777", "jose@gmail.com");
		customers.add(customer3);

		Customer customer4 = new Customer(1, "Klay", "666666666", "klay@gmail.com");
		customers.add(customer4);
	}

	
}
