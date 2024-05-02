package com.timetracker.timetracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.services.CheckInService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CheckInController {
    
    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/checkin/{userId}")
    public CheckIn createCheckIn(@PathVariable String userId, @RequestBody CheckIn checkIn) {
        return checkInService.createCheckIn(userId, checkIn);
    }
    
}
