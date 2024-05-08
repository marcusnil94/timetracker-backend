package com.timetracker.timetracker.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.models.TimeReport;

@Service
public class TimeReportService {

    private final MongoOperations mongoOperations;
    

    public TimeReportService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<TimeReport> getAllTimeReports() {
        return mongoOperations.findAll(TimeReport.class);
    }


    public void createTimeReport(String checkInId) {
        CheckIn checkIn = mongoOperations.findById(checkInId, CheckIn.class);
        if (checkIn != null) {
            LocalDateTime checkInTime = checkIn.getCheckInTime();
            LocalDateTime checkOutTime = checkIn.getCheckOutTime();

            if (checkOutTime != null) {
                Duration duration = Duration.between(checkInTime, checkOutTime);
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;

                TimeReport timeReport = new TimeReport(checkInId, hours, minutes);
                mongoOperations.save(timeReport);
            } else {
                throw new RuntimeException("Check-out time is not available for the check-in with ID: " + checkInId);
            }
        } else {
            throw new RuntimeException("Check-in with ID: " + checkInId + " not found");
        }
    }

    
}
