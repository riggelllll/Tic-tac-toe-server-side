package com.koniukhov.tictactoe.model;

public class GameResponse {
    public enum Status{
        IN_PROGRESS, FINISHED
    }
    private Status status;
    private int cellIndex;
    private char symbol;
    private String currentPlayer;
    private boolean canMove;
    private String winnerMsg;

    public GameResponse(Status status, int cellIndex, char symbol, String currentPlayer, boolean canMove) {
        this.status = status;
        this.cellIndex = cellIndex;
        this.symbol = symbol;
        this.currentPlayer = currentPlayer;
        this.canMove = canMove;
    }

    public GameResponse(Status status, int cellIndex, char symbol, String winnerMsg) {
        this.status = status;
        this.cellIndex = cellIndex;
        this.symbol = symbol;
        this.winnerMsg = winnerMsg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public String getWinnerMsg() {
        return winnerMsg;
    }

    public void setWinnerMsg(String winnerMsg) {
        this.winnerMsg = winnerMsg;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
