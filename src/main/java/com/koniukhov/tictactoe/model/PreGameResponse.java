package com.koniukhov.tictactoe.model;

public class PreGameResponse {

    private String currentPlayer;
    private boolean canMove;

    public PreGameResponse() {
    }

    public PreGameResponse(String currentPlayer, boolean canMove) {
        this.currentPlayer = currentPlayer;
        this.canMove = canMove;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
