package com.nec.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nec.customerapi.models.Address;
import com.nec.customerapi.models.Customer;
import com.nec.customerapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
    
    //insert
    
    public Customer addCustomer(Customer customer) {
    	
    	customer.getAddressList().stream().forEach(address->{
    		address.setCustomer(customer);
    	});
    	
    	return customerRepository.save(customer);
    }
    
    //update
    
    //delete
    
    public boolean deleteCustomerById(long customerId) {
    	
    	boolean status=false;
    	this.customerRepository.deleteById(customerId);
    	if(this.getCustomerById(customerId)==null)
    		status=true;
    	return status;
    }
    
    //select
    
    public List<Customer> getCustomers(){
    	return this.customerRepository.findAll();
    }
    
    
    public Customer getCustomerById(long customerId) {
    	return this.customerRepository.findById(customerId).orElse(null);
    }
    
}
