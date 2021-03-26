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

    @Override
    public Optional<TypeRecord> findByTyperecord_name(String typerecord_name){
        return typerecordRepository.findByTyperecord_name(typerecord_name);
    }

    @Override
    public void addTyperecord(TypeRecord typerecord){
        typerecordRepository.saveAndFlush(typerecord);
    }
}