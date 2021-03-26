package org.example.RestAPI.repository;

import org.example.RestAPI.model.TypeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRecordRepository extends JpaRepository<TypeRecord, Long> {
}
