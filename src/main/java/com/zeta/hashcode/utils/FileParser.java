package com.zeta.hashcode.utils;

import com.zeta.hashcode.model.*;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Log4j2
public final class FileParser {

    private static final List<String> FILES_NAMES_LIST = Arrays.asList("example.in", "kittens.in.txt", "me_at_the_zoo.in", "trending_today.in", "videos_worth_spreading.in");

    public static List<RequestDescription> readInputDataSet() {

        List<RequestDescription> listRequestDescriptions = new ArrayList<>();
        List<Cache> listCaches = new ArrayList<>();
        List<Video> listVideos = new ArrayList<>();
        List<Endpoint> listEndpoints = new ArrayList<>();

        File file = new File(FileParser.class.getClassLoader().getResource("input/" + FILES_NAMES_LIST.get(1)).getFile());

        try (Scanner scanner = new Scanner(file)) {

            Integer lineNumber = 0;
            Integer nbConnectedCachesEndpoint = 0;
            Integer endpointNumber = 0;

            while (scanner.hasNext()) {

                String[] parametres = FileParser.getLineParameters(scanner.nextLine());

                if (lineNumber == 0) {

                    Long nbVideos = Long.valueOf(parametres[0]);
                    Long nbEndpoints = Long.valueOf(parametres[1]);
                    Long nbRequestsDescription = Long.valueOf(parametres[2]);
                    Integer nbCaches = Integer.valueOf(parametres[3]);
                    Long sizeCaches = Long.valueOf(parametres[4]);

                    log.debug("{} videos, {} endpoints, {} request descriptions, {} caches {}MB each.", nbVideos, nbEndpoints, nbRequestsDescription, nbConnectedCachesEndpoint, sizeCaches);

                    Constants.getInstance().initValues(nbVideos, nbEndpoints, nbRequestsDescription, Long.valueOf(nbCaches), sizeCaches);

                    IntStream.range(0, nbCaches).forEach(
                            cacheNumber -> listCaches.add(new Cache(Long.valueOf(cacheNumber), sizeCaches))
                    );

                } else if (lineNumber == 1) {
                    IntStream.range(0, parametres.length).forEach(
                            index -> listVideos.add(new Video(Long.valueOf(index), Long.valueOf(parametres[index])))
                    );
                } else if (parametres.length == 2 && nbConnectedCachesEndpoint == 0) {

                    Long dcLatency = Long.valueOf(parametres[0]);
                    nbConnectedCachesEndpoint = Integer.valueOf(parametres[1]);

                    listEndpoints.add(new Endpoint(Long.valueOf(endpointNumber), dcLatency));

                    log.debug("Endpoint {} has {}ms datacenter latency and is connected to {} caches", endpointNumber, dcLatency, nbConnectedCachesEndpoint);

                    endpointNumber++;

                } else if (parametres.length == 2 && nbConnectedCachesEndpoint != 0) {

                    Integer cacheNumber = Integer.valueOf(parametres[0]);
                    Long latency = Long.valueOf(parametres[1]);

                    Cache cacheEndpoint = listCaches.get(cacheNumber);

                    listEndpoints.get(endpointNumber - 1).getCacheAvailableList().add(new CacheAvailable(latency, cacheEndpoint));

                    log.debug("The latency (of endpoint {}) to cache {} is {}ms", endpointNumber - 1, cacheNumber, latency);

                    nbConnectedCachesEndpoint--;

                } else if (parametres.length == 3) {

                    Long nbRequest = Long.valueOf(parametres[2]);
                    Integer idVideo = Integer.valueOf(parametres[0]);
                    Integer idEndpoint = Integer.valueOf(parametres[1]);

                    Video video = listVideos.get(idVideo);
                    Endpoint endpoint = listEndpoints.get(idEndpoint);

                    listRequestDescriptions.add(new RequestDescription(nbRequest, video, endpoint));

                    log.debug("{} requests for video {} coming from endpoint {}", nbRequest, idVideo, idEndpoint);

                }

                lineNumber++;

            }
        } catch (IOException e) {
            log.error(e);
        }

        log.debug(listRequestDescriptions.toString());
        log.debug(Constants.getInstance().toString());

        return listRequestDescriptions;

    }

    private static String[] getLineParameters(String line) {
        return line.split("\\s+");
    }

}
