package com.jluvizon.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tb_team_match")
public class TeamMatch implements Serializable {

  private static final long serialVersionUID = -7140957424172520449L;

  @Id
  @ManyToOne
  private Team team;

  @Id
  @ManyToOne
  private Match match;

  @Column(name = "ind_winner")
  private boolean winner;

  public TeamMatch(Team team, Match match) {
    this.team = team;
    this.match = match;
  }

  public TeamMatch(Team team, Match match, boolean winner) {
    this.team = team;
    this.match = match;
    this.winner = winner;
  }

  public boolean isWinner() {
    return this.winner;
  }

  public void setWinner(boolean winner) {
    this.winner = winner;
  }

  public Match getMatch() {
    return this.match;
  }

  public void setMatch(Match match) {
    this.match = match;
  }

  public Team getTeam() {
    return this.team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof TeamMatch)) {
      return false;
    }
    TeamMatch teamMatch = (TeamMatch) o;
    return Objects.equals(team, teamMatch.team) && Objects.equals(match, teamMatch.match);
  }

  @Override
  public int hashCode() {
    return Objects.hash(team, match);
  }

  @Override
  public String toString() {
    return "{" + " team='" + team + "'" + ", match='" + match + "'" + ", winner='" + winner + "'" + "}";
  }

}