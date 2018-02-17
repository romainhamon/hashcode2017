package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Cache {

    private Long id;

    private Long freeSpace;

}
