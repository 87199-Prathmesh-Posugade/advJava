package com.sunbeam.tester;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;

import com.sunbeam.entities.Team;
import com.sunbeam.utils.HibernateUtils;

public class DisplayAllTeams {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory()) {
			TeamDao t = new TeamDaoImpl();
			List<Team> teamL = t.getTeamList();
			teamL.forEach(System.out::println);
		}
	}

}
