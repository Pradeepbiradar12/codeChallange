package com.practice.monitoringBackend.Controllers;

import com.practice.monitoringBackend.entities.ResponseEntity;
import com.practice.monitoringBackend.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class ResponseController {

    @Autowired
    ApiService apiService;

    @CrossOrigin
    @GetMapping("/get-response")
    public ResponseEntity getResponseTime() throws IOException {
        return apiService.getResponses();
    }

    @CrossOrigin
    @GetMapping("/get-all-response")
    public List<ResponseEntity> getAll() {
        return apiService.getAllResponseTime();
    }
}
