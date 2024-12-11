package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.utils.HibernateUtils;

public class DeletePlayerFromTeam {

	public static void main(String[] args) {

		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			com.sunbeam.dao.PlayerDao p = new com.sunbeam.dao.PlayerDaoImpl();
			String msg = p.deletePlayer(1l, 4l);
			System.out.println(msg);
		}

	}

}
