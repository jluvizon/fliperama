package com.jluvizon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tb_tournament")
public class Tournament implements Serializable {

  private static final long serialVersionUID = -6836771170963089686L;

  @Id
  @GenericGenerator(name = "tournamentSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "seq_tb_tournament"), @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1") })
  @GeneratedValue(generator = "tournamentSequenceGenerator")
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  private Game game;

  @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
  private List<Match> matches = new ArrayList<>();

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamTournament> teams = new ArrayList<>();

  @Column
  private Integer numMatchesAgainstEachTeam = 2;

  public Tournament(Game game, String name) {
    this.game = game;
    this.name = name;
  }

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