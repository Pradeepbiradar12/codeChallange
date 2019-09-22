package com.practice.monitoringBackend.service;

import com.practice.monitoringBackend.entities.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ApiService {
    ResponseEntity getResponses() throws IOException;

    List<ResponseEntity> getAllResponseTime();
}
