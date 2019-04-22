package com.jluvizon.repository;

import com.jluvizon.model.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}