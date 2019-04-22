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
@Entity(name = "tb_team")
public class Team implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "teamSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "seq_tb_team"), @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1") })
  @GeneratedValue(generator = "teamSequenceGenerator")
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamPlayer> players = new ArrayList<>();

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamTournament> tournaments = new ArrayList<>();

  public Team(String name) {
    this.name = name;
  }

  public void addPlayer(Player player) {
    TeamPlayer teamPlayer = new TeamPlayer(this, player);
    players.add(teamPlayer);
    player.getTeams().add(teamPlayer);
  }

  public void removePlayer(Player player) {
    TeamPlayer teamPlayer = new TeamPlayer(this, player);
    player.getTeams().remove(teamPlayer);
    players.remove(teamPlayer);
    teamPlayer.setPlayer(null);
    teamPlayer.setTeam(null);
  }

}
