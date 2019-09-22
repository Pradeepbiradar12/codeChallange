package com.practice.monitoringBackend.service.impl;

import com.practice.monitoringBackend.ApiGateWay.IntuitService;
import com.practice.monitoringBackend.ApiGateWay.WikiServie;
import com.practice.monitoringBackend.entities.ResponseEntity;
import com.practice.monitoringBackend.repositories.ResponseRepository;
import com.practice.monitoringBackend.service.ApiService;

import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.util.*;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    IntuitService intuitService;

    @Autowired
    WikiServie wikiServie;

    @Autowired
    ResponseRepository responseRepository;

    @Override
    public ResponseEntity getResponses() throws IOException {
        Call<ResponseBody> responseCall = intuitService.handShake();

        Response response = responseCall.clone().execute();
        long rx = response.raw().receivedResponseAtMillis();
        long tx = response.raw().sentRequestAtMillis();

        Call<ResponseBody> wikiResponseCall = wikiServie.handShake();
        Response wikiResponse = wikiResponseCall.clone().execute();
        long wikix = wikiResponse.raw().receivedResponseAtMillis();
        long wikitx = wikiResponse.raw().sentRequestAtMillis();

        ResponseEntity responseDto = new ResponseEntity();
        responseDto.setIntuitResponseTime(rx - tx);
        responseDto.setWikiResponseTime(wikix - wikitx);
        responseDto.setRequestedTime(new Date());

        return responseRepository.save(responseDto);
    }

    @Override
    public List<ResponseEntity> getAllResponseTime() {
        return responseRepository.findAll();
    }
}
