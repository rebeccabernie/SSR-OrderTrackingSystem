package com.sales.controllers;

// Imports
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Product;
import com.sales.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService prodS;
	private org.springframework.security.core.Authentication auth;
	
// List Products page ---------------------------------------------------------------------------------------
	
	// Uses get request, no save functionality needed
	@RequestMapping(value = "/showProducts", method = RequestMethod.GET)
	public String showProduct(Model m) {

		// Get all products from product service, same them to array
		ArrayList<Product> products = prodS.getAll();

		// Add to product model object
		m.addAttribute("products", products);

		// Display showProducts page
		return "showProducts";
	}
	

// Add Products page ---------------------------------------------------------------------------------------------

	// Shows addProduct page using get request
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("product1") Product prod, HttpServletRequest http) {
		return "addProduct";
	}

	// Saves addProduct page info into database using post
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("product1") Product prod, BindingResult result, HttpServletRequest http,
			Model m) {

		if (result.hasErrors()) {
			
			// Refresh the Add Product page - won't add the invalid details
			return "addProduct";
		
		} else {
			// Pass the product to the Product Service for saving
			prodS.save(prod);

			// New product arraylist - get all products from product service, including new one
			ArrayList<Product> products = prodS.getAll();
			
			// Add to product model object
			m.addAttribute("products", products);
			
			// Display the showProducts page
			return "showProducts";
		}
	}
	
	
// Log out ------------------------------------------------------------------------------------------------------
	
	// Tried to put logout functionality in its own controller but didn't work
	// Won't work if this method is in all controllers either so keeping it here
	// Seems to work perfectly anyway
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}
