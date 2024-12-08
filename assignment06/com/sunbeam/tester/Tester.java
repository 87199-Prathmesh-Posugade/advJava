package com.sunbeam.tester;

import static com.sunbeam.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;
import com.sunbeam.entities.Team;

public class Tester {
	public static void main(String[] args) {
//		try (SessionFactory sf = getSessionFactory()) {
//			System.out.println("Hibernate is up and running...!");
//		}
		try (SessionFactory sf = getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			TeamDao t = new TeamDaoImpl();
//			String name, String abbr, String owner, int maxPlayerAge, double battingAvg, wicketsTaken
			System.out.println("Enter name, abbr, owner, maxPlayerAge, battingAvg, wicketsTaken");
			Team tm = new Team(sc.next(), sc.next().toUpperCase(), sc.next(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
			System.out.println(t.addTeam(tm));
		}
	}
}
