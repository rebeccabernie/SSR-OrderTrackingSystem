package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.repositories.CustomerInterface;

@Service
public class CustomerService {

	@Autowired
	private CustomerInterface custInt;

	public ArrayList<Customer> getAll() {

		// Gets all customers from the CRUDRepo for customer -> CustomerInterface
		return (ArrayList<Customer>) custInt.findAll();
	}

	public Customer save(Customer customer) {

		// Passes customer to be saved to the CRUDRepo for customer
		return custInt.save(customer);
	}

	
}