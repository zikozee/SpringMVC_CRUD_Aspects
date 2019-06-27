package com.zikozee.SpringMVC.service;

import com.zikozee.SpringMVC.entity.Customer;

import java.util.List;

public interface CustomerService {
	
	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int theId);

	void deleteCustomer(int theId);

	List<Customer> searchCustomers(String theSearchName);
}
