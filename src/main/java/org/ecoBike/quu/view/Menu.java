package org.ecoBike.quu.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.bikeCatalog.*;
import org.ecoBike.quu.model.*;
import org.ecoBike.quu.messages.Error;
import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.utils.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static org.ecoBike.quu.factory.FactoryBikes.getBike;
import static org.ecoBike.quu.messages.Error.ERROR1;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class Menu {
    public static final String CONFIRM_CASE = "y";
    public static final String PRESENCELIGHTS = "PRESENCELIGHTS";
    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private static final String PRICE = "PRICE";
    private static final String NUMBERGEARS = "NUMBERGEARS";
    private static final String WEIGHTBIKE = "WEIGHTBIKE";
    private static final String SIZEWHEELS = "SIZEWHEELS";
    private static final String MAXIMUMSPEED = "MAXIMUMSPEED";
    private static final String BATTERYCAPACITY = "BATTERYCAPACITY";
    private static final Map<Integer, Messages> messagesMap;
    private static Scanner scanner;

    static {
        messagesMap = new HashMap<>();
        messagesMap.put(0, Messages.PLEASE_MAKE_CHOICE);
        messagesMap.put(1, Messages.SHOW_ENTIRE_ECOBIKE_CATALOG);
        messagesMap.put(2, Messages.ADD_NEW_FOLDING_BIKE);
        messagesMap.put(3, Messages.ADD_NEW_SPEEDELEC);
        messagesMap.put(4, Messages.ADD_NEW_E_BIKE);
        messagesMap.put(5, Messages.FIND_FIRST_ITEM_OF_PARTICULAR_BAND);
        messagesMap.put(6, Messages.WRITE_TO_FILE);
        messagesMap.put(7, Messages.STOP_PROGRAM);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void showMenu() {
        messagesMap.values().forEach(messages -> StringUtils.writeText(messages.getMessage()));
    }

    public static int makeMenuChoice() {
        int numberMenu = 0;
        try {
            scanner = new Scanner(System.in);
            while ((numberMenu = scanner.nextInt()) > 0
                    && numberMenu <= messagesMap.size() - 1) {
                return numberMenu;
            }
        } catch (RuntimeException e) {
            LOGGER.error(ERROR1.getName(), e);
        }
        return numberMenu;
    }

    public static void menuSwitcher(int numberMenu) {
        FoldinBikeCatalog foldinBikeCatalog = FoldinBikeCatalog.getInstance();
        SpeedelecBikeCatalog speedelecBikeCatalog = SpeedelecBikeCatalog.getInstance();
        ElectroBikeCatalog electroBikeCatalog = ElectroBikeCatalog.getInstance();
        List<Catalog> catalogList = Arrays.asList(foldinBikeCatalog, speedelecBikeCatalog, electroBikeCatalog);
        try {
            switch (numberMenu) {
                case 1:
                    BikeCatalog.getInstance().showWriteBikeCatalog();
                    LOGGER.info(LoggerMessage.SHOW_ALL_BIKE.getName());
                    break;
                case 2:
                    addFoldingBikeToCatalog(foldinBikeCatalog);
                    break;
                case 3:
                    addSpeedelecBikeToCatalog(speedelecBikeCatalog);
                    break;
                case 4:
                    addElectroBikeToCatalog(electroBikeCatalog);
                    break;
                case 5:
                    new Thread().start();
                    break;
                case 6:
                    writeIfTempCatalogsNotEmpty(catalogList);
                    break;
                case 7:
                    stopProgramIfTempCatalogsEmpty(catalogList);
            }
        } catch (IOException e) {
            LOGGER.error(Messages.INCORRECT_INPUT.getMessage(), e);
            StringUtils.writeText(Messages.INCORRECT_INPUT.getMessage());
        }
    }

    private static void addElectroBikeToCatalog(ElectroBikeCatalog electroBikeCatalog) {
        Bike electroBike = getNewBike(TypeBike.E_BIKE, ElectroBike.Builder.class.getDeclaredFields());
        electroBikeCatalog.addNewBikeToTempCatalog(electroBike);
        LOGGER.info(LoggerMessage.ADD_NEW_ELECTRO_BIKE.getName());
    }

    private static void addSpeedelecBikeToCatalog(SpeedelecBikeCatalog speedelecBikeCatalog) {
        Bike speedelecBike = getNewBike(TypeBike.SPEEDELEC, SpeedelecBike.Builder.class.getDeclaredFields());
        speedelecBikeCatalog.addNewBikeToTempCatalog(speedelecBike);
        LOGGER.info(LoggerMessage.ADD_NEW_SPEEDELEC_BIKE.getName());
    }

    private static void addFoldingBikeToCatalog(FoldinBikeCatalog foldinBikeCatalog) {
        Bike foldingBike = getNewBike(TypeBike.FOLDING_BIKE, FoldingBike.Builder.class.getDeclaredFields());
        foldinBikeCatalog.addNewBikeToTempCatalog(foldingBike);
        LOGGER.info(LoggerMessage.ADD_NEW_FOLDING_BIKE.getName());
    }

    private static void stopProgramIfTempCatalogsEmpty(List<Catalog> catalogList) {
        if (catalogList.stream().anyMatch(catalog -> !(catalog.getTempCatalog().isEmpty()))) {
            StringUtils.writeText(Messages.YOU_SURE_GO_OUT.getMessage());

            if (CONFIRM_CASE.equalsIgnoreCase(scanner.next())) {
                closeScannerAndExit();
            }
        } else {
            closeScannerAndExit();
        }
    }

    private static void writeIfTempCatalogsNotEmpty(List<Catalog> catalogList) {
        if (catalogList.stream().allMatch(Catalog::tempCatalogIsEmpty)) {
            StringUtils.writeText(Messages.NOTHING_WRITE.getMessage());
        } else {
            catalogList.forEach(catalog -> {

                try {
                    catalog.writeAndClearTempCatalog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            StringUtils.writeText(Messages.WRITE_SUCCESSFULLY.getMessage());
            LOGGER.info(LoggerMessage.WRITE_TO_FILE.getName());
        }
    }

    private static void closeScannerAndExit() {
        StringUtils.writeText(Messages.GOOD_BY.getMessage());
        scanner.close();
        System.exit(0);
    }

    public static Bike getNewBike(TypeBike typeBike, Field[] declaredFields) {
        List<String> bikeParams = new ArrayList<>();
        Bike bike = null;
        scanner = new Scanner(System.in);
        try {
            String confirmCase = "";
            while (!(bikeParams.size() == declaredFields.length && CONFIRM_CASE.equalsIgnoreCase(confirmCase))) {
                bikeParams.clear();
                getBikeParameters(declaredFields, bikeParams);
                bike = getBike(typeBike, bikeParams);
                StringUtils.writeText(Objects.requireNonNull(bike).showInfoForBike() + "\n");
                StringUtils.writeText(Messages.CONFIRM_INPUT.getMessage());
                confirmCase = scanner.next();
            }
            StringUtils.writeText(Messages.ADDED_SUCCESSFULLY.getMessage());
            return bike;
        } catch (Exception e) {
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
            StringUtils.writeText(Messages.INCORRECT_INPUT.getMessage());
        }
        return bike;
    }

    private static void getBikeParameters(Field[] declaredFields, List<String> bikeParams) {
        Arrays.stream(declaredFields).forEach(field -> {
            String filedName = field.getName().toUpperCase();
            switch (filedName) {
                case PRESENCELIGHTS:
                    StringUtils.writeText(String.format("Enter %s", "IS AVAILABILITY OF LIGHTS AT FRONT?  Type y/n."));
                    String isAvailbLights = CONFIRM_CASE.equalsIgnoreCase(scanner.next()) ? "true" : "false";
                    bikeParams.add(isAvailbLights);
                    break;
                case PRICE:
                    addValidParamet(bikeParams, filedName);
                    break;
                case NUMBERGEARS:
                    addValidParamet(bikeParams, "NUMBER OF GEARS");
                    break;
                case WEIGHTBIKE:
                    addValidParamet(bikeParams, "WEIGHT OF THE BIKE (IN GRAMS)");
                    break;
                case SIZEWHEELS:
                    addValidParamet(bikeParams, "SIZE OF THE WHEELS (IN INCH)");
                    break;
                case MAXIMUMSPEED:
                    addValidParamet(bikeParams, "MAXIMUM SPEED (IN KM/H)");
                    break;
                case BATTERYCAPACITY:
                    addValidParamet(bikeParams, "BATTERY CAPACITY (IN MAH)");
                    break;
                default:
                    StringUtils.writeText(String.format("Enter %s", filedName));
                    bikeParams.add(scanner.next());
            }
        });
    }

    private static void addValidParamet(List<String> bikeParams, String filedName) {
        StringUtils.writeText(String.format("Enter %s", filedName));
        int param = scanner.nextInt();
        if (param <= 0) {
            throw new NumberFormatException(Error.ERROR2.getName());
        }
        bikeParams.add(String.valueOf(param));
    }
}
