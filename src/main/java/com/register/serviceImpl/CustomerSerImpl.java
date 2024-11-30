package com.register.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.register.dto.CustomerDto;
import com.register.model.Customer;
import com.register.repository.CustomerRepository;
import com.register.service.CustomerService;


@Service
public class CustomerSerImpl implements CustomerService{

	@Autowired
	private CustomerRepository cusrepo;
	
	@Autowired
	private ModelMapper modemapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Customer createUser(CustomerDto userDetails) {
		
		//userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Customer customer = modelMapper.map(userDetails, Customer.class);
		customer.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		return cusrepo.save(customer);
		
		
		
		/*Customer customer = new Customer();
		customer.setUserName(userDetails.getUserName());
		customer.setEmail(userDetails.getEmail());
		customer.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		customer.setPhoneNo(userDetails.getPhoneNo());
		customer.setAddress(userDetails.getAddress());
  

        return cusrepo.save(customer);*/
		
		
	}




	@Override
	public List<CustomerDto> getAllUsers() {
		
		List<Customer> cus = cusrepo.findAll();
      
        return cus.stream()
        		.map( cust  -> modemapper.map(cust, CustomerDto.class))
        		.collect(Collectors.toList());
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Customer cus = cusrepo.findByEmail(username);
		
		if(cus == null) throw new UsernameNotFoundException(username);
		
		return new User(cus.getEmail(),cus.getPassword(),
				true, true, true, true, new ArrayList<>());
		
		
	}




	@Override
	public CustomerDto getUserDetailsByEmail(String email) {
		
      Customer customer = cusrepo.findByEmail(email);
		
		if(customer == null) throw new UsernameNotFoundException(email);
		
		return new ModelMapper().map(customer, CustomerDto.class);
		
		
		
	}





	
	
}
