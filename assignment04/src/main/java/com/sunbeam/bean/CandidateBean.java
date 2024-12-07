package com.sunbeam.bean;

import java.util.List;

import com.voters.daos.CandidateDao;
import com.voters.daos.CandidateDaoImpl;
import com.voters.entity.Candidate;

public class CandidateBean {
	private List<Candidate> ll = null;

	public CandidateBean() {
	}

	public void list() {
		try (CandidateDao c = new CandidateDaoImpl()) {
			ll = c.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Candidate> getLl() {
		return ll;
	}

}
