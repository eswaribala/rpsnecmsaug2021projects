package com.nec.customerapi.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.nec.customerapi.models.Customer;
import com.nec.customerapi.services.CustomerService;

@Component
public class CustomerQueryResolver implements GraphQLQueryResolver{
	   @Autowired
		private CustomerService customerService;
	    
	    
	    public List<Customer> findAllCustomers(){
	    	return this.customerService.getCustomers();
	    }
	    
	    public Customer findCustomer(long customerId) {
	    	return this.customerService.getCustomerById(customerId);
	    }
	
	
}
