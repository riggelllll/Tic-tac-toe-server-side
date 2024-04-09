package com.koniukhov.tictactoe.repository;

import com.koniukhov.tictactoe.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
}
