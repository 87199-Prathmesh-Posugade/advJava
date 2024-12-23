package com.blogs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dao.EmpDao;
import com.blogs.dto.ApiResponse;
import com.blogs.entity.Employee;
@Service
public class EmpServiceImpl implements EmpService {
	
	
	@Autowired
	private EmpDao empDao;

	@Override
	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return empDao.findAll();
	}

	

	
	

	@Override
	public String addNewEmployee(Employee employee) {
		Employee e=empDao.save(employee);
		return "Added new employee with ID="+e.getId();
	}






	@Override
	public ApiResponse deleteEmployee(Long empId) {
		if (empDao.existsById(empId)) {
			empDao.deleteById(empId);
			return new ApiResponse("Deleted employee Details");
		}
		return null;
		
		
	}






	@Override
	public ApiResponse updateEmployee(Long empId, Employee employee) {
		if(empDao.existsById(empId)) {
			employee.setId(empId);
			empDao.save(employee);
			return new ApiResponse("emp updated");
			
		}
		return new ApiResponse("invalid");
	}






	@Override
	public Employee getempDetails(Long catId) {
		
		return empDao.findById(catId).orElseThrow();
	}
	
	
	

	

	

	

}
