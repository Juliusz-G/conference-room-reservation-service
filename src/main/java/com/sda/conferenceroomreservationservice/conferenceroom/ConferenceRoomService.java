package com.sda.conferenceroomreservationservice.conferenceroom;

import com.sda.conferenceroomreservationservice.generics.genericservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceRoomService extends GenericServiceImpl<ConferenceRoom> {

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository repository) {
        super(repository);
    }

    @Override
    public ConferenceRoom create(ConferenceRoom entity) {
        return super.create(entity);
    }

    @Override
    public void remove(ConferenceRoom entity) {
        super.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        super.removeById(id);
    }

    @Override
    public ConferenceRoom update(ConferenceRoom entity) {
        return super.update(entity);
    }

    @Override
    public Optional<ConferenceRoom> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Optional<ConferenceRoom> getByName(String name) {
        return super.getByName(name);
    }

    @Override
    public List<ConferenceRoom> getAll() {
        return super.getAll();
    }
}
