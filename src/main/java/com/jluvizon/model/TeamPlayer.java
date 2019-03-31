package com.jluvizon.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tb_team_player")
public class TeamPlayer implements Serializable {

  private static final long serialVersionUID = 8740729091270572141L;

  @Id
  @ManyToOne
  private Team team;

  @Id
  @ManyToOne
  private Player player;

  public TeamPlayer(Team team, Player player) {
    this.team = team;
    this.player = player;
  }

  public Team getTeam() {
    return this.team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public TeamPlayer team(Team team) {
    this.team = team;
    return this;
  }

  public TeamPlayer player(Player player) {
    this.player = player;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof TeamPlayer)) {
      return false;
    }
    TeamPlayer teamPlayer = (TeamPlayer) o;
    return Objects.equals(team, teamPlayer.team) && Objects.equals(player, teamPlayer.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(team, player);
  }

  @Override
  public String toString() {
    return "{" + " team='" + getTeam() + "'" + ", player='" + getPlayer() + "'" + "}";
  }

}