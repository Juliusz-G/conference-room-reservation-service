package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.service.ConferenceRoomService;
import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// Annotation in order to simplify the creation of RESTful web services.
// It's a convenient annotation that combines @Controller and @ResponseBody,
// which eliminates the need to annotate every request handling method of
// the controller class with the @ResponseBody annotation
@RestController
// the class-level annotation maps a specific request path or pattern onto a controller
@RequestMapping("/conference-room")
public class ConferenceRoomController extends GenericControllerImpl<ConferenceRoom> {

    public ConferenceRoomController(ConferenceRoomService service) {
        super(service);
    }

    @Override
    public ResponseEntity<ConferenceRoom> create(ConferenceRoom entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<ConferenceRoom> remove(ConferenceRoom entity) {
        super.remove(entity);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ConferenceRoom> removeById(Long id) {
        super.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ConferenceRoom> update(ConferenceRoom entity) {
        return super.update(entity);
    }

    @Override
    public ResponseEntity<Optional<ConferenceRoom>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<Optional<ConferenceRoom>> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public ResponseEntity<List<ConferenceRoom>> getAll() {
        return super.getAll();
    }
}
