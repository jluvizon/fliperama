package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "tb_game")
public class Game implements Serializable {

  private static final long serialVersionUID = 3338086777378462028L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
  private List<Match> matches;

}
