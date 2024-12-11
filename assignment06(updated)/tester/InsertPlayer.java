package com.sunbeam.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.entities.Player;
import com.sunbeam.entities.Team;
import com.sunbeam.utils.*;

public class InsertPlayer {
	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			com.sunbeam.dao.PlayerDao p = new com.sunbeam.dao.PlayerDaoImpl();
			Player player=new Player("mohmd", "siraj", LocalDate.parse("1996-11-18"), 5, 1);
			System.out.println(p.insertPlayer(player, 1l));
		}
	}
}
