package com.blogs.service;

import java.util.List;

import com.blogs.dto.ApiResponse;
import com.blogs.entity.Employee;

public interface EmpService {
	
	List<Employee> getAllEmp();
   String addNewEmployee(Employee employee);
   ApiResponse deleteEmployee(Long empId);
   
    ApiResponse updateEmployee(Long empId,Employee employee);
    Employee getempDetails(Long catId);
    
  
	
}
