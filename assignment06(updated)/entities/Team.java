package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Teams")
@ToString(callSuper = true, exclude = { "playerList" })
public class Team extends BaseEntity {
	
	@OneToMany(mappedBy = "playersTeam", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Player> playerList = new ArrayList<>();

	@Column(unique = true, length = 100)
	private String name;

	@Column(name = "abbreviation", unique = true, length = 10)
	private String abbr;

	public Team(String abbr) {
		this.abbr = abbr;
	}

	@Column(name = "max_player_age")
	private int maxPlayerAge;

	@Column(name = "batting_avg ")
	private double battingAvg;

	@Column(name = "wickets_taken")
	private int wicketsTaken;

	public Team(String name, String abbr, int maxPlayerAge, double battingAvg, int wicketsTaken) {
		this.name = name;
		this.abbr = abbr;
		this.maxPlayerAge = maxPlayerAge;
		this.battingAvg = battingAvg;
		this.wicketsTaken = wicketsTaken;
	}

	public void addPlayer(Player pobj) {
		this.playerList.add(pobj);
		pobj.setPlayersTeam(this);
	}

	public void deletePlayer(Player pl) {
		playerList.remove(pl);
		pl.setPlayersTeam(null);
	}
}
