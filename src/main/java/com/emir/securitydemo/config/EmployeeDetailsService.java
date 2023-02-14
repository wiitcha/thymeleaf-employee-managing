package com.emir.securitydemo.config;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.emir.securitydemo.model.Employee;
import com.emir.securitydemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService{

	private EmployeeRepository empRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employee = empRep.findByUsername(username);
		
		return employee.map(EmployeeUserDetails::new)
			.orElseThrow(() -> new UsernameNotFoundException("User whose username is " + username + " not found."));
	}

}
