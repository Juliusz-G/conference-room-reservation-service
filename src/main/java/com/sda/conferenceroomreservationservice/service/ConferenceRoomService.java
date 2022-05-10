package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationservice.repository.ConferenceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service marks bean to indicate that they're holding the business logic
@Service
public class ConferenceRoomService extends GenericServiceImpl<ConferenceRoom> {

    @Autowired
    public ConferenceRoomService(final ConferenceRoomRepository repository) {
        super(repository);
    }

    @Override
    public ConferenceRoom create(final ConferenceRoom entity) {
        return super.create(entity);
    }

    @Override
    public void remove(final ConferenceRoom entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(final Long id) {
        super.removeById(id);
    }

    @Override
    public ConferenceRoom update(final ConferenceRoom entity) {
        return super.update(entity);
    }

    @Override
    public Optional<ConferenceRoom> getById(final Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<ConferenceRoom> getByName(final String name) {
        return super.getByName(name);
    }

    @Override
    public List<ConferenceRoom> getAll() {
        return super.getAll();
    }
}
