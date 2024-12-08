package com.sunbeam.tester;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;
import com.sunbeam.entities.Team;
import com.sunbeam.utils.*;

public class TeamOwnerName {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			TeamDao t = new TeamDaoImpl();
			System.out.println("Enter age and avg: ");
			List<Team> owns = t.getTeamOwnerName(sc.nextInt(), sc.nextDouble());
			owns.forEach(System.out::println);
		}
	}

}
