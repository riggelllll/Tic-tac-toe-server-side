package com.koniukhov.tictactoe.model;

public class BaseRequest {
    private int playerId;

    public BaseRequest() {
    }

    public BaseRequest(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
