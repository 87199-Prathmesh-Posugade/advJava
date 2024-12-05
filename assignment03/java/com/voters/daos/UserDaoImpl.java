package com.voters.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.voters.entity.User;

public class UserDaoImpl extends Dao implements UserDao {
	private PreparedStatement findAll;
	private PreparedStatement findEmail;
	private PreparedStatement incrVoted;
	private PreparedStatement insertUser;
	private PreparedStatement updateUser;

	public UserDaoImpl() throws Exception {
		String sqlFindAll = "SELECT * FROM users";
		findAll = con.prepareStatement(sqlFindAll);

		String sqlFindByEmail = "SELECT * FROM users WHERE email = ?";
		findEmail = con.prepareStatement(sqlFindByEmail);

		String sqlIncrVoted = "UPDATE users SET status = 1 WHERE id = ?";
		incrVoted = con.prepareStatement(sqlIncrVoted);

		String ins = "INSERT INTO users VALUES(default, ?, ?, ?, ?, ?, ?, ?)";
		insertUser = con.prepareStatement(ins);

		String update = "UPDATE users SET first_name=?, last_name=?, email=?, password=?, dob=?, status=?, role=? WHERE id=?";
		updateUser = con.prepareStatement(update);
	}

	@Override
	public void close() throws Exception {
		updateUser.close();
		insertUser.close();
		findAll.close();
		findEmail.close();
		incrVoted.close();
		super.close();
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> l = new ArrayList<User>();
		try (ResultSet rs = findAll.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				Date d = rs.getDate("dob");
				int status = rs.getInt("status");
				String role = rs.getString("role");
				User u = new User(id, fName, lName, email, pass, d, status, role);
				l.add(u);
			}
			return l;
		}
	}

	@Override
	public User findByEmail(String email) throws Exception {
		findEmail.setString(1, email);
		try (ResultSet rs = findEmail.executeQuery()) {
			if (rs.next()) {
				int id = rs.getInt("id");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String pass = rs.getString("password");
				Date d = rs.getDate("dob");
				int status = rs.getInt("status");
				String role = rs.getString("role");
				return new User(id, fName, lName, email, pass, d, status, role);
			}
			return null;
		}
	}

	@Override
	public int reflectVoted(int id) throws Exception {
		incrVoted.setInt(1, id);
		int affectedRow = incrVoted.executeUpdate();
		return affectedRow;
	}

	@Override
	public int insertUser(User u) throws Exception {
		insertUser.setString(1, u.getFirstName());
		insertUser.setString(2, u.getLastName());
		insertUser.setString(3, u.getEmail());
		insertUser.setString(4, u.getPassword());
		insertUser.setDate(5, u.getBirth());
		insertUser.setInt(6, u.getStatus());
		insertUser.setString(7, u.getRole());
		return insertUser.executeUpdate();
	}

	@Override
	public int updateUser(User u) throws Exception {
		updateUser.setString(1, u.getFirstName());
		updateUser.setString(2, u.getLastName());
		updateUser.setString(3, u.getEmail());
		updateUser.setString(4, u.getPassword());
		updateUser.setDate(5, u.getBirth());
		updateUser.setInt(6, u.getStatus());
		updateUser.setString(7, u.getRole());
		updateUser.setInt(8, u.getId());
		int count = updateUser.executeUpdate();
		return count;
	}

}
