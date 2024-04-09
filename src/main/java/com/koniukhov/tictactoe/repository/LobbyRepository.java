package com.koniukhov.tictactoe.repository;


import com.koniukhov.tictactoe.model.Lobby;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LobbyRepository extends CrudRepository<Lobby, Integer> {
    List<Lobby> findAll();
    Lobby findFirstByOrderByIdAsc();
}
