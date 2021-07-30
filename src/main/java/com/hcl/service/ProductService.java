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
	
	public void saveProduct(Product p) {
		productRepo.save(p);
	}
	
	public Product getProductById(long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	public Product updateProduct(long id, Product p) {
		Product p1 = getProductById(id);
		p1.setPrdName(p.getPrdName());
		p1.setPrdPrice(p.getPrdPrice());
		p1.setPrdQuantity(p.getPrdQuantity());
		productRepo.save(p1);
		
		return p1;
	}
	
	public void deleteProductById(long id) {
		productRepo.deleteById(id);
	}
	
	
}
