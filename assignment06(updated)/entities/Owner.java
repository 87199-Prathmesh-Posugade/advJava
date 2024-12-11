package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = { "ownsTeam" })
@Entity
public class Owner extends BaseEntity {
//	name , email , company +
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	private String company;
	@JoinColumn(name = "owns_team")
	@OneToOne
	private Team ownsTeam;
	public Owner(String name, String email, String company) {
		this.name = name;
		this.email = email;
		this.company = company;
	}
	
}
