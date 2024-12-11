package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.entities.Team;
import com.sunbeam.utils.HibernateUtils;

public class GetAllPlayersFromTeam {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			com.sunbeam.dao.PlayerDao p = new com.sunbeam.dao.PlayerDaoImpl();
			Team t = p.getAllPlayer(1l);
			if (t != null) {
				System.out.println(t);
				System.out.println(t.getPlayerList());
			}
		}
	}

}
