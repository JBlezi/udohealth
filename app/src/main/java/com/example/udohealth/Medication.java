package com.example.udohealth;

public class Medication {
    private long id;
    private long userId;
    private long doctorId;
    private String name;
    private String consumption_time;
    private String description;
    private String dosage;
    private String brand_name;

    // Constructor, getters, and setters


    public Medication(long userId, long doctorId, String name, String description, String consumption_time, String dosage, String brand_name) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.name = name;
        this.consumption_time = consumption_time;
        this.dosage = dosage;
        this.brand_name = brand_name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brand_name;
    }

    public void setBrandName(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConsumptionTime() {
        return consumption_time;
    }

    public void setConsumption_time(String consumption_time) {
        this.consumption_time = consumption_time;
    }
}

