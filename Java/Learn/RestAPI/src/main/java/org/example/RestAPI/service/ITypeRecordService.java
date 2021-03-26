package org.example.RestAPI.service;

import org.example.RestAPI.model.TypeRecord;

import java.lang.reflect.Type;
import java.util.Optional;

public interface ITypeRecordService {
    Optional<TypeRecord> findById(long id);

    Optional<TypeRecord> findByTyperecord_name(String typerecord_name);

    void addTyperecord(TypeRecord typeRecord);
}
