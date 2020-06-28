package org.ecoBike.quu.repositiry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.*;
import org.ecoBike.quu.utils.StringUtils;

import java.io.*;
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
    public static final String ESC_CASE = "esc";
    private static final Logger LOGGER = LogManager.getLogger(FileRepository.class);
    private static final List<Bike> TEMP_BIKE_LIST = Collections.synchronizedList(new ArrayList<>());
    private static List<Bike> writeBike = new ArrayList<>();
    private static String pathToProject;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<Bike> getTempList() {
        return TEMP_BIKE_LIST;
    }

    public synchronized void writeToFile() throws IOException {
        if (TEMP_BIKE_LIST.isEmpty()) return;
        try (FileWriter fileWriter = new FileWriter(pathToProject, true)) {
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

    public List<Bike> readAll() throws IOException, NumberFormatException {
        writeBike = Stream.of(readFoldingBike(), readElectroBike(), readSpeedelecBike())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return writeBike;
    }

    public List<Bike> getWriteBike() {
        return writeBike;
    }

    @Override
    public List<Bike> readFoldingBike() throws IOException, NumberFormatException {
        List<Bike> foldingBike = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(pathToProject))) {
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
    public List<Bike> readElectroBike() throws IOException, NumberFormatException {
        List<Bike> electroBike = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(pathToProject))) {
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
    public List<Bike> readSpeedelecBike() throws IOException, NumberFormatException {
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
    public void setPath() {
        String inputString;
        StringUtils.writeText(Messages.ENTER_FILE_PATH.getMessage());
        FileFilter fileFilter = file -> file.exists() && file.isFile() && file.getName().endsWith("ecobike.txt");
        while (!(fileFilter.accept(new File(inputString = scanner.nextLine())) || ESC_CASE.equalsIgnoreCase(inputString))
        ) {
            StringUtils.writeErrorText(Messages.INVALID_ENTER_PATH.getMessage());
        }
        if (ESC_CASE.equalsIgnoreCase(inputString)) {
            LOGGER.debug("Exit status = 0");
            StringUtils.writeText(Messages.GOOD_BY.getMessage());
            System.exit(0);
        }
        pathToProject = inputString;
        StringUtils.writeText(Messages.PATH_SUCCESSFULLY.getMessage());
    }

    @Override
    public void addBikeToTempList(Bike bike) {
        TEMP_BIKE_LIST.add(bike);
    }
}
