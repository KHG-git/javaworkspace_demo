package com.example.demo.dto;

public class TestOutputDTO {
    private String message;

    public TestOutputDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
