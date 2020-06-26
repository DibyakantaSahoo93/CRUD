package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employee/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee emp = employeeService.findById(employeeId);
		if(emp == null) {
			throw new RuntimeException("Employee id not Found: "+employeeId);
		}
		
		return emp;
	}
	
	@PostMapping("/employee")
	public Employee insert(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping("/employee")
	public Employee update(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String delete(@PathVariable int employeeId) {
		Employee emp = employeeService.findById(employeeId);
		if(emp == null) {
			throw new RuntimeException("Employee id not Found: "+employeeId);
		}
		
		employeeService.deleteById(employeeId);
		return "Employee "+employeeId+ " deleted" ;
	}
}
