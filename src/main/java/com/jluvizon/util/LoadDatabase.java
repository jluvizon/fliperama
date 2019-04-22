package com.jluvizon.util;

import java.util.ArrayList;
import java.util.List;

import com.jluvizon.model.Player;
import com.jluvizon.model.Team;
import com.jluvizon.model.Tournament;
import com.jluvizon.model.Game;
import com.jluvizon.model.Match;
import com.jluvizon.repository.GameRepository;
import com.jluvizon.repository.MatchRepository;
import com.jluvizon.repository.PlayerRepository;
import com.jluvizon.repository.TeamRepository;
import com.jluvizon.repository.TournamentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(PlayerRepository playerRepository, TeamRepository teamRepository,
      MatchRepository matchRepository, TournamentRepository tournamentRepository, GameRepository gameRepository) {

    return args -> {

      log.info("Loading games...");
      Game game = gameRepository.save(new Game("Foosball"));

      List<Player> players = defaultPlayers();
      log.info("Loading players...");
      players = playerRepository.save(players);

      log.info("Loading teams...");
      List<Team> teams = new ArrayList<>();
      players.forEach(player -> {
        Team team = new Team(player.getName() + " S.C");
        team.addPlayer(player);
        teams.add(teamRepository.save(team));
      });

      log.info("Loading tournament...");
      Tournament tournament = new Tournament(game, "Campeonato de Foosball Luso-Brasileiro - Primeira Edição");
      teams.forEach(team -> {
        tournament.addTeam(team);
      });
      tournamentRepository.save(tournament);

      log.info("Loading matches...");
      List<Match> matches = new ArrayList<>();
      for (int numMatches = 1; numMatches <= tournament.getNumMatchesAgainstEachTeam(); numMatches++) {
        for (int i = 0; i < teams.size() - 1; i++) {
          Team teamOne = teams.get(i);
          for (int j = i + 1; j < teams.size(); j++) {
            Team teamTwo = teams.get(j);
            Match match = new Match(game, teamOne, teamTwo);
            match.setTournament(tournament);
            matches.add(match);
          }
        }
      }
      matchRepository.save(matches);
    };
  }

  private List<Player> defaultPlayers() {
    List<Player> players = new ArrayList<Player>();

    players.add(new Player("Jariel"));
    players.add(new Player("Wagner"));
    players.add(new Player("Carlos"));
    players.add(new Player("Eder"));
    players.add(new Player("Jaques"));
    players.add(new Player("Gleison"));

    return players;
  }

}