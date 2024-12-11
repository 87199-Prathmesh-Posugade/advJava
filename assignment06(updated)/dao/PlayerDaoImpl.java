package com.sunbeam.dao;

import com.sunbeam.entities.Player;
import com.sunbeam.entities.Team;

import static com.sunbeam.utils.HibernateUtils.getSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDaoImpl implements PlayerDao {

	public String insertPlayer(Player pobj, Long id) {
		String msg = "failed";
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			Team team = ss.get(Team.class, id);
			if (team != null) {
				team.addPlayer(pobj);
				ss.persist(pobj);
				msg = "success...!";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public Team getAllPlayer(Long l) {
		Team t = null;
		String jpql = "select t from Team t left join fetch t.playerList where t.id=:i";
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			t = ss.createQuery(jpql, Team.class).setParameter("i", l).getSingleResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return t;
	}

	@Override
	public String deletePlayer(Long t, Long p) {
		String msg = "failed...!";
		
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			
			Player pl = ss.get(Player.class, p);
			Team te = ss.get(Team.class, t);
			if (pl != null && te != null) {
				te.deletePlayer(pl);
			}
			msg = "succcess";
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
