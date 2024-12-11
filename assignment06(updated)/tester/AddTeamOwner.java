package com.sunbeam.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.entities.Owner;
import com.sunbeam.utils.HibernateUtils;

public class AddTeamOwner {
	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {
			com.sunbeam.dao.Owner own = new com.sunbeam.dao.OwnerImpl();
			String s = own.addOwner(1l, new Owner("mallya", "mallya@7gmail.com", "Make it Large"));
			System.out.println(s);
		}
	}
}
