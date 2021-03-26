package org.example.RestAPI.service;

import org.example.RestAPI.model.TypeRecord;

import java.util.Optional;

public interface ITypeRecordService {
    Optional<TypeRecord> findById(long id);
}
