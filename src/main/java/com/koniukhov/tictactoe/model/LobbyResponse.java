package com.koniukhov.tictactoe.model;

public class LobbyResponse {
    public enum Status {
        WAITING, FOUND
    }

    private Status status;
    private String playerName;
    private String enemyName;
    private PreGameResponse preGameResponse;

    public LobbyResponse() {
    }

    public LobbyResponse(Status status) {
        this.status = status;
    }

    public LobbyResponse(Status status, String playerName, String enemyName, PreGameResponse preGameResponse) {
        this.status = status;
        this.playerName = playerName;
        this.enemyName = enemyName;
        this.preGameResponse = preGameResponse;
    }

    public Status getStatus() {
        return status;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public PreGameResponse getPreGameResponse() {
        return preGameResponse;
    }

    public void setPreGameResponse(PreGameResponse preGameResponse) {
        this.preGameResponse = preGameResponse;
    }
}
