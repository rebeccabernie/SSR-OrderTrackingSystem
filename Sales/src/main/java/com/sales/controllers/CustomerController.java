package com.sales.controllers;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.services.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService custS;
	
	
// List Customers page -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/showCustomers", method = RequestMethod.GET)
	public String getCustomers(Model m) {

		// Get all customers from customer service, same them to array
		ArrayList<Customer> customers = custS.getAll();

		// Add to customer model object
		m.addAttribute("customers", customers);

		return "showCustomers";
	}
	
	
// Add Customer page ---------------------------------------------------------------------------------------
	
	// Get the page
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("customer1") Customer c, HttpServletRequest h) {
		return "addCustomer";
	}

	// Save the info
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("customer1") Customer c, BindingResult result,
			HttpServletRequest h, Model m) {

		if (result.hasErrors()) {
			
			// Refresh the Add Customer page - won't add the invalid details
			return "addCustomer";

		} else {
			// Pass the customer to the Customer Service for saving
			custS.save(c);
			
			// New customer arraylist - get all customers from customer service, including new one
			ArrayList<Customer> customers = custS.getAll();

			// Add to customer model object
			m.addAttribute("customers", customers);

			return "showCustomers";
		}
	}
	
}
