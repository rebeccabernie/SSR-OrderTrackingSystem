package com.sales.controllers;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.services.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService custS;
	
	
// List Customers page -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/showCustomers", method = RequestMethod.GET)
	public String getCustomers(Model m) {

		// Gets all customers in product arraylist
		ArrayList<Customer> customers = custS.getAll();

		// Add to customer model object
		m.addAttribute("customers", customers);

		return "displayCustomers";
	}
	
	
// Add Customer page ---------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("customer1") Customer c, HttpServletRequest h) {
		return "addCustomer";
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("customer1") Customer c, BindingResult result,
			HttpServletRequest h, Model m) {

		if (result.hasErrors()) {

			return "addCustomer";

		} else {
			// Save customer to database
			custS.save(c);
			
			// Get customers from database, save into arraylist
			ArrayList<Customer> customers = custS.getAll();

			// Add to customer model object
			m.addAttribute("customers", customers);

			return "displayCustomers";
		}
	}
	
}
