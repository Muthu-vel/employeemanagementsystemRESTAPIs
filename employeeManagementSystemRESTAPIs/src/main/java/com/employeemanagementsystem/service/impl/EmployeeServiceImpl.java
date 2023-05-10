package com.employeemanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employeemanagementsystem.exception.ResoruceNotFoundException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import com.employeemanagementsystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new ResoruceNotFoundException("Employee", "Id", id);
		}

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		// we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResoruceNotFoundException("Employee", "Id", id));

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {

		// check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() -> new ResoruceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}

}
