package com.sunbeam.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sf;
	static {
		System.out.println("in init static block");
		//empty, loading and mapping, create sessionfactory
		sf = new Configuration().configure().buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		return sf;
	}
}
