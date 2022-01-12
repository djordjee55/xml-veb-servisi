package com.tim123.vaccinationmain.service.impl;

import com.tim123.vaccinationmain.repository.CRUDRepository;
import com.tim123.vaccinationmain.service.CRUDService;

import java.util.Optional;

public abstract class CRUDServiceImpl<T> implements CRUDService<T> {

    protected abstract CRUDRepository<T> getRepository();

    @Override
    public T save(T entity) throws Exception {
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> findById(String id) {
        try {
            return Optional.of(getRepository().findById(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
