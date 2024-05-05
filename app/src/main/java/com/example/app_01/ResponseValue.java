package com.example.app_01;

import com.google.gson.annotations.SerializedName;

public class ResponseValue {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
