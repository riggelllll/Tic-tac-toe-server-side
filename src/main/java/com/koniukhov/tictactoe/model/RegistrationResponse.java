package com.koniukhov.tictactoe.model;

public class RegistrationResponse {
    public enum Status{
        SUCCESS, ALREADY_EXISTS
    }

    public static final String SUCCESS_MSG = "Success";
    public static final String ALREADY_EXISTS_MSG = "Player already exists";

    private final Status status;
    private final String message;
    private int playerId;

    public RegistrationResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public RegistrationResponse(Status status, String message, int playerId) {
        this.status = status;
        this.message = message;
        this.playerId = playerId;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
