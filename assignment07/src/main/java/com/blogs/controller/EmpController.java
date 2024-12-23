package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.ApiResponse;
import com.blogs.entity.Employee;
import com.blogs.service.EmpService;




@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpController {
	@Autowired
	EmpService empService;
	
	
	@GetMapping("/")
	public ResponseEntity<?> getEmployee() {
		System.out.println("get all");
	
	List<Employee> emp	=empService.getAllEmp();
		if (emp.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(emp);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<?> addNewEmployee(@RequestBody 
			Employee employee) {
		System.out.println("in add employee " + employee);//transient category
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(empService.addNewEmployee(employee));
	}
	
	
	
	@DeleteMapping("/{empId}")
	public ResponseEntity<?> deleteCategoryDetails(@PathVariable Long empId)
	{
		System.out.println("in delete "+empId);
		try {
		
			return ResponseEntity.ok(
			empService.deleteEmployee(empId));
		} catch (RuntimeException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	@PutMapping("/{empId}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long empId, @RequestBody Employee emp) {
		System.out.println("in update employee " + empId + " " + emp);

		
		return ResponseEntity.ok(
				empService.updateEmployee(empId, emp));

	}
	
	
	
	
	
	
	
	
	
	 
		@GetMapping("/{empId}")
		public ResponseEntity<?> getempDetails(@PathVariable Long empId) {
			System.out.println("in get dtls " + empId);
			try {
				
				Employee emp =empService.getempDetails(empId);
				return ResponseEntity.ok(emp);
			} catch (RuntimeException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
		}
	
	
	
	
	

}
