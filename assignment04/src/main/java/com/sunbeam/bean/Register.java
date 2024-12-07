package com.sunbeam.bean;

import java.sql.Date;

import com.voters.daos.UserDao;
import com.voters.daos.UserDaoImpl;
import com.voters.entity.User;

public class Register {

	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String dob;
	private int inserted = 0;
	private User u;

	public void li() {
		inserted = 0;
		try (UserDao u = new UserDaoImpl()) {
			User obj = u.findByEmail(email);
			if (obj.getPassword().equals(password)) {
				this.u = obj;
				inserted = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public void iu() {
		try (UserDao u = new UserDaoImpl()) {
//			private int id;
//			private String firstName;
//			private String lastName;
//			private String email;
//			private String password;
//			private Date birth;
//			private int status;
//			private String role;
			Date d = Date.valueOf(dob);
			inserted = u.insertUser(new User(0, first_name, last_name, email, password, d, 0, "voter"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getInserted() {
		return inserted;
	}

	public void setInserted(int inserted) {
		this.inserted = inserted;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Register() {
	}

}
