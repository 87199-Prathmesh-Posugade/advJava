package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.entities.Team;

public interface TeamDao {
	String addTeam(Team t);

	List<Team> getTeamList();

	List<Team> getTeamsListAgeAvg(int age, double avg);

	List<Team> getTeamOwnerName(int age, double avg);

	String changeAgeByTeamName(int age, String abbr);

	String deleteById(Long id);
}
