package com.timetracker.timetracker.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public User getUserById(String userId) {
        return mongoOperations.findById(userId, User.class);
    }

   public User getUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoOperations.findOne(query, User.class);
    }
}
