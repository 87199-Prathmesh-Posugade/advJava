package com.blogs.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

public class Employee {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)                	
    private Long id;
	
	private String firstName;
	private  String  lastName;
	private String   email;
	private String  location;
	private LocalDate joinDate;
	private String department;
	private Double salary;
	
public Employee() {
		
	}


	
	public Employee(String firstName, String lastName, String email, String location, LocalDate joinDate,
			String department, Double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.joinDate = joinDate;
		this.department = department;
		this.salary = salary;
	}



	
   

}
