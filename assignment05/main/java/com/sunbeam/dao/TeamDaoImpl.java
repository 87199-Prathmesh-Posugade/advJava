package com.sunbeam.dao;

import org.hibernate.*;
import static com.sunbeam.utils.HibernateUtils.getSessionFactory;

import java.io.Serializable;

import com.sunbeam.entities.Team;

public class TeamDaoImpl implements TeamDao {
	@Override
	public String addTeam(Team t) {
		String msg = "failed...!";
		
		Session sess = getSessionFactory().getCurrentSession();
		Transaction tx = sess.beginTransaction();
		try {
			Serializable id = sess.save(t);
			tx.commit();
			msg = id + " added team...!";
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}
}
