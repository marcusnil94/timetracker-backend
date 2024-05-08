package com.timetracker.timetracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.services.CheckInService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
public class CheckInController {
    
    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @GetMapping("/checkins")
    public List<CheckIn> getCheckIns() {
        return checkInService.getCheckIns();
    }
    
    @GetMapping("/checkins/{userId}")
    public List<CheckIn> getCheckInsByUserId(@PathVariable String userId) {
        return checkInService.getCheckInsByUserId(userId);
    }
    

    @PostMapping("/checkin/{userId}")
    public CheckIn createCheckIn(@PathVariable String userId, @RequestBody CheckIn checkIn) {
        return checkInService.createCheckIn(userId, checkIn);
    }

    @PatchMapping("/checkout/{checkInId}")
    public ResponseEntity<CheckIn> checkout(@PathVariable String checkInId) {
        CheckIn checkedOutCheckIn = checkInService.checkout(checkInId);
        return new ResponseEntity<>(checkedOutCheckIn, HttpStatus.OK);
    }
    
}
