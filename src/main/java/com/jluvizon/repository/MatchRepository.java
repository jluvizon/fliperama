package com.jluvizon.repository;

import com.jluvizon.model.Match;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}