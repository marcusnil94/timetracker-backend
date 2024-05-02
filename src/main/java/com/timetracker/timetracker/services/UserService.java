package com.timetracker.timetracker.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetracker.timetracker.models.User;

@Service
public class UserService {
    private final MongoOperations mongoOperations;

    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public User addUser(User user) {
        return mongoOperations.insert(user);
    }

    public List<User> getUsers() {
        return mongoOperations.findAll(User.class);
    }
}
