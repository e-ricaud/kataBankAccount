package com.ericaud.kata.bankaccount.controller.model;

public class JSONResponse {

    private String code;

    private String content;

    private String message;

    public JSONResponse(String code) {
        this.code = code;
    }

    public JSONResponse(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public JSONResponse(String code, String content, String message) {
        this.code = code;
        this.content = content;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}