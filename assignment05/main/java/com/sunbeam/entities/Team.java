package com.sunbeam.entities;

import javax.persistence.*;

@Entity
@Table(name = "Teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long teamId;

	@Column(unique = true, length = 100)
	private String name;

	@Column(name = "abbreviation", unique = true, length = 10)
	private String abbr;

	@Column(length = 20, nullable = false)
	private String owner;

	@Column(name = "max_player_age")
	private int maxPlayerAge;

	@Column(name = "batting_avg ")
	private double battingAvg;

	@Column(name = "wickets_taken")
	private int wicketsTaken;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Team(String name, String abbr, String owner, int maxPlayerAge, double battingAvg, int wicketsTaken) {
		this.name = name;
		this.abbr = abbr;
		this.owner = owner;
		this.maxPlayerAge = maxPlayerAge;
		this.battingAvg = battingAvg;
		this.wicketsTaken = wicketsTaken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getMaxPlayerAge() {
		return maxPlayerAge;
	}

	public void setMaxPlayerAge(int maxPlayerAge) {
		this.maxPlayerAge = maxPlayerAge;
	}

	public double getBattingAvg() {
		return battingAvg;
	}

	public void setBattingAvg(double battingAvg) {
		this.battingAvg = battingAvg;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

}
