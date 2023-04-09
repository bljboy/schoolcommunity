package com.bljboy.schoolcommunity.model;

import java.util.List;

public class ForumMyDataList {
    private int code;
    private List<ForumMyData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ForumMyData> getData() {
        return data;
    }

    public void setData(List<ForumMyData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
