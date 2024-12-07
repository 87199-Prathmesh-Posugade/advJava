package com.voters.daos;

import java.util.List;

import com.voters.entity.Candidate;

public interface CandidateDao extends AutoCloseable {
	List<Candidate> findAll() throws Exception;

	int vote(int id) throws Exception;

	Candidate showWinner() throws Exception;

	int insertRecord(Candidate c) throws Exception;

	int deleteRecord(int id) throws Exception;

	int updateCand(Candidate c) throws Exception;

	List<Candidate> revAll() throws Exception;

	Candidate findById(int id) throws Exception;
}
