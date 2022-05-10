package com.sda.conferenceroomreservationservice.controller;

import com.sda.conferenceroomreservationservice.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// @CrossOrigin(origins = "http://localhost:4200") can be used instead of WebConfiguration file...

public abstract class GenericControllerImpl<T> implements GenericController<T> {

    private final GenericServiceImpl<T> service;

    @Autowired
    public GenericControllerImpl(GenericServiceImpl<T> service) {
        this.service = service;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<T> create(@Valid T entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<T> remove(@Valid T entity) {
        service.remove(entity);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<T> removeById(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<T> update(@Valid T entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @Override
    @GetMapping("/get/id/{id}")
    public ResponseEntity<Optional<T>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping("/get/name/{name}")
    public ResponseEntity<Optional<T>> getByName(@Valid @PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @Override
    @GetMapping("/get/all")
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
