package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "tb_match")
public class Match implements Serializable {

  private static final long serialVersionUID = 5612028913000708372L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamMatch> teams;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public List<TeamMatch> getTeams() {
    return teams;
  }

  public void setTeams(List<TeamMatch> teams) {
    this.teams = teams;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Match)) {
      return false;
    }
    Match match = (Match) o;
    return Objects.equals(id, match.id) && Objects.equals(game, match.game);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, game);
  }

  @Override
  public String toString() {
    return "{" + " id='" + id + "'" + ", game='" + game + "'" + ", teams='" + teams + "'" + "}";
  }

  public void addTeam(Team team, boolean isWinner) {
    TeamMatch teamMatch = new TeamMatch(team, this, isWinner);
    teams.add(teamMatch);
    team.getMatches().add(teamMatch);
  }

  public void removeTeam(Team team) {
    TeamMatch teamMatch = new TeamMatch(team, this);
    team.getMatches().remove(teamMatch);
    teams.remove(teamMatch);
    teamMatch.setMatch(null);
    teamMatch.setTeam(null);
  }

}
