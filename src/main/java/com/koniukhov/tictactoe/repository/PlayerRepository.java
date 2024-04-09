package com.koniukhov.tictactoe.repository;

import com.koniukhov.tictactoe.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    boolean existsByName(String name);
}
