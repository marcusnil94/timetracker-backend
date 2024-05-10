package com.timetracker.timetracker.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.CategoryStats;
import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.models.Stats;
import com.timetracker.timetracker.services.CheckInService;

@RestController
public class StatsController {
    private final CheckInService checkInService;

    public StatsController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @GetMapping("/weekstats/{userId}")
    public List<Stats> getWeeklyStatsByUserId(@PathVariable String userId) {
        
        List<CheckIn> checkIns = checkInService.getCheckInsByUserId(userId);

        
        Map<String, List<CheckIn>> checkInsByWeek = checkIns.stream()
                .collect(Collectors.groupingBy(this::getWeekStartDate));

        
        return checkInsByWeek.entrySet().stream()
        .map(entry -> {
            String weekStartDate = entry.getKey();
            String weekEndDate = LocalDate.parse(weekStartDate).plusDays(6).toString();
            List<CheckIn> checkInsForWeek = entry.getValue();

            
            Map<String, CategoryStats> stats = checkInsForWeek.stream()
            .collect(Collectors.groupingBy(
                CheckIn::getCategory,
             Collectors.collectingAndThen(
            Collectors.summarizingLong(this::calculateDuration),
            summary -> new CategoryStats(summary.getSum() / 60, summary.getSum() % 60)
        )
    ));

            return new Stats(weekStartDate, weekEndDate, stats);
        })
        .collect(Collectors.toList());
    }
 
    private String getWeekStartDate(CheckIn checkIn) {
        return checkIn.getCheckInTime().toLocalDate().with(java.time.DayOfWeek.MONDAY).toString();
    }

    private long calculateDuration(CheckIn checkIn) {
        LocalDateTime checkInTime = checkIn.getCheckInTime();
        LocalDateTime checkOutTime = checkIn.getCheckOutTime();
        return Duration.between(checkInTime, checkOutTime).toMinutes();
    }
}
