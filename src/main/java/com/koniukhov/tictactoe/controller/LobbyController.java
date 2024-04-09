package com.koniukhov.tictactoe.controller;

import com.koniukhov.tictactoe.model.*;
import com.koniukhov.tictactoe.repository.GameRepository;
import com.koniukhov.tictactoe.repository.LobbyRepository;
import com.koniukhov.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Controller
public class LobbyController {
    private static final String LOBBY_DESTINATION = "/queue/lobby";
    private static final String LOBBY_MAPPING = "/lobby";
    private static final int EMPTY_LOBBY_SIZE = 0;

    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @Autowired
    private LobbyRepository lobbyRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @MessageMapping(LOBBY_MAPPING)
    public void lobby(@RequestBody BaseRequest request){
        var player = playerRepository.findById(request.getPlayerId()).get();
        var playersInLobby = lobbyRepository.findAll().size();

        if (playersInLobby == EMPTY_LOBBY_SIZE){
            Lobby lobby = new Lobby();
            lobby.setPlayerId(request.getPlayerId());
            lobbyRepository.save(lobby);

            msgTemplate.convertAndSendToUser(
                    String.valueOf(request.getPlayerId()),
                    LOBBY_DESTINATION,
                    new LobbyResponse(LobbyResponse.Status.WAITING));
        }else {
            Lobby lobby = lobbyRepository.findFirstByOrderByIdAsc();
            Player enemy = playerRepository.findById(lobby.getPlayerId()).get();

            if (!enemy.getName().equals(player.getName())){
                lobbyRepository.delete(lobby);

                Game game = new Game();
                game.setPlayers(Arrays.asList(player, enemy));
                game.init();
                player.setGame(game);
                enemy.setGame(game);

                gameRepository.save(game);
                playerRepository.save(player);
                playerRepository.save(enemy);

                msgTemplate.convertAndSendToUser(
                        String.valueOf(request.getPlayerId()),
                        LOBBY_DESTINATION,
                        new LobbyResponse(LobbyResponse.Status.FOUND, player.getName(), enemy.getName(),
                                new PreGameResponse(game.getCurrentPlayer(), player.getCanMove())));

                msgTemplate.convertAndSendToUser(
                        enemy.getId().toString(),
                        LOBBY_DESTINATION,
                        new LobbyResponse(LobbyResponse.Status.FOUND, enemy.getName(), player.getName(),
                                new PreGameResponse(game.getCurrentPlayer(), enemy.getCanMove())));
            }else {
                msgTemplate.convertAndSendToUser(
                        String.valueOf(request.getPlayerId()),
                        LOBBY_DESTINATION,
                        new LobbyResponse(LobbyResponse.Status.WAITING));
            }
        }
    }
}
