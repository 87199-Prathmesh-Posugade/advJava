package com.voters.daos;

import java.util.List;

import com.voters.entity.User;

public interface UserDao extends AutoCloseable {

	List<User> findAll() throws Exception;

	User findByEmail(String email) throws Exception;

	int reflectVoted(int id) throws Exception;

	int insertUser(User u) throws Exception;

}