package com.koniukhov.tictactoe.controller;

import com.koniukhov.tictactoe.repository.GameRepository;
import com.koniukhov.tictactoe.repository.PlayerRepository;
import com.koniukhov.tictactoe.model.GameRequest;
import com.koniukhov.tictactoe.model.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameController {
    private static final String GAME_DESTINATION = "/queue/game";
    private static final String GAME_MAPPING = "/game";

    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @MessageMapping(GAME_MAPPING)
    public void game(@RequestBody GameRequest request){
        var player = playerRepository.findById(request.getPlayerId()).get();
        var game = player.getGame();
        var symbol = player.getSymbol();

        if (game.updateBoardState(player, request.getCellIndex())){
            game.changeCurrentPlayer();
            game.toggleCanMove();
            gameRepository.save(game);

            if (game.isGameOver(player)){
                var winner = game.getWinner(player);
                var player1 = game.getPlayers().get(0);
                var player2 = game.getPlayers().get(1);

                player1.reset();
                player2.reset();

                game.increaseNumberOfGames(winner, player1, player2);
                playerRepository.saveAll(game.getPlayers());
                gameRepository.delete(game);

                var gameResponse = new GameResponse(GameResponse.Status.FINISHED, request.getCellIndex(), symbol, game.getWinnerMsg(winner));

                msgTemplate.convertAndSendToUser(
                        player1.getId().toString(),
                        GAME_DESTINATION,
                        gameResponse);

                msgTemplate.convertAndSendToUser(
                        player2.getId().toString(),
                        GAME_DESTINATION,
                        gameResponse);
            }else {
                var player1 = game.getPlayers().get(0);
                var player2 = game.getPlayers().get(1);
                msgTemplate.convertAndSendToUser(
                        player1.getId().toString(),
                        GAME_DESTINATION,
                        new GameResponse(GameResponse.Status.IN_PROGRESS, request.getCellIndex(), symbol, game.getCurrentPlayer(), player1.getCanMove()));

                msgTemplate.convertAndSendToUser(
                        player2.getId().toString(),
                        GAME_DESTINATION,
                        new GameResponse(GameResponse.Status.IN_PROGRESS, request.getCellIndex(), symbol, game.getCurrentPlayer(), player2.getCanMove()));
            }
            playerRepository.saveAll(game.getPlayers());
        }
    }
}
