package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "tb_team")
public class Team implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamPlayer> players;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamTournament> tournaments;

  public void addPlayer(Player player) {
    TeamPlayer teamPlayer = new TeamPlayer(this, player);
    players.add(teamPlayer);
    player.getTeams().add(teamPlayer);
  }

  public void removePlayer(Player player) {
    TeamPlayer teamPlayer = new TeamPlayer(this, player);
    player.getTeams().remove(teamPlayer);
    players.remove(teamPlayer);
    teamPlayer.setPlayer(null);
    teamPlayer.setTeam(null);
  }

}
