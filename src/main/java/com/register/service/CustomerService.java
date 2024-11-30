package com.register.service;



import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.register.dto.CustomerDto;
import com.register.model.Customer;

public interface CustomerService extends UserDetailsService{

	public List<CustomerDto> getAllUsers();
	
	public Customer createUser(CustomerDto userDetails);

	public CustomerDto getUserDetailsByEmail(String email);
	
}
