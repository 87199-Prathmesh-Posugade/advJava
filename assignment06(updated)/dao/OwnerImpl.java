package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.entities.Team;

public class OwnerImpl implements Owner {
	@Override
	public String addOwner(Long l, com.sunbeam.entities.Owner owner) {
		String msg = "failed";
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			Team team = ss.get(Team.class, l);
			owner.setOwnsTeam(team);
			ss.persist(owner);
			msg = "success...!";
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;	
	}
}
