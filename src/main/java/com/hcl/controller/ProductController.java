package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.model.Product;
import com.hcl.service.ProductService;
import com.hcl.service.UserDetailsServiceImpl;

@Controller
public class ProductController {

	@Autowired
	private ProductService products;
	
	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	
	@GetMapping("/logoutSuccess") 
	public String logoutSuccess() {
		products.clearQuantity();
		return "logoutSuccess";
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
		if (products.saveProduct(pName, pPrice)) {
			return "redirect:/adminAllProducts";
		} else {
			m.addAttribute("error", "Please enter a vaild price!");
			return "addProduct";
		}
	}
	
	@GetMapping("/update")
	public ModelAndView updateProduct(long id) {
		//this.id = id;
		Product p = new Product();
		p = products.getProductById(id);
		ModelAndView model = new ModelAndView();
		model.setViewName("update");
		model.addObject("product", p);
		return model;
	}
	
	
	@PostMapping("/update")
	public String updateProduct(@RequestParam long pId, @RequestParam String pName, @RequestParam String pPrice, ModelMap m) {
		
		if (products.updateProduct(pId, pName, pPrice, 0)) {
			return "redirect:/adminAllProducts";
		} else {
			Product p = new Product();
			p = products.getProductById(pId);
			m.addAttribute("error", "Please enter a vaild price!");
			m.addAttribute("product", p);
			return "update";
		}
		
	}
	
	@GetMapping("/userUpdate")
	public ModelAndView updateProductQuantity(long id) {
		Product p = new Product();
		p = products.getProductById(id);
		ModelAndView model = new ModelAndView();
		model.setViewName("userUpdate");
		model.addObject("product", p);
		return model;
	}
	
	@PostMapping("/userUpdate")
	public String updateProductQuantity(@RequestParam long pId, @RequestParam int pQuantity, ModelMap m) {
		
		Product p = products.getProductById(pId);
		String pToString = "";
		if (products.updateProduct(pId, p.getPrdName(), pToString + p.getPrdPrice(), pQuantity)) {
			return "redirect:/userAllProducts";
		} else {
			m.addAttribute("error", "quantity must be greater than 0");
			m.addAttribute("product", p);
		}
		
		return "userUpdate";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam long id) {
		products.deleteProductById(id);
		return "redirect:/adminAllProducts";
	}
	
	@GetMapping("/userDelete")
	public String userDelete(@RequestParam long id) {
		Product p = products.getProductById(id);
		String dToString = "";
		products.updateProduct(id, p.getPrdName(), dToString + p.getPrdPrice(), 0);
		return "redirect:/userAllProducts";
	}
	
	
	@GetMapping("/createNewUser")
	public String createUser() {
		return "createNewUser";
	}
	
	@PostMapping("/createNewUser")
	public String createNewUser(@RequestParam String name, @RequestParam String password) {
		userDetails.createNewUser(name, password);
		
		return "redirect:/userAllProducts";
	}
	
	@GetMapping("/403")
	public String errorPage() {
		return "/403";
	}
	
	
}
