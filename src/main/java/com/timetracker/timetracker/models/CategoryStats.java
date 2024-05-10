package com.timetracker.timetracker.models;

public class CategoryStats {
    private long totalHours;
    private long totalMinutes;

    public CategoryStats() {
    }

    public CategoryStats(long totalHours, long totalMinutes) {
        this.totalHours = totalHours;
        this.totalMinutes = totalMinutes;
    }

    public long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }

    public long getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(long totalMinutes) {
        this.totalMinutes = totalMinutes;
    }
}