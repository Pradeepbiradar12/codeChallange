package com.practice.monitoringBackend.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "response_entity")
@Table(name = "response_entity")
public class ResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date requestedTime;
    private long wikiResponseTime;
    private long intuitResponseTime;

    public long getWikiResponseTime() {
        return wikiResponseTime;
    }

    public void setWikiResponseTime(long wikiResponseTime) {
        this.wikiResponseTime = wikiResponseTime;
    }

    public long getIntuitResponseTime() {
        return intuitResponseTime;
    }

    public void setIntuitResponseTime(long intuitResponseTime) {
        this.intuitResponseTime = intuitResponseTime;
    }

    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
