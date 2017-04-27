package com.sales.controllers;

// Imports
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

import com.sales.models.Product;
import com.sales.services.ProductService;

// Controller
@Controller
public class ProductController {
	@Autowired
	private ProductService prodS;

	// Shows addProduct page using get request
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("product1") Product prod, HttpServletRequest http) {
		System.out.println("HTTP Request = " + http.getMethod());
		return "addProduct";
	}

	// Saves addProduct page info into database using post
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("product1") Product prod, BindingResult result, HttpServletRequest http,
			Model m) {

		if (result.hasErrors()) {
			
			return "addProduct";
		
		} else {

			System.out.println("HTTP Request = " + http.getMethod());

			// Save Product to database
			prodS.save(prod);

			// Get products from database, save into arraylist
			ArrayList<Product> products = prodS.getAll();
			
			// Add to product model object
			m.addAttribute("products", products);
			
			return "displayProduct";
		}
	}

	// Shows List of Products page, uses get request, no save functionality needed
	@RequestMapping(value = "/showProducts", method = RequestMethod.GET)
	public String showProduct(Model m) {

		// Gets all products in product arraylist
		ArrayList<Product> products = prodS.getAll();

		// Add to product model object
		m.addAttribute("products", products);

		return "displayProduct";
	}

}