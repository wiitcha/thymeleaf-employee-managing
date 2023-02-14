package com.emir.securitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emir.securitydemo.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);
}
