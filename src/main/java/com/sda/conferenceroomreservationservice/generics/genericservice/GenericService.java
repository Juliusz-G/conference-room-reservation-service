package com.sda.conferenceroomreservationservice.generics.genericservice;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    T create(T entity);

    void remove(T entity);

    void removeById(Long id);

    T update(T entity);

    Optional<T> getById(Long id);

    Optional<T> getByName(String name);

    List<T> getAll();
}
