package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CacheAvailable {

    private Long latency;

    private Cache cache;

}
