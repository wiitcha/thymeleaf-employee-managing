package com.emir.securitydemo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "employee_role",
			joinColumns = @JoinColumn(name="id_employee"),
			inverseJoinColumns = @JoinColumn(name="id_role")
			)
	private List<Role> roles;
	
	public Employee() {
		
	}

	public Employee(String username, String password, String name, String surname, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.roles = roles;
	}

	public Employee(String username, String password, String name, String surname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
}
