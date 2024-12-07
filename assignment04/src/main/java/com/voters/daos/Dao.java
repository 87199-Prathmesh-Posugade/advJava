package com.voters.daos;

import java.sql.Connection;

import com.voters.utils.DatabaseUtil;

public abstract class Dao implements AutoCloseable {

	protected Connection con;

	public Dao () throws Exception {
		con = DatabaseUtil.getCon();
	}
	
	@Override
	public void close() throws Exception {
		con.close();
	}

}
