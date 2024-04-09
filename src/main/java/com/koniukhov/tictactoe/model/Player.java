package com.koniukhov.tictactoe.model;

import jakarta.persistence.*;

@Entity
public class Player {
    private static final String JOIN_COLUMN_NAME = "game_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numberOfGames = 0;
    private Integer numberOfWins = 0;
    private Boolean canMove;
    private char symbol;

    @ManyToOne
    @JoinColumn(name = JOIN_COLUMN_NAME)
    private Game game;

    public void reset(){
        symbol = Character.MIN_VALUE;
        canMove = false;
        game = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Integer getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(Integer numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void increaseNumberOfGames(){
        numberOfGames++;
    }

    public void increaseNumberOfWins(){
        numberOfWins++;
    }
}
