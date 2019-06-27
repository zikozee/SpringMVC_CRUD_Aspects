package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
//	// need to inject the customer dao
//	@Autowired // Spring will scan for a component that implements CustomerDAO interface and inject it here
//	private CustomerDAO customerDAO;
	
	//the above commented out because we now make use of the service layer i.e CustomerService
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list") // since this handles only get request hence t'will reject all other e.g post rememba was @RequestingMapping b4
	public String listCustomers(Model theModel) {
		
					//get customers from the dao
		//List<Customer> theCustomers = customerDAO.getCustomers();
		
		//get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers); //a list of customer and pass to the html page
		
		return "list-customers"; //customers model attribute reference from list-customers
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Customer theCustomer = new Customer();//empty
		
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
		
		//Add customer hrefs :"window.location.href='showFormForAdd' i.e here
		// and a new new customer is created whose values are set in the customer-form with attribute = "customer"
		//And from the  form action="saveCustomer" implemented below and calls customerService Class
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {//receives a customer   for multiple customer u can import a file and loop over
		
		//save the customer using service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		
		//relates to the DAO class which directly talks to the database
		//which updates if key is found  or insert if not found
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {// request param in the list-customer.jsp
		
		//get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);//customer with record
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
		
		// note same model attribute in customer-form. 
		//hence the update click on list-customer.jsp page gets that customer id and passes 
		//it to the the showFormForUpdate and id used by customerService to relate 
		//with customerDAO which in turn talks to the database to retrieve customer 
		//and the customer info is used to populate the customer-form whose attribute is presented i.e "customer"
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		//search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";//customers model attribute reference from list-customers
	}
}
