package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/Customer")
public class CustomerController {

	// need to inject the customer Service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get the customer from the customer service dao implementation
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customer to model
		
		theModel.addAttribute("customers",theCustomers);
		
		
		return "list-customers";
	}
	
	//will direct to a new page to input new customer data
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//creating a model attribute to bind form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer using customer service
		customerService.saveCustomer(theCustomer);
		return "redirect:/Customer/list";
	}
		
	//will direct to a new page to update current customer data
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,  Model theModel) {
		
		// get the customer from service layer
		Customer theCustomer = customerService.getCustomer(theId);
		
		
		//set customer as a model atribute to pre populate the form
		theModel.addAttribute("customer",theCustomer);
		
		//send over to our form
		
		return "customer-form";
	}	
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId,  Model theModel) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/Customer/list";
	}	
	
}
