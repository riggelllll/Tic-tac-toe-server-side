package com.koniukhov.tictactoe.model;

import com.koniukhov.tictactoe.util.Util;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
public class Game {
    private static final String PLAYERS_MAPPED_BY = "game";
    private static final List<String> WINNING_INDEXES = Arrays.asList("012", "345", "678", "036", "147", "258", "048", "246");
    private static final char SYMBOL_X = 'X';
    private static final char SYMBOL_O = 'O';
    private static final char SYMBOL_E = 'E';
    private static final int NUM_OF_CELLS = 9;
    private static final int NUM_OF_OCCURRENCES = 3;
    private static final String WIN_MSG = "%s won";
    private static final String DRAW_MSG = "It's a draw";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = PLAYERS_MAPPED_BY)
    private List<Player> players = new ArrayList<>();

    private String boardState = String.valueOf(SYMBOL_E).repeat(NUM_OF_CELLS);
    private String currentPlayer;

    public void init(){
        var random = new Random();

        if (random.nextBoolean()){
            players.get(0).setCanMove(true);
            players.get(0).setSymbol(SYMBOL_X);

            players.get(1).setCanMove(false);
            players.get(1).setSymbol(SYMBOL_O);

            currentPlayer = players.get(0).getName();
        }else {
            players.get(1).setCanMove(true);
            players.get(1).setSymbol(SYMBOL_X);

            players.get(0).setCanMove(false);
            players.get(0).setSymbol(SYMBOL_O);

            currentPlayer = players.get(1).getName();
        }
    }

    public boolean updateBoardState(Player player, int cellIndex){
        var needToUpdate = false;
        if (player.getCanMove()){
            StringBuilder strBuilder = new StringBuilder(boardState);
            if (strBuilder.charAt(cellIndex) == SYMBOL_E){
                strBuilder.setCharAt(cellIndex, player.getSymbol());
                boardState = strBuilder.toString();
                needToUpdate = true;
            }
        }
        return needToUpdate;
    }

    public boolean isGameOver(Player player){
        if (boardState.indexOf(SYMBOL_E) == -1){
            return true;
        }else return hasWinner(player);
    }

    public String getWinner(Player player){
        var winner = "";

        if (hasWinner(player)){
            winner = player.getName();
        }

        return winner;
    }

    public String getWinnerMsg(String winner){
        var res = "";
        if (!winner.isEmpty()){
            res = String.format(WIN_MSG, winner);
        }else {
            res = DRAW_MSG;
        }

        return res;
    }

    private boolean hasWinner(Player player){
        var playerIndexes = Util.getIndexesBySymbol(player.getSymbol(), boardState);
        var result = false;

        for (String winIndex : WINNING_INDEXES){
            var occurrences = 0;

            for (char ch : winIndex.toCharArray()){
                if (playerIndexes.indexOf(ch) != -1){
                    occurrences++;
                }
            }
            if (occurrences == NUM_OF_OCCURRENCES){
                result = true;
                break;
            }
        }
        return result;
    }

    public void toggleCanMove(){
        var player1= players.get(0);
        var player2= players.get(1);
        player1.setCanMove(!player1.getCanMove());
        player2.setCanMove(!player2.getCanMove());
    }

    public void changeCurrentPlayer(){
        var playerNames = players.get(0).getName() + players.get(1).getName();
        currentPlayer = playerNames.replace(currentPlayer, "");
    }

    public void increaseNumberOfGames(String winner, Player player1, Player player2){
        if (winner.equals(player1.getName())){
            player1.increaseNumberOfGames();
            player1.increaseNumberOfWins();
            player2.increaseNumberOfGames();
        }else if (winner.equals(player2.getName())){
            player2.increaseNumberOfGames();
            player2.increaseNumberOfWins();
            player1.increaseNumberOfGames();
        }else {
            player1.increaseNumberOfGames();
            player2.increaseNumberOfGames();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getBoardState() {
        return boardState;
    }

    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
