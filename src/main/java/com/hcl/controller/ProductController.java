package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.model.Product;
import com.hcl.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService products;
	
	private long id;
	
	/*@GetMapping("/") 
	public String login() {
		return "redirect:/login";
	}*/
	
	@GetMapping("/userAllProducts")
	public ModelAndView getProducts(ModelAndView model) {
		List<Product> p = products.getAllProducts();
		model.setViewName("userAllProducts");
		model.addObject("pList", p);
		return model;
		
	}
	
	@PostMapping("/userAllProducts")
	public String postProducts(ModelAndView model) {
		List<Product> p = products.getAllProducts();
		model.setViewName("userAllProducts");
		model.addObject("pList", p);
		return "userAllProducts";
	}
	
	@GetMapping("/adminAllProducts")
	public ModelAndView getProductsAdmin(ModelAndView model) {
		List<Product> p = products.getAllProducts();
		model.setViewName("adminAllProducts");
		model.addObject("pList", p);
		return model;
		
	}
	
	@PostMapping("/adminAllProducts")
	public String postProductsAdmin(ModelAndView model) {
		List<Product> p = products.getAllProducts();
		model.setViewName("adminAllProducts");
		model.addObject("pList", p);
		return "adminAllProducts";
	}
	
	@GetMapping("/addProduct")
	public String add() {
		return "addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam String pName, @RequestParam double pPrice) {
		Product p = new Product();
		p.setPrdName(pName);
		p.setPrdPrice(pPrice);
		p.setPrdQuantity(0);
		products.saveProduct(p);
		
		
		return "redirect:/adminAllProducts";
	}
	
	@GetMapping("/update")
	public String updateProduct(long id) {
		this.id = id;
		return "/update";
	}
	
	
	@PostMapping("/update")
	public String updateProduct(@RequestParam String pName, @RequestParam double pPrice) {
		
		Product p = new Product();
		p.setPrdName(pName);
		p.setPrdPrice(pPrice);
		p.setPrdQuantity(0);
		
		products.updateProduct(id, p);
		return "redirect:/adminAllProducts";
	}
	
	@GetMapping("/userUpdate")
	public String updateProductQuantity(long id) {
		this.id = id;
		return "/userUpdate";
	}
	
	@PostMapping("/userUpdate")
	public String updateProductQuantity(@RequestParam int pQuantity) {
		
		Product p = new Product();
		Product p1 = products.getProductById(id);
		p.setPrdName(p1.getPrdName());
		p.setPrdPrice(p1.getPrdPrice());
		p.setPrdQuantity(pQuantity);
		
		products.updateProduct(id, p);
		return "redirect:/userAllProducts";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam long id) {
		products.deleteProductById(id);
		return "redirect:/adminAllProducts";
	}
	
	@GetMapping("/userDelete")
	public String userDelete(@RequestParam long id) {
		Product p = products.getProductById(id);
		p.setPrdQuantity(0);
		products.updateProduct(id, p);
		return "redirect:/userAllProducts";
	}
	
	
}
