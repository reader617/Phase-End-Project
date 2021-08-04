package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.model.Product;
import com.hcl.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		List<Product> productRecord = new ArrayList<>();
		productRepo.findAll().forEach(productRecord::add);
		
		return productRecord;
	}
	
	public boolean saveProduct(String name, String price) {
		Product p1 = new Product();
		try {
			double newPrice = Double.parseDouble(price);
			
			if (newPrice < 0) {
				throw new NumberFormatException("price cannot be less than 0");
			}
			
			p1.setPrdName(name);
			p1.setPrdPrice(newPrice);
			p1.setPrdQuantity(0);
			productRepo.save(p1);
			
		} catch (NumberFormatException c) {
			return false;
		}
		
		return true;
		
	}
	
	public Product getProductById(long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	public boolean updateProduct(long id, String name, String price, int quantity) {
		Product p1 = getProductById(id);
		
		try {
			double newPrice = Double.parseDouble(price);
			
			if (newPrice < 0) {
				throw new NumberFormatException("price cannot be less than 0");
			}
			
			if (quantity <= 0) {
				throw new NumberFormatException("quantity cannot be less than 0");
			}
			
			p1.setPrdName(name);
			p1.setPrdPrice(newPrice);
			p1.setPrdQuantity(quantity);
			productRepo.save(p1);
			
		} catch (NumberFormatException c) {
			return false;
		}
		
		return true;
	}
	
	public void deleteProductById(long id) {
		productRepo.deleteById(id);
	}
	
	public void clearQuantity() {
		List<Product> pro = getAllProducts();
		for (Product p: pro) {
			p.setPrdQuantity(0);
			productRepo.save(p);
		}
		
	}
	
	
}
