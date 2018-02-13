package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestDescription {

    private Long nbRequest;

    private Video video;

    private Endpoint endpoint;

    private List<CacheAvailable> listCacheAvailable;

    public Long getScore() {
        return this.endpoint.getDcLatency() * this.nbRequest;
    }

}
