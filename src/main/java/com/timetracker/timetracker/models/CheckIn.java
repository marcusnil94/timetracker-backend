package com.timetracker.timetracker.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CheckIns")
public class CheckIn {
    @Id   
    private String id;
    private String checkInTime; 
}
