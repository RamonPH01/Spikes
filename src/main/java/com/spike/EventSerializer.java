package com.spike;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class EventSerializer {

    // Assuming Event class and eventRepo are defined somewhere


    public String getAllEventsAsJson(List<Event> events) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(events);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle the exception accordingly
        }
    }
}
