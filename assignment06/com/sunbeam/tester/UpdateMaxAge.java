package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;
import com.sunbeam.utils.*;

public class UpdateMaxAge {

//	3.5 Update max age requirement
//	i/p - IPL team name
//	o/p - message (success | failure)
	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			TeamDao t = new TeamDaoImpl();
			System.out.println("Enter max age and abbr: ");
			String msg = t.changeAgeByTeamName(sc.nextInt(), sc.next());
			System.out.println(msg);
		}
	}
}
