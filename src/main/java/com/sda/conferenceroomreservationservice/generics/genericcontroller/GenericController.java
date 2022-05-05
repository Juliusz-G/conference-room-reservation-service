package com.sda.conferenceroomreservationservice.generics.genericcontroller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericController<T> {

    ResponseEntity<T> create(T entity);

    void remove(T entity);

    void removeById(Long id);

    ResponseEntity<T> update(T entity);

    ResponseEntity<Optional<T>> getById(Long id);

    ResponseEntity<Optional<T>> getByName(String name);

    ResponseEntity<List<T>> getAll();
}
