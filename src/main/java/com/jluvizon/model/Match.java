package com.jluvizon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tb_match")
public class Match implements Serializable {

  private static final long serialVersionUID = 5612028913000708372L;

  @Id
  @GenericGenerator(name = "matchSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "seq_tb_match"), @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1") })
  @GeneratedValue(generator = "matchSequenceGenerator")
  private Long id;

  @ManyToOne
  private Game game;

  @ManyToOne
  private Tournament tournament;

  @ManyToOne
  private Team teamOne;

  @ManyToOne
  private Team teamTwo;

  @Column
  private Integer scoreTeamOne;

  @Column
  private Integer scoreTeamTwo;

  public Match(Game game, Team teamOne, Team teamTwo) {
    this.game = game;
    this.teamOne = teamOne;
    this.teamTwo = teamTwo;
  }

}
