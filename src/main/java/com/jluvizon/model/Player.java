package com.jluvizon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tb_player")
public class Player implements Serializable {

  private static final long serialVersionUID = -580983520840619080L;

  @Id
  @GenericGenerator(name = "playerSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "seq_tb_player"), @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1") })
  @GeneratedValue(generator = "playerSequenceGenerator")
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamPlayer> teams = new ArrayList<TeamPlayer>();

  public Player(String name) {
    this.name = name;
  }

}
