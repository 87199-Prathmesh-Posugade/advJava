package com.sunbeam.dao;

import org.hibernate.*;
import static com.sunbeam.utils.HibernateUtils.getSessionFactory;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public List<Team> getTeamList() {
		List<Team> ll = null;
		String jpql = "select t from Team t";

		Session ss = getSessionFactory().getCurrentSession();

		Transaction tn = ss.beginTransaction();

		try {
			ll = ss.createQuery(jpql, Team.class).getResultList();

			tn.commit();
			// need to commit even if for select need to return db conn used by session into
			// pool
			// and destroy L1 cache
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}
			throw e;
		}
		return ll;
	}

//	"Team [teamId=" + teamId + ", name=" + name + ", abbr=" + abbr + ", owner=" + owner + ", maxPlayerAge="
//	+ maxPlayerAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "

	@Override
	public List<Team> getTeamsListAgeAvg(int age, double avg) {
		List<Team> ll = null;
		String jpql = "select t from Team t where maxPlayerAge<:ag and battingAvg>:av";

		Session ss = getSessionFactory().getCurrentSession();

		Transaction tx = ss.beginTransaction();

		try {
			ll = ss.createQuery(jpql, Team.class).setParameter("ag", age).setParameter("av", avg).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return ll;
	}

//	"Team [teamId=" + teamId + ", name=" + name + ", abbr=" + abbr + ", owner=" + owner + ", maxPlayerAge="
//	+ maxPlayerAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "

//	Display team owner names n team abbreviations
//	 requiring 
//	max age requirement of the player < specified age
//	batting average > specified average
//	I/P - age n batting average
//	o/p - list of teams
	@Override
	public List<Team> getTeamOwnerName(int age, double avg) {
		List<Team> sll = null;
		String jpql = "select new com.sunbeam.entities.Team(abbr, owner) from Team t where maxPlayerAge<:ag and battingAvg>:av";

		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			sll = ss.createQuery(jpql, Team.class).setParameter("ag", age).setParameter("av", avg).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return sll;
	}

//	"Team [teamId=" + teamId + ", name=" + name + ", abbr=" + abbr + ", owner=" + owner + ", maxPlayerAge="
//	+ maxPlayerAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "
	@Override
	public String changeAgeByTeamName(int age, String abbr) {
		String msg = "age update failed";
		String jpql = "select t from Team t where abbr=:abbr";
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			Team team = ss.createQuery(jpql, Team.class).setParameter("abbr", abbr).getSingleResult();
			team.setMaxPlayerAge(age);
			msg = "age update successfully";
			tx.commit();
			ss.evict(team);
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public String deleteById(Long id) {
		String msg = "failed to delete by id";
		String jql = "select t from Team t where teamId=:id";
		Session ss = getSessionFactory().getCurrentSession();
		Transaction tx = ss.beginTransaction();
		try {
			Team team = ss.createQuery(jql, Team.class).setParameter("id", id).getSingleResult();
			ss.delete(team);
			tx.commit();
			msg = "deleted successfully";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		}
		return msg;
	}

}
