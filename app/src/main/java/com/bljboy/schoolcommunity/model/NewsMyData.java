package com.bljboy.schoolcommunity.model;

public class NewsMyData {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsMyData(String time, String pic, String url, String content, String title) {
        this.time = time;
        this.pic = pic;
        this.url = url;
        this.content = content;
        this.title = title;
    }

    private String time;
    private String pic;
    private String url;
    private String content;
    private String title;

}
