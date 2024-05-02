package com.timetracker.timetracker.services;

import com.timetracker.timetracker.models.CheckIn;
import com.timetracker.timetracker.models.User;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
            checkIn.setCheckInTime(LocalDateTime.now()); 
            mongoOperations.save(checkIn);
            return checkIn;
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
