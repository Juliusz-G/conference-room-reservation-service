package com.sda.conferenceroomreservationservice;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenericService<T> {

    T create(T entity);

    void remove(T entity);

    void removeById(Long id);

    void update(T entity);

    T getById(Long id);

    List<T> getAll();
}
