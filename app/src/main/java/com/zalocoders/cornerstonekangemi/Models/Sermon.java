package com.zalocoders.cornerstonekangemi.Models;

public class Sermon {

    String date;
    String Topic;
    String description;
    String audoUrl;
    String pastor;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudoUrl() {
        return audoUrl;
    }

    public void setAudoUrl(String audoUrl) {
        this.audoUrl = audoUrl;
    }

    public String getPastor() {
        return pastor;
    }

    public void setPastor(String pastor) {
        this.pastor = pastor;
    }
}
