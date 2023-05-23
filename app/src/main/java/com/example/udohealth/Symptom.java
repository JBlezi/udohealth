package com.example.udohealth;

public class Symptom {
    private long id;
    private long userId;
    private String name;
    private String start_time;
    private String description;
    private String duration;

    // Constructor, getters, and setters


    public Symptom(long userId, String name, String description, String start_time, String duration) {
        this.userId = userId;
        this.name = name;
        this.start_time = start_time;
        this.duration = duration;
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}

