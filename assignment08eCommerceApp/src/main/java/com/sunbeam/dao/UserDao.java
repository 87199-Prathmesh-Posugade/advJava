package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.UserE;

public interface UserDao extends JpaRepository<UserE, Long> {

}
