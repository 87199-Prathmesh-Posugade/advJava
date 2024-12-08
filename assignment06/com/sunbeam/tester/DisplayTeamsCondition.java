package com.sunbeam.tester;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;
import com.sunbeam.entities.Team;
import com.sunbeam.utils.HibernateUtils;

public class DisplayTeamsCondition {
//	Display all teams , requiring 
//	max age requirement of the player < specified age
//	batting average required > specified average
//	I/P - age n batting average

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = HibernateUtils.getSessionFactory()) {
			TeamDao t=new TeamDaoImpl();
			System.out.println("Enter Age and Average: ");
			List<Team> ll=t.getTeamsListAgeAvg(sc.nextInt(), sc.nextDouble());
			ll.forEach(System.out::println);
		}
	}

}
