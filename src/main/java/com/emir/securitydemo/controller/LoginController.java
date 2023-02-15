package com.emir.securitydemo.controller;


import com.emir.securitydemo.dto.EmployeeDTO;
import com.emir.securitydemo.model.Employee;
import com.emir.securitydemo.repository.EmployeeRepository;
import com.emir.securitydemo.repository.RoleRepository;
import com.emir.securitydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;


@Controller
@RequestMapping("/login")
public class LoginController {

	EmployeeRepository empRep;
	EmployeeService empService;
	RoleRepository roleRep;

	@Autowired
	public LoginController(EmployeeRepository empRep, EmployeeService empService, RoleRepository roleRep) {
		this.empRep = empRep;
		this.empService = empService;
		this.roleRep = roleRep;
	}

	@GetMapping
	public String welcome() {
		return "login/login-page";
	}

	@GetMapping("/register")
	public String register() {
		return "login/registerForm";
	}

	@PostMapping ("/registerCheck")
	public String registerCheck(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
		if(empRep.findByUsername(employeeDTO.getUsername()).isEmpty()) {
			Employee emp = new Employee(employeeDTO.getUsername(), employeeDTO.getPassword(), employeeDTO.getName(), employeeDTO.getSurname());
			emp.setRoles(roleRep.findByName("ROLE_USER").stream().collect(Collectors.toList()));
			empService.addEmployee(emp);
		return "redirect:/login?registered=true";
		} else return "redirect:/login/register?error=true";
	}

}
