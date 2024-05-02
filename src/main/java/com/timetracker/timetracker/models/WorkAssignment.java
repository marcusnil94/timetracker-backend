package com.timetracker.timetracker.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WorkAssignments")
public class WorkAssignment {
    private String id;
    private String assignmentName;
    
    public WorkAssignment(String id, String assignmentName) {
        this.id = id;
        this.assignmentName = assignmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
}
