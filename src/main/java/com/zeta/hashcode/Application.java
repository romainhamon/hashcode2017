package com.zeta.hashcode;

import com.zeta.hashcode.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {


        List<Video> videos = new ArrayList<>();
        videos.add(new Video(0L, 50L));
        videos.add(new Video(1L, 50L));
        videos.add(new Video(2L, 80L));
        videos.add(new Video(3L, 30L));
        videos.add(new Video(4L, 110L));

        List<Cache> caches = new ArrayList<>();
        caches.add(new Cache(0L, new ArrayList<>()));
        caches.add(new Cache(1L, new ArrayList<>()));
        caches.add(new Cache(2L, new ArrayList<>()));

        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(new Endpoint(0L, 1000L));
        endpoints.add(new Endpoint(1L, 500L));

        List<RequestDescription> rdList = new ArrayList<>();
        RequestDescription rd = new RequestDescription(1500L, videos.get(3), endpoints.get(0), new ArrayList<>());
        rd.getListCacheAvailable().add(new CacheAvailable(100L, caches.get(0)));
        rd.getListCacheAvailable().add(new CacheAvailable(200L, caches.get(2)));
        rd.getListCacheAvailable().add(new CacheAvailable(300L, caches.get(1)));
        rdList.add(rd);

        rd = new RequestDescription(1000L, videos.get(0), endpoints.get(1), new ArrayList<>());
        rdList.add(rd);

        rd = new RequestDescription(500L, videos.get(4), endpoints.get(0), new ArrayList<>());
        rd.getListCacheAvailable().add(new CacheAvailable(100L, caches.get(0)));
        rd.getListCacheAvailable().add(new CacheAvailable(200L, caches.get(2)));
        rd.getListCacheAvailable().add(new CacheAvailable(300L, caches.get(1)));
        rdList.add(rd);

        rd = new RequestDescription(1000L, videos.get(1), endpoints.get(0), new ArrayList<>());
        rd.getListCacheAvailable().add(new CacheAvailable(100L, caches.get(0)));
        rd.getListCacheAvailable().add(new CacheAvailable(200L, caches.get(2)));
        rd.getListCacheAvailable().add(new CacheAvailable(300L, caches.get(1)));
        rdList.add(rd);

        rdList.stream().sorted(Comparator.comparingLong(RequestDescription::getScore)).forEach(rdb -> {
             rdb.getListCacheAvailable().stream().sorted(Comparator.comparingLong(CacheAvailable::getLatency))
                    .forEach(cacheAvailable -> {
                        if (cacheAvailable.getCache().canAccept(rdb.getVideo())){
                            cacheAvailable.getCache().getVideos().add(rdb.getVideo());
                            System.out.println("Video " + rdb.getVideo().getId() + " added to cache " + cacheAvailable.getCache().getId());
                            System.out.println("CacheSize: " + cacheAvailable.getCache().getId());
                        }
                    }
            );
        });

        caches.forEach(cache -> {
            System.out.print("Cache server  " + cache.getId() + " contains ");

            cache.getVideos().forEach(vid -> {
                System.out.print("videos " + vid.getId() + " and ");
            });

            System.out.print("\n");
        });

        System.out.println(caches.size());
        caches.forEach(cache -> {
            System.out.print(cache.getId());

            cache.getVideos().forEach(vid -> {
                System.out.print(" " + vid.getId());
            });

            System.out.print("\n");
        });



    }
}
