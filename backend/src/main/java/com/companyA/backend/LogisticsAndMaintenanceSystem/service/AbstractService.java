package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import java.util.List;

public abstract class AbstractService<T> {
    public abstract List<T> findAll();

    public abstract void add(T obj);
}
