package com.blogs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.entity.Employee;

public interface EmpDao  extends JpaRepository<Employee,Long>{

}
