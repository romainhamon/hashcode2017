package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Constants {

    private static volatile Constants instance = null;

    private Long nbVideo;

    private Long nbEndpoint;

    private Long nbRequestDescription;

    private Long nbCache;

    private Long cacheSize;

    private Constants() {
        super();
    }

    public final static Constants getInstance() {
        if (Constants.instance == null) {
            synchronized(Constants.class) {
                if (Constants.instance == null) {
                    Constants.instance = new Constants();
                }
            }
        }
        return Constants.instance;
    }

    public void initValues(Long nbVideo, Long nbEndpoint, Long nbRequestDescription, Long nbCache, Long cacheSize) {
        this.nbVideo = nbVideo;
        this.nbEndpoint = nbEndpoint;
        this.nbRequestDescription = nbRequestDescription;
        this.nbCache = nbCache;
        this.cacheSize = cacheSize;
    }

}
