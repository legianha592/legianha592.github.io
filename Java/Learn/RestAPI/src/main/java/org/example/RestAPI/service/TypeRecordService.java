package org.example.RestAPI.service;

import org.example.RestAPI.model.TypeRecord;
import org.example.RestAPI.repository.TypeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeRecordService implements ITypeRecordService{
    @Autowired
    TypeRecordRepository typeRecordRepository;

    @Override
    public Optional<TypeRecord> findById(long id) {
        return typeRecordRepository.findById(id);
    }

    @Override
    public Optional<TypeRecord> findByTypeRecord_name(String typeRecord_name){
        return typeRecordRepository.findByTypeRecord_name(typeRecord_name);
    }

    @Override
    public void addTypeRecord(TypeRecord typerecord){
        typeRecordRepository.saveAndFlush(typerecord);
    }
}