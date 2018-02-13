package com.zeta.hashcode.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestDescription {

    private Long id;

    private Long nbRequest;

    private Video video;

    private Endpoint endpoint;

    private List<CacheAvailable> listCacheAvailable;

}
