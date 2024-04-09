package com.koniukhov.tictactoe.model;

public class RegistrationRequest {
    private String androidId;
    private String name;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String androidId, String name) {
        this.androidId = androidId;
        this.name = name;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String getName() {
        return name;
    }
}
