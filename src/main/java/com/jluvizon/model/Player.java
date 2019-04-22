package com.jluvizon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "tb_player")
public class Player implements Serializable {

  private static final long serialVersionUID = -580983520840619080L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamPlayer> teams;

}
