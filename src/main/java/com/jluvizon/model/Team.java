package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
  private List<TeamMatch> matches;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<TeamPlayer> getPlayers() {
    return players;
  }

  public void setPlayers(List<TeamPlayer> players) {
    this.players = players;
  }

  public List<TeamMatch> getMatches() {
    return this.matches;
  }

  public void setMatches(List<TeamMatch> matches) {
    this.matches = matches;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Team)) {
      return false;
    }
    Team team = (Team) o;
    return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(matches, team.matches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, matches);
  }

  @Override
  public String toString() {
    return "{" + " id='" + id + "'" + ", name='" + name + "'" + "}";
  }

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
