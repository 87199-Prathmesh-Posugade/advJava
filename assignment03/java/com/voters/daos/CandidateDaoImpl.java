package com.voters.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.voters.entity.Candidate;

public class CandidateDaoImpl extends Dao implements CandidateDao {

	PreparedStatement findAll;
	PreparedStatement voteCand;
	PreparedStatement winner;
	PreparedStatement insertRecord;
	PreparedStatement delRecord;
	PreparedStatement updRecord;
	PreparedStatement revResult;
	PreparedStatement fbi;

	public CandidateDaoImpl() throws Exception {
		String sqlfbi = "select * from candidates where id = ?";
		fbi = con.prepareStatement(sqlfbi);

		String sqlFindAll = "SELECT * FROM candidates";
		findAll = con.prepareStatement(sqlFindAll);

		String sqlvoteCand = "UPDATE candidates SET votes = votes + 1 WHERE id = ?";
		voteCand = con.prepareStatement(sqlvoteCand);

		String sqlWinner = "SELECT * FROM candidates ORDER BY votes DESC LIMIT 1";
		winner = con.prepareStatement(sqlWinner);

		String sqlInsert = "INSERT INTO candidates VALUES (default, ?, ?, ?)";
		insertRecord = con.prepareStatement(sqlInsert);

		String del = "DELETE FROM candidates WHERE id = ?";
		delRecord = con.prepareStatement(del);

		String up = "UPDATE candidates SET name = ?, party = ?, votes = ? WHERE id = ?";
		updRecord = con.prepareStatement(up);

		String rev = "SELECT * FROM candidates ORDER BY votes DESC";
		revResult = con.prepareStatement(rev);
	}

	@Override
	public void close() throws Exception {
		findAll.close();
		fbi.close();
		voteCand.close();
		winner.close();
		insertRecord.close();
		delRecord.close();
		updRecord.close();
		revResult.close();
		super.close();
	}

	@Override
	public List<Candidate> findAll() throws Exception {
		try (ResultSet rs = findAll.executeQuery()) {
			List<Candidate> c = new ArrayList<Candidate>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String party = rs.getString("party");
				int votes = rs.getInt("votes");
				c.add(new Candidate(id, name, party, votes));
			}
			return c;
		}
	}

	@Override
	public int vote(int id) throws Exception {
		voteCand.setInt(1, id);
		int affectedRows = voteCand.executeUpdate();
		return affectedRows;
	}

	@Override
	public Candidate showWinner() throws Exception {
		try (ResultSet rs = winner.executeQuery()) {
			if (rs.next())
				return new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes"));
		}
		return null;
	}

	@Override
	public int insertRecord(Candidate c) throws Exception {
		insertRecord.setString(1, c.getName());
		insertRecord.setString(2, c.getParty());
		insertRecord.setInt(3, c.getVotes());
		return insertRecord.executeUpdate();
	}

	@Override
	public int deleteRecord(int id) throws Exception {
		delRecord.setInt(1, id);
		return delRecord.executeUpdate();
	}

	@Override
	public int updateCand(Candidate c) throws Exception {
		updRecord.setInt(4, c.getId());
		updRecord.setString(1, c.getName());
		updRecord.setString(2, c.getParty());
		updRecord.setInt(3, c.getVotes());
		return updRecord.executeUpdate();
	}

	@Override
	public List<Candidate> revAll() throws Exception {
		List<Candidate> l = new ArrayList<Candidate>();
		try (ResultSet rs = revResult.executeQuery()) {
			while (rs.next()) {
				l.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		}
		return l;
	}

	@Override
	public Candidate findById(int id) throws Exception {
		fbi.setInt(1, id);
		try (ResultSet rs = fbi.executeQuery()) {
			if (rs.next())
				return new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes"));
		}
		return null;
	}

}
