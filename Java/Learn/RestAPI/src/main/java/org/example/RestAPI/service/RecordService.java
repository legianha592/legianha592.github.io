package org.example.RestAPI.service;

import org.example.RestAPI.model.Record;
import org.example.RestAPI.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService implements IRecordService{
    @Autowired
    RecordRepository recordRepository;

    @Override
    public void addRecord(Record record) {
        recordRepository.saveAndFlush(record);
    }
}
