package com.timetracker.timetracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.TimeReport;
import com.timetracker.timetracker.services.TimeReportService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class TimeReportController {
    
    private final TimeReportService timeReportService;

    @Autowired
    public TimeReportController(TimeReportService timeReportService) {
        this.timeReportService = timeReportService;
    }

    @PostMapping("/timereport/{checkInId}") 
    public void createTimeReport(@PathVariable String checkInId) {
        timeReportService.createTimeReport(checkInId);
    }

    @GetMapping("/timereports")
    public List<TimeReport> getAllTimeReports() {
        return timeReportService.getAllTimeReports();
    }
    
}
