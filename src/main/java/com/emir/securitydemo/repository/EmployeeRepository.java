package com.emir.securitydemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emir.securitydemo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findByUsername(String username);
	Page<Employee> findAll(Pageable page);

	@Query(value = "Select e from Employee e where e.name like %:search% or e.surname like %:search% or e.username like %:search%")
	Page<Employee> findFilteredEmployees(@Param("search") String search, Pageable page);

}
