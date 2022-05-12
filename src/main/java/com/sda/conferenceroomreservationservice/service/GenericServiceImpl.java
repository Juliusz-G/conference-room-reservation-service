package com.sda.conferenceroomreservationservice.service;

import com.sda.conferenceroomreservationservice.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public abstract class GenericServiceImpl<T> implements GenericService<T> {

    private final GenericRepository<T> repository;

    @Autowired
    public GenericServiceImpl(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(final T entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(final T entity) {
        repository.delete(entity);
    }

    @Override
    public void removeById(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public T update(final T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> getById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<T> getByName(final String name) {
        return repository.findByName(name);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
