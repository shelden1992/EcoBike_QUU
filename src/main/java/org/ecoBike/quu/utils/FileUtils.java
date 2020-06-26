package org.ecoBike.quu.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.TypeBike;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.ecoBike.quu.factory.FactoryBikes.getBike;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class FileUtils {
    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);
    private static final String PATH_TO_PROJECT = "data" + getSeparator() + "ecobike.txt";


    private static String getSeparator() {
        String osName = System.getProperty("os.name");
        String separator;

        if (osName.contains("Mac") || osName.contains("OS X") || osName.contains("Linux")) {
            separator = "/";
        } else {
            separator = "\\";
        }
        return separator;
    }

    public static Map<TypeBike, List<Bike>> readAllBike() throws IOException {
        Map<TypeBike, List<Bike>> allBikes = new HashMap<>();
        List<Bike> speedelecBikes = new ArrayList<>();
        List<Bike> eBikes = new ArrayList<>();
        List<Bike> foldingBikes = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(PATH_TO_PROJECT))) {

            String readString;
            while ((readString = fileReader.readLine()) != null) {
                if (readString.contains(TypeBike.SPEEDELEC.getName())) {
                    speedelecBikes.add(getBike(TypeBike.SPEEDELEC, getTokenList(readString)));
                } else if (readString.contains(TypeBike.E_BIKE.getName())) {
                    eBikes.add(getBike(TypeBike.E_BIKE, getTokenList(readString)));
                } else if (readString.contains(TypeBike.FOLDING_BIKE.getName())) {
                    foldingBikes.add(getBike(TypeBike.FOLDING_BIKE, getTokenList(readString)));
                }
            }
            allBikes.put(TypeBike.SPEEDELEC, speedelecBikes);
            allBikes.put(TypeBike.E_BIKE, eBikes);
            allBikes.put(TypeBike.FOLDING_BIKE, foldingBikes);

        }
        return allBikes;
    }

    private static List<String> getTokenList(String readString) {
        String replaceType = StringUtils.replaceAllType(readString);
        String[] split = replaceType.split(";\\s");
        return Arrays.stream(split).map(String::trim).collect(Collectors.toList());
    }

    public static void writeToFile(List<Bike> list) throws IOException {
        if (list.isEmpty()) return;
        try (FileWriter fileWriter = new FileWriter(PATH_TO_PROJECT, true)) {
            list.forEach(bike -> {
                try {
                    fileWriter.write(bike.toString());
                    fileWriter.flush();
                } catch (IOException e) {
                    LOGGER.error("Output exception", e);
                }
            });

        }

    }
}
