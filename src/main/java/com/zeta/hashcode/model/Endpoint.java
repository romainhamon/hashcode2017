package com.zeta.hashcode.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Endpoint {

    private Long id;

    private Long dcLatency;

    private List<CacheAvailable> cacheAvailableList;

    public Endpoint(Long id, Long dcLatency) {
        this.id = id;
        this.dcLatency = dcLatency;
        this.cacheAvailableList = new ArrayList<>();
    }

}
