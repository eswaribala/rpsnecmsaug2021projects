package com.nec.customerapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.nec.customerapi.models.Customer;
import com.nec.customerapi.repositories.CustomerRepository;
import com.nec.customerapi.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired    
	private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    
    @PostMapping({"/v1.0", "/v1.1"})

    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
    	
    	Customer obj=this.customerService.addCustomer(customer);
    	if(obj!=null)
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(obj);
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Added");
    	
    }
    
    @GetMapping({"/v1.0", "/v1.1"})
    public List<Customer> getCustomers(){
    	return this.customerService.getCustomers();
    }
    
    @GetMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})

    public Customer getCustomerById(@PathVariable("customerId") long customerId) {
    	return this.customerService.getCustomerById(customerId);
    }
    
    @DeleteMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})

    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId) {
    	if(this.customerService.deleteCustomerById(customerId))
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("customer"
    	+customerId+"deleted");
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("customer"
    		    	+customerId+"not deleted");
    }
    
  //http://localhost:7070/customers/v1.0/filters/1?fields=customerId,email,contactNo
  	@GetMapping({"/v1.0/filters", "/v1.1/filters"})
      public String getFilteredCustomer(@RequestParam(name = "filter", required = false) 
      String filter) 
  	{

  		List<Customer> customerList = getCustomers();
  		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), filter);  
  		return SquigglyUtils.stringify(mapper, customerList);
  		
      }
  	
  	
  	@GetMapping({"/v1.0/pages","/v1.1/pages"})
    public ResponseEntity<Map<String, Object>> getAllCustomerPage(
        @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam(defaultValue = "customerId,desc") String[] sort) {

      try {
        List<Order> orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {
          // will sort more than 2 fields
          // sortOrder="field, direction"
          for (String sortOrder : sort) {
            String[] _sort = sortOrder.split(",");
            orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
          }
        } else {
          // sort=[field, direction]
          orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }

        List<Customer> customers = new ArrayList<Customer>();
        PageRequest pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<Customer> pageCustomers;
        if (email == null)
          pageCustomers = customerRepository.findAll(pagingSort);
        else
          pageCustomers =customerRepository.findByEmailContaining(email, pagingSort);

        customers = pageCustomers.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("customers", customers);
        response.put("currentPage", pageCustomers.getNumber());
        response.put("totalItems", pageCustomers.getTotalElements());
        response.put("totalPages", pageCustomers.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  	
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
          return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
          return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
      }

  	
  	   

}
