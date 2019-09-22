package com.practice.monitoringBackend.repositories;

import com.practice.monitoringBackend.entities.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity, Integer> {

}
