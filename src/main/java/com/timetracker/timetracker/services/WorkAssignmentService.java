package com.timetracker.timetracker.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.timetracker.timetracker.models.WorkAssignment;

@Service
public class WorkAssignmentService {
    private final MongoOperations mongoOperations;

    public WorkAssignmentService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
    public WorkAssignment addWorkAssignment(WorkAssignment workAssignment) {
        return mongoOperations.insert(workAssignment);
    }

    public List<WorkAssignment> getWorkAssignments() {
        return mongoOperations.findAll(WorkAssignment.class);
    }
}
