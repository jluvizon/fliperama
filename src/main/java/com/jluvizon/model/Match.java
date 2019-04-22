package com.jluvizon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "tb_match")
public class Match implements Serializable {

  private static final long serialVersionUID = 5612028913000708372L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private Game game;

  @ManyToOne
  private Tournament tournament;

  @ManyToOne
  private Team homeTeam;

  @ManyToOne
  private Team awayTeam;

  @Column
  private Integer scoreHomeTeam;

  @Column
  private Integer scoreAwayTeam;

  public Match(Game game, Team homeTeam, Team awayTeam) {
    this.game = game;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }

}
