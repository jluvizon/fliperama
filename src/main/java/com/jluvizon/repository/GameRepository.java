package com.jluvizon.repository;

import com.jluvizon.model.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}