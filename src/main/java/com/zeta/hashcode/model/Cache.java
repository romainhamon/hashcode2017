package com.zeta.hashcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cache {

    private Long id;

    private List<Video> videos;

    public boolean canAccept(Video video){
        return this.videos.stream().mapToLong(Video::getSize).sum() + video.getSize() < 100;
    }

}
