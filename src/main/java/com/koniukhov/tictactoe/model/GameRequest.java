package com.koniukhov.tictactoe.model;

public class GameRequest {
    private int playerId;
    private int cellIndex;

    public GameRequest() {
    }

    public GameRequest(int playerId, int cellIndex) {
        this.playerId = playerId;
        this.cellIndex = cellIndex;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getCellIndex() {
        return cellIndex;
    }
}
