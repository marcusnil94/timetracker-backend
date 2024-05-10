package com.timetracker.timetracker.models;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CheckIns")
public class CheckIn {
    @Id   
    private String id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String category;
    private long hours;
    private long minutes;
    
    @DBRef
    private User user;

    public CheckIn(String id, String category, LocalDateTime checkInTime, User user) {
        this.id = id;
        this.category = category;
        this.checkInTime = checkInTime;
        this.user = user;
    }

    private void calculateTimeWorked() {
        if (checkInTime != null && checkOutTime != null) {
            Duration duration = Duration.between(checkInTime, checkOutTime);
            this.hours = duration.toHours();
            this.minutes = duration.toMinutes() % 60;
        } else {
            throw new RuntimeException("Not found");
        }
    }

    

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
        calculateTimeWorked();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
