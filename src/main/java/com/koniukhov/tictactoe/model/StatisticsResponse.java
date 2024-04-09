package com.koniukhov.tictactoe.model;

public class StatisticsResponse {
    private String name;
    private int numberOfGames;
    private int numberOfWins;

    public StatisticsResponse(String name, int numberOfGames, int numberOfWins) {
        this.name = name;
        this.numberOfGames = numberOfGames;
        this.numberOfWins = numberOfWins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
}
