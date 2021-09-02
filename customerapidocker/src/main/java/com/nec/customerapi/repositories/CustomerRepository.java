package com.nec.customerapi.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nec.customerapi.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	  Page<Customer> findByEmailContaining(String email, PageRequest pagingSort);
	  
	  List<Customer> findByEmailContaining(String email, Sort sort);
	}
