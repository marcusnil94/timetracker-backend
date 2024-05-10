package com.timetracker.timetracker.models;

import java.util.Map;

public class Stats {
    private String weekStartDate;
    private String weekEndDate;
    private Map<String, CategoryStats> stats; 

    public Stats() {
    }    

    public Stats(String weekStartDate, String weekEndDate, Map<String, CategoryStats> stats) {
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.stats = stats;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public Map<String, CategoryStats> getStats() {
        return stats;
    }

    public void setStats(Map<String, CategoryStats> stats) {
        this.stats = stats;
    }

    
}