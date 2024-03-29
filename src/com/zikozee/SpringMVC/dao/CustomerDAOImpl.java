package com.zikozee.SpringMVC.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zikozee.SpringMVC.entity.Customer;

@Repository  //component scan >> find this repository >> handle the exception translation for us (note only for DAO)
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired //Spring check the .xml to look for a bean with id == properties name i.e sessionFactory
	private SessionFactory sessionFactory;
	
	
	@Override //@Transactional  //Automagically begin and end/commit transaction for your hibernate code but better done in the service layer hence @Transactional commented out
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
									Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer){

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//currentSession.save(theCustomer);//inserts new Record
		//currentSession.save(theCustomer);//Updates new Record
		
		// save/update the customer .. finally LOL
		currentSession.saveOrUpdate(theCustomer);//checks if id exists: if yes it updates, else, it inserts
	}

	@Override
	public Customer getCustomer(int theId){

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key i.e theId
		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;
		
		//only search by name if theSearchname is not empty
		if(theSearchName != null && theSearchName.trim().length()>0) {
			
			//search for the firstName or lastName .. case insensitive
			theQuery = currentSession.createQuery("from Customer where lower(firstName) "
					+ "like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%"+ theSearchName.toLowerCase() +"%");		
		}else {
			
			//the SearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}
}
