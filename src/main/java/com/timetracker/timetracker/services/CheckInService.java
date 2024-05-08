package com.timetracker.timetracker.services;

import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.models.User;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    private final MongoOperations mongoOperations;
    private final UserService userService; 

    @Autowired
    public CheckInService(MongoOperations mongoOperations, UserService userService) {
        this.mongoOperations = mongoOperations;
        this.userService = userService;
    }

    public CheckIn createCheckIn(String userId, CheckIn checkIn) {
        User user = userService.getUserById(userId);
        if (user != null) {
            checkIn.setUser(user);
            
            LocalDateTime adjustedCheckInTime = LocalDateTime.now().plusHours(2);
            checkIn.setCheckInTime(adjustedCheckInTime);
            mongoOperations.save(checkIn);
            return checkIn;
        } else {
            throw new RuntimeException("User not found");
        }
    }
    
    public CheckIn checkout(String checkInId) {
        CheckIn checkIn = mongoOperations.findById(checkInId, CheckIn.class);
        if (checkIn != null) {
            
            LocalDateTime adjustedCheckOutTime = LocalDateTime.now().plusHours(2);
            checkIn.setCheckOutTime(adjustedCheckOutTime);
            mongoOperations.save(checkIn);
            return checkIn;
        } else {
            throw new RuntimeException("Check-in not found");
        }
    }

    public List<CheckIn> getCheckIns() {
        return mongoOperations.findAll(CheckIn.class);
    }

     public List<CheckIn> getCheckInsByUserId(String userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return mongoOperations.find(Query.query(Criteria.where("user").is(user)), CheckIn.class);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
