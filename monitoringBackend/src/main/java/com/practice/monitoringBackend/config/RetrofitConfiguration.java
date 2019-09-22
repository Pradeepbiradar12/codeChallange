package com.practice.monitoringBackend.config;

import com.practice.monitoringBackend.ApiGateWay.IntuitService;
import com.practice.monitoringBackend.ApiGateWay.WikiServie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public IntuitService getIntuitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.intuit.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IntuitService.class);
    }

    @Bean
    public WikiServie getWikiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" https://en.wikipedia.org/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(WikiServie.class);
    }
}

