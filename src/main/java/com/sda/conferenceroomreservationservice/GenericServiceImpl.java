package com.sda.conferenceroomreservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericServiceImpl<T> implements GenericService<T> {

    private final GenericRepository<T> repository;

    @Autowired
    public GenericServiceImpl(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(T entity) {
        repository.delete(entity);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public T getById(Long id) {
        return (T) repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
