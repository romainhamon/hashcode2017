package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class RequestDescription {

    private Long nbRequest;

    private Video video;

    private Endpoint endpoint;

}
