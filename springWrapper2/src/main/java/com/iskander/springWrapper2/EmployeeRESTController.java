package com.iskander.springWrapper2;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/iskander/api/v1")
public class EmployeeRESTController {
	@Autowired
	private EmployeeRepository employeeRepository;
	// get all employees
	 @GetMapping("/employees")
	 public List<Employee> getAllEmployees(Model model) {
	  	
	 return this.employeeRepository.findAll();
	}
	// get employee by id
	 @GetMapping("/employee/{id}")
	 public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	   throws ResourceNotFoundException {
	   Employee employee = employeeRepository.findById(employeeId)
	    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	   return ResponseEntity.ok().body(employee);
	 }
	// save employee
	 @PostMapping("/employee")
	 public Employee createEmployee(@Valid @RequestBody Employee employee) {
		 return employeeRepository.save(employee);
	 }
}