package com.sunbeam.bean;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.daos.UserDao;
import com.voters.daos.UserDaoImpl;

public class Candidate {
	private String name;
	private int id;
	private String party;
	private int votes;
	private boolean c;
	private int userid;

	public void vot() {
		try (CandidateDao obj = new CandidateDaoImpl()) {
			c = false;
			int cc = obj.vote(this.id);
			if (cc == 1) {
				try (UserDao u = new UserDaoImpl()) {
					int flag = u.reflectVoted(userid);
					if (flag == 1) {
						c = true;
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void ret() {
		try (CandidateDao obj = new CandidateDaoImpl()) {
			com.voters.entity.Candidate cc = obj.findById(this.id);
			this.name = cc.getName();
			this.party = cc.getParty();
			this.votes = cc.getVotes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del() {
		c = false;
		try (CandidateDao obj = new CandidateDaoImpl()) {
			int d = obj.deleteRecord(this.id);
			if (d == 1) {
				c = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void upd() {
		c = false;
		try (CandidateDao obj = new CandidateDaoImpl()) {
			int count = obj.updateCand(new com.voters.entity.Candidate(id, name, party, votes));
			if (count == 1) {
				c = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Candidate() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public boolean getC() {
		return c;
	}

	public void setC(boolean c) {
		this.c = c;
	}
}
