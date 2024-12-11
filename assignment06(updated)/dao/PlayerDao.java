package com.sunbeam.dao;

import com.sunbeam.entities.Team;
import com.sunbeam.entities.Player;

public interface PlayerDao {
	public String insertPlayer(Player pobj, Long id);

	public Team getAllPlayer(Long l);

	public String deletePlayer(Long t, Long p);
}
