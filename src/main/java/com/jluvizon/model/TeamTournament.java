package com.jluvizon.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tb_team_tournament")
public class TeamTournament implements Serializable {

  private static final long serialVersionUID = -7279293851097717449L;

  @Id
  @ManyToOne
  private Team team;

  @Id
  @ManyToOne
  private Tournament tournament;

  public TeamTournament(Team team, Tournament tournament) {
    this.tournament = tournament;
    this.team = team;
  }

}