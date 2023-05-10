package com.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagementsystem.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
