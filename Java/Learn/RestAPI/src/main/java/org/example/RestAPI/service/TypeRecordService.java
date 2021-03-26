package org.example.RestAPI.service;

import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.repository.TypeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeRecordService implements ITypeRecordService{
    @Autowired
    TypeRecordRepository typerecordRepository;

    @Override
    public Optional<TypeRecord> findById(long id) {
        return typerecordRepository.findById(id);
    }
}