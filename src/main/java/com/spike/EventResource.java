package com.spike;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

@Path("/events")


@ApplicationScoped
public class EventResource{
    @Inject
    EventRepo eventRepo;

    public List<Event> getAllEvents() {
        return eventRepo.listAll();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEventsAsJson() {

        return eventRepo.listAll();
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces
    @Transactional
    public Response createEvent(String jsonBody) throws JsonProcessingException {
            ObjectMapper objectMapper =new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Event event =objectMapper.readValue(jsonBody, Event.class);
            eventRepo.persist(event);
            return Response.status(Response.Status.CREATED).entity(event).build();

        }

}