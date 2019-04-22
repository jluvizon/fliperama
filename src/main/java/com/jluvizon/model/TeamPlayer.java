package com.jluvizon.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}