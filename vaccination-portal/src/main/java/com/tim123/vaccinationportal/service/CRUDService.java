package com.tim123.vaccinationportal.service;

import java.util.Optional;

public interface CRUDService<T> {
    T save(T entity) throws Exception;
    Optional<T> findById(String id) throws Exception;
    T update(T entity);
    void delete(String id);
}
