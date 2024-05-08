package com.timetracker.timetracker.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TimeReports")
public class TimeReport {
    private String checkInId;
    private long hours;
    private long minutes;

    public TimeReport(String checkInId, long hours, long minutes) {
        this.checkInId = checkInId;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(String checkInId) {
        this.checkInId = checkInId;
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

   
}

