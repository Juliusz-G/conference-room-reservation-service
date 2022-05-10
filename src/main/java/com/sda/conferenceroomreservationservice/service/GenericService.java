package com.sda.conferenceroomreservationservice.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    T create(final T entity);

    void remove(final T entity);

    void removeById(final Long id);

    T update(final T entity);

    Optional<T> getById(final Long id);

    Optional<T> getByName(final String name);

    List<T> getAll();
}
