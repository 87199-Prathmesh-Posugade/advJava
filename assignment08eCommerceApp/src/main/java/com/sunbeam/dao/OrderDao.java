package com.sunbeam.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeam.entities.Orders;

public interface OrderDao extends JpaRepository<Orders, Long>{
	@Query("select o from Orders o where o.createdOn between :sD and :eD")
	List<Orders> fetchOrderBetweeen(@Param("sD") LocalDate s, @Param("eD") LocalDate e);
}
