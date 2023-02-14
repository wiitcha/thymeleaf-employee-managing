package com.emir.securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emir.securitydemo.model.Employee;
import com.emir.securitydemo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository empRep;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRep, PasswordEncoder passwordEncoder) {
		this.empRep = empRep;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String addEmployee(Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		empRep.save(emp);
		return "Employee eklendi sayfasina ->";
	}

	@Override
	public List<Employee> findAllEmployee() {
		return empRep.findAll();
	}

	@Override
	public Optional<Employee> findByEmployeeId(Long id) {
		return empRep.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		empRep.deleteById(id);
	}

	/*public Page<Employee> findPaginated(Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();

		int numberOfEmployees = empRep.findAll().size();
		int numberOfPages =  numberOfEmployees / pageSize;

		List<Integer> pages = new ArrayList<>();

		for (int i = 1; i <= numberOfPages; i++) pages.add(i);

		int numberOfEmployees = empRep.findAll().size();
		int numberOfPages =  numberOfEmployees / pageSize;



	}*/




}
