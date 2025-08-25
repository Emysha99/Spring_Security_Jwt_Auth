package com.example.Auth_demo.payload.response;

public class MessageResponse {
    private String message;
    public MessageResponse(String m){
        this.message = m;
    }
    public String getMessage(){
        return message;
    }
}
