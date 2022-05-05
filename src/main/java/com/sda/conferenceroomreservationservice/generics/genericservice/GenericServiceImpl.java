package com.sda.conferenceroomreservationservice.generics.genericservice;

import com.sda.conferenceroomreservationservice.generics.genericrepository.GenericRepository;
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
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<T> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
