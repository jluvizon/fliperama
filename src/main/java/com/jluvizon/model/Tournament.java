package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Tournament implements Serializable {

  private static final long serialVersionUID = -6836771170963089686L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  private Game game;

  @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
  private List<Match> matches;

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamTournament> teams;

  public void addTeam(Team team) {
    TeamTournament teamTournament = new TeamTournament(team, this);
    teams.add(teamTournament);
    team.getTournaments().add(teamTournament);
  }

  public void removeTeam(Team team) {
    TeamTournament teamTournament = new TeamTournament(team, this);
    team.getTournaments().remove(teamTournament);
    teams.remove(teamTournament);
    teamTournament.setTournament(null);
    teamTournament.setTeam(null);
  }

}