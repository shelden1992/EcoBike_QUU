package org.ecoBike.quu.repositiry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.model.*;
import org.ecoBike.quu.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ecoBike.quu.factory.FactoryBikes.getBike;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class FileRepository implements Repository {
    public static final String SPLIT_PATTERN = ";\\s";
    private static final Logger LOGGER = LogManager.getLogger(FileRepository.class);
    private static final List<Bike> TEMP_BIKE_LIST = Collections.synchronizedList(new ArrayList<>());
    private static List<Bike> writeBike = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<Bike> getTempList() {
        return TEMP_BIKE_LIST;
    }

    @Override
    public synchronized void writeToFile(String pathToFile) throws IOException {
        if (TEMP_BIKE_LIST.isEmpty()) return;
        try (FileWriter fileWriter = new FileWriter(pathToFile, true)) {
            TEMP_BIKE_LIST.forEach(bike -> {
                try {
                    fileWriter.write(bike.writeFormat());
                    fileWriter.flush();
                } catch (IOException e) {
                    LOGGER.error(LoggerMessage.OUTPUT_EXCEPTION.getName(), e);
                }
            });
        }
    }

    @Override
    public List<Bike> readAll(String path) throws IOException, NumberFormatException {
        writeBike = Stream.of(readFoldingBike(path), readElectroBike(path), readSpeedelecBike(path))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return writeBike;
    }

    @Override
    public List<Bike> getWriteBike(String path) throws IOException {
        return writeBike.isEmpty() ? readAll(path) : writeBike;
    }

    @Override
    public List<Bike> readFoldingBike(String pathToFile) throws IOException, NumberFormatException {
        List<Bike> foldingBike = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(pathToFile))) {
            String readLine;
            while ((readLine = fileReader.readLine()) != null) {
                if (readLine.contains(TypeBike.FOLDING_BIKE.getName())) {
                    String withOutType = StringUtils.replaceAllType(readLine);
                    foldingBike.add(getBike(FoldingBike.class, StringUtils.getTokenListByPattern(withOutType, SPLIT_PATTERN)));
                }
            }
        }
        return foldingBike;
    }

    @Override
    public List<Bike> readElectroBike(String pathToFile) throws IOException, NumberFormatException {
        List<Bike> electroBike = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(pathToFile))) {
            String readLine;
            while ((readLine = fileReader.readLine()) != null) {
                if (readLine.contains(TypeBike.E_BIKE.getName())) {
                    String withOutType = StringUtils.replaceAllType(readLine);
                    electroBike.add(getBike(ElectroBike.class, StringUtils.getTokenListByPattern(withOutType, SPLIT_PATTERN)));
                }
            }
        }
        return electroBike;
    }

    @Override
    public List<Bike> readSpeedelecBike(String pathToProject) throws IOException, NumberFormatException {
        List<Bike> speedlecBike = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(pathToProject))) {
            String readLine;
            while ((readLine = fileReader.readLine()) != null) {
                if (readLine.contains(TypeBike.SPEEDELEC.getName())) {
                    String withOutType = StringUtils.replaceAllType(readLine);
                    speedlecBike.add(getBike(SpeedelecBike.class, StringUtils.getTokenListByPattern(withOutType, SPLIT_PATTERN)));
                }
            }
        }
        return speedlecBike;
    }


    @Override
    public void addBikeToTempList(Bike bike) {
        TEMP_BIKE_LIST.add(bike);
    }
}
