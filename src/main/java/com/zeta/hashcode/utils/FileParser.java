package com.zeta.hashcode.utils;

import com.zeta.hashcode.model.Constants;
import com.zeta.hashcode.model.Endpoint;
import com.zeta.hashcode.model.RequestDescription;
import com.zeta.hashcode.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public final class FileParser {

    private static final String FILE_NAME = "kittens.in.txt";

    public static List<RequestDescription> getListRequestDescriptionFromInputDataSet() {

        List<RequestDescription> listRequestDescriptions = new ArrayList<RequestDescription>();
        List<Video> listVideos = new ArrayList<Video>();
        List<Endpoint> listEndpoints = new ArrayList<Endpoint>();

        try {

            ClassLoader classLoader = FileParser.class.getClassLoader();
            File file = new File(classLoader.getResource("input/" + FILE_NAME).getFile());

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder out = new StringBuilder();
            String line;

            Integer lineNumber = 0;
            Integer nbCaches = 0;
            Integer endpointNumber = 0;

            while ((line = reader.readLine()) != null) {

                System.out.println("Lecture de la ligne " + lineNumber);

                String[] parametres = FileParser.getParametresLines(line);

                // Initialisation des constantes
                if (lineNumber == 0) {
                    initValues(parametres);
                // Initialisation des vidéos
                } else if (lineNumber == 1) {
                    for (Integer i = 0; i < parametres.length; i++) {
                        listVideos.add(new Video(Long.valueOf(i), Long.valueOf(parametres[i])));
                    }
                } else if (parametres.length == 2 && nbCaches == 0) {
                    listEndpoints.add(new Endpoint(Long.valueOf(endpointNumber), Long.valueOf(parametres[0])));
                    nbCaches = Integer.valueOf(parametres[1]);
                } else if (parametres.length == 2 && nbCaches != 0) {



                    nbCaches--;

                } else if (parametres.length == 3) {

                }

                lineNumber++;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listRequestDescriptions;

    }

    private static void initValues(String[] parametres) {

        Long nbVideos = Long.valueOf(parametres[0]);
        Long nbEndpoints = Long.valueOf(parametres[1]);
        Long nbRequestsDescription = Long.valueOf(parametres[2]);
        Long nbCaches = Long.valueOf(parametres[3]);
        Long sizeCaches =  Long.valueOf(parametres[4]);

        Constants.getInstance().initValues(nbVideos, nbEndpoints, nbRequestsDescription, nbCaches, sizeCaches);

    }

    private static String[] getParametresLines(String line) {
        return line.split("\\s+");
    }



}
