package com.sda.conferenceroomreservationservice.generics.genericcontroller;

import com.sda.conferenceroomreservationservice.generics.genericservice.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
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
    public void remove(@Valid T entity) {
        service.remove(entity);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void removeById(@PathVariable Long id) {
        service.removeById(id);
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
