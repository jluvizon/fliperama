package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tb_game")
public class Game implements Serializable {

  private static final long serialVersionUID = 3338086777378462028L;

  @Id
  @GenericGenerator(name = "gameSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "seq_tb_game"), @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1") })
  @GeneratedValue(generator = "gameSequenceGenerator")
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
  private List<Match> matches;

  public Game(String name) {
    this.name = name;
  }

}
