package com.register.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.register.service.CustomerService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired
	private CustomerService cusService;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private Environment env;
	
	
	
	
	  @Bean
	    public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	
	  /*@Bean
	    public static PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }*/
	  
	   
	    @Bean
	    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    	
	    	
	    	// Configure AuthenticationManagerBuilder
	    	AuthenticationManagerBuilder authenticationManagerBuilder = 
	    			http.getSharedObject(AuthenticationManagerBuilder.class);
	    	
	    	authenticationManagerBuilder.userDetailsService(cusService)
	    	.passwordEncoder(bCryptPasswordEncoder);
	    	
	    	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
	    	
	    	
	    	
	    	http.csrf((csrf) -> csrf.disable());
             http.authorizeHttpRequests((authorize) ->
                      //authorize.anyRequest().authenticated()
                      authorize.requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                              .requestMatchers("/api/v1/**").permitAll()
                              .anyRequest().authenticated()
                              .and()
                              .addFilter(new AuthenticationFilter(cusService,env,authenticationManager))
                              .authenticationManager(authenticationManager)
                 );

                  //	http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
           
                   return http.build();

	    }
	  
}
