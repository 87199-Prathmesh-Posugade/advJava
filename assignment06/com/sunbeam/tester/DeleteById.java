package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.dao.TeamDao;
import com.sunbeam.dao.TeamDaoImpl;
import com.sunbeam.utils.*;

public class DeleteById {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			TeamDao t = new TeamDaoImpl();
			System.out.println("Enter id: ");
			String msg = t.deleteById(sc.nextLong());
			System.out.println(msg);
		}

	}

}
