package com.bljboy.schoolcommunity.model;

public class ChatMessageData {

    public static final int TYPE_USER = 0;
    public static final int TYPE_OTHER = 1;

    public ChatMessageData(String message, long time, int type, String name) {
        this.message = message;
        this.time = time;
        this.type = type;
        this.name = name;
    }

    public ChatMessageData(String message, String name, int type) {
        this.message = message;
        this.name = name;
        this.type = type;

    }

    private String message;
    private long time;
    private int type;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ChatMessageData(String message, long time, int type) {
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
