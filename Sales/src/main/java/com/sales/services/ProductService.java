package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sales.models.Product;
import com.sales.repositories.ProductInterface;


@Service
public class ProductService {
	
	@Autowired
	private ProductInterface prodInt;
	
	public ArrayList<Product> getAll() {

		// Gets all products from the CRUDRepository for products -> ProductInterface
		return (ArrayList<Product>) prodInt.findAll();
	}
	
	public Product save(Product prod) {
		
		// Passes product to be saved to the CRUDRepo for products
		return prodInt.save(prod);
	}
	
}
