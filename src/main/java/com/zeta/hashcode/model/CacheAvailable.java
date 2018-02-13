package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheAvailable {

    private Long latency;

    private Cache cache;

}
