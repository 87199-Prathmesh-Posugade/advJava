package com.sunbeam.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//player details (first_name,last_name, dob,batting_avg,wickets_taken) n team id

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Players")
@ToString(callSuper = true)
@Entity
public class Player extends BaseEntity {
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private double battingAverage;
	private int wicketTaken;
	@ManyToOne
	@JoinColumn(name = "associatedTeamId")
	private Team playersTeam;

	public Player(String firstName, String lastName, LocalDate dob, double battingAverage, int wicketTaken) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.battingAverage = battingAverage;
		this.wicketTaken = wicketTaken;
	}
}
