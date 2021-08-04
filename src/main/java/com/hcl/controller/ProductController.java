package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.model.Product;
import com.hcl.model.User;
import com.hcl.service.ProductService;
import com.hcl.service.UserDetailsServiceImpl;

@Controller
public class ProductController {

	@Autowired
	private ProductService products;
	
	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	private long id;
	
	@RequestMapping("/logoutSuccess") 
	public String logoutSuccess() {
		List<Product> pro = products.getAllProducts();
		for (Product p: pro) {
			p.setPrdQuantity(0);
			products.updateProduct(p.getPrdId(), p);
		}
		
		return "/logoutSuccess";
	}
	
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
	public String addProduct(@RequestParam String pName, @RequestParam String pPrice, ModelMap m) {
		Product p = new Product();
		
		try {
			double price = Double.parseDouble(pPrice);
			
			if (price < 0) {
				throw new NumberFormatException("price cannot be less than 0");
			}
			
			p.setPrdName(pName);
			p.setPrdPrice(price);
			p.setPrdQuantity(0);
			products.saveProduct(p);
			return "redirect:/adminAllProducts";
		} catch (NumberFormatException c) {
			m.addAttribute("error", "Please enter a vaild price!");
		}
		
		return "addProduct";
	}
	
	@GetMapping("/update")
	public String updateProduct(long id) {
		this.id = id;
		return "/update";
	}
	
	
	@PostMapping("/update")
	public String updateProduct(@RequestParam String pName, @RequestParam String pPrice, ModelMap m) {
		Product p = new Product();
		
		try {
			double price = Double.parseDouble(pPrice);
			
			if (price < 0) {
				throw new NumberFormatException("price cannot be less than 0");
			}
			
			p.setPrdName(pName);
			p.setPrdPrice(price);
			p.setPrdQuantity(0);
			products.updateProduct(id, p);
			return "redirect:/adminAllProducts";
		} catch (NumberFormatException c) {
			m.addAttribute("error", "Please enter a vaild price!");
		}
		
		return "update";
	}
	
	@GetMapping("/userUpdate")
	public String updateProductQuantity(long id) {
		this.id = id;
		return "/userUpdate";
	}
	
	@PostMapping("/userUpdate")
	public String updateProductQuantity(@RequestParam int pQuantity, ModelMap m) {
	
		Product p = new Product();
		try {
			if (pQuantity <= 0) {
				throw new NumberFormatException("quantity cannot be less than 0");
			}
			Product p1 = products.getProductById(id);
			p.setPrdName(p1.getPrdName());
			p.setPrdPrice(p1.getPrdPrice());
			p.setPrdQuantity(pQuantity);
			
			products.updateProduct(id, p);
			return "redirect:/userAllProducts";
		} catch (NumberFormatException e) {
			m.addAttribute("error", "quantity must be greater than 0");
		}
		
		return "/userUpdate";
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
	
	
	@GetMapping("/createNewUser")
	public String createUser() {
		return "createNewUser";
	}
	
	@PostMapping("/createNewUser")
	public String createNewUser(@RequestParam String name, @RequestParam String password) {
		User u = new User();
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		u.setUsername(name);
		String encryptP = b.encode(password);
		u.setPassword(encryptP);
		u.setRole("ROLE_USER");
		u.setEnabled(true);
		userDetails.createNewUser(u);
		
		return "redirect:/userAllProducts";
	}
	
	@GetMapping("/403")
	public String errorPage() {
		return "/403";
	}
	
	
}
