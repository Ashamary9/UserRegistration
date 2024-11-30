package com.register.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.dto.CustomerDto;
import com.register.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	
	@Autowired
	private CustomerService cusServ;
	
	

    @Autowired
    private AuthenticationManager authenticationManager;

   
	
	
	    @GetMapping("/getAllCustomers")
        public List<CustomerDto> getAllUsers() {
		
		return cusServ.getAllUsers();
	    }
	
	 
	 @PostMapping("/register")
	    public ResponseEntity<String> createUser(@RequestBody CustomerDto userDetails) {
	        try {
	        	cusServ.createUser(userDetails);
	            return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error registering customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	   @PostMapping("/signin")
	    public ResponseEntity<String> authenticateUser(@RequestBody CustomerDto userDetails){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                userDetails.getEmail(), userDetails.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
	    }
	 
	
}
