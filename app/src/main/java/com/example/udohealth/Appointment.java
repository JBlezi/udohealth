package com.example.udohealth;

public class Appointment {
    private long id;
    private long userId;
    private long doctorId;
    private String date;
    private String time;
    private String diagnosis;
    private String reason;

    // Constructor, getters, and setters


    public Appointment(long userId, long doctorId, String date, String time, String diagnosis, String reason) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.diagnosis = diagnosis;
        this.reason = reason;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

