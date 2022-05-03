package com.sda.conferenceroomreservationservice.conferenceroom;

import com.sda.conferenceroomreservationservice.GenericRepository;
import com.sda.conferenceroomreservationservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomService extends GenericServiceImpl<ConferenceRoom> {

    @Autowired
    public ConferenceRoomService(GenericRepository<ConferenceRoom> repository) {
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
    public void update(ConferenceRoom entity) {
        super.update(entity);
    }

    @Override
    public ConferenceRoom getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<ConferenceRoom> getAll() {
        return super.getAll();
    }
}
