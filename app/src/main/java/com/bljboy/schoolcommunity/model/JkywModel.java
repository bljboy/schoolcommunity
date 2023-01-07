package com.bljboy.schoolcommunity.model;

public class JkywModel {
    private String jxutnews_timemonth;
    private String jxutnews_timeyear;
    private String jxutnews_title;
    private String jxutnews_content;
    private String jxutnews_url;


    public JkywModel(String jxutnews_timemonth, String jxutnews_timeyear, String jxutnews_title, String jxutnews_content, String jxutnews_url) {
        this.jxutnews_timemonth = jxutnews_timemonth;
        this.jxutnews_timeyear = jxutnews_timeyear;
        this.jxutnews_title = jxutnews_title;
        this.jxutnews_content = jxutnews_content;
        this.jxutnews_url = jxutnews_url;
    }

    public String getJxutnews_url() {
        return jxutnews_url;
    }

    public void setJxutnews_url(String jxutnews_url) {
        this.jxutnews_url = jxutnews_url;
    }

    public String getJxutnews_timemonth() {
        return jxutnews_timemonth;
    }

    public void setJxutnews_timemonth(String jxutnews_timemonth) {
        this.jxutnews_timemonth = jxutnews_timemonth;
    }

    public String getJxutnews_timeyear() {
        return jxutnews_timeyear;
    }

    public void setJxutnews_timeyear(String jxutnews_timeyear) {
        this.jxutnews_timeyear = jxutnews_timeyear;
    }

    public String getJxutnews_title() {
        return jxutnews_title;
    }

    public void setJxutnews_title(String jxutnews_title) {
        this.jxutnews_title = jxutnews_title;
    }

    public String getJxutnews_content() {
        return jxutnews_content;
    }

    public void setJxutnews_content(String jxutnews_content) {
        this.jxutnews_content = jxutnews_content;
    }


}
