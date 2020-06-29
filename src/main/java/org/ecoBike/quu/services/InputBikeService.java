package org.ecoBike.quu.services;

import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;
import org.ecoBike.quu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;

import static org.ecoBike.quu.factory.FactoryBikes.getBike;

/**
 * Created by Shelupets Denys on 27.06.2020.
 */
public class InputBikeService {
    public static final String PRESENCELIGHTS = "PRESENCELIGHTS";
    private static final String PRICE = "PRICE";
    private static final String NUMBERGEARS = "NUMBERGEARS";
    private static final String WEIGHTBIKE = "WEIGHTBIKE";
    private static final String SIZEWHEELS = "SIZEWHEELS";
    private static final String MAXIMUMSPEED = "MAXIMUMSPEED";
    private static final String BATTERYCAPACITY = "BATTERYCAPACITY";
    private static final String BRAND = "BRAND";

    private static final String CONFIRM_CASE = "y";
    private static final Logger LOGGER = LoggerFactory.getLogger(InputBikeService.class);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private List<String> getBikeToken(Field[] fields) throws NumberFormatException {
        List<String> tokenList = new ArrayList<>();
        Arrays.stream(fields).forEach(field -> {
            String fieldName = field.getName().toUpperCase();

            switch (fieldName) {
                case PRESENCELIGHTS:
                    addAvaibLights(tokenList);
                    break;
                case PRICE:
                    addValidToken(tokenList, "PRICE");
                    break;
                case NUMBERGEARS:
                    addValidToken(tokenList, "NUMBER OF GEARS");
                    break;
                case WEIGHTBIKE:
                    addValidToken(tokenList, "WEIGHT OF THE BIKE (IN GRAMS)");
                    break;
                case SIZEWHEELS:
                    addValidToken(tokenList, "SIZE OF THE WHEELS (IN INCH)");
                    break;
                case MAXIMUMSPEED:
                    addValidToken(tokenList, "MAXIMUM SPEED (IN KM/H)");
                    break;
                case BATTERYCAPACITY:
                    addValidToken(tokenList, "BATTERY CAPACITY (IN MAH)");
                    break;
                default:
                    addStringToken(tokenList, fieldName);
            }
        });
        return tokenList;
    }

    private void addStringToken(List<String> tokenList, String fieldName) {
        StringUtils.writeText(String.format("Enter %s", fieldName));
        try {
            tokenList.add(reader.readLine());
        } catch (IOException e) {
            StringUtils.writeText(Messages.INCORRECT_INPUT.getMessage());
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
        }
    }

    private void addAvaibLights(List<String> tokenList) {
        StringUtils.writeText(String.format("Enter %s", "IS AVAILABILITY OF LIGHTS AT FRONT?  Type y/n."));
        String isAvailbLights = null;
        try {
            isAvailbLights = CONFIRM_CASE.equalsIgnoreCase(reader.readLine()) ? "true" : "false";
        } catch (IOException e) {
            StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
        }
        tokenList.add(isAvailbLights);
    }

    private void addValidToken(List<String> tokenList, String filedName) {
        StringUtils.writeText(String.format("Enter %s", filedName));
        int token = -1;

        while (token <= 0) {
            try {
                token = Integer.parseInt(reader.readLine());

                if (token <= 0) {
                    StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
                }
            } catch (IOException | NumberFormatException e) {
                StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
                LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
            }
        }

        tokenList.add(String.valueOf(token));
    }

    public Bike getNewBike(Class classBike) throws NumberFormatException {
        List<String> tokenList = new ArrayList<>();
        Bike bike = null;
        String confirmCase = "";
        while (!(tokenList.size() == classBike.getDeclaredFields().length && CONFIRM_CASE.equalsIgnoreCase(confirmCase))) {
            tokenList.clear();
            tokenList = getBikeToken(classBike.getDeclaredFields());
            bike = getBike(classBike, tokenList);
            confirmCase = getConfirm(bike);
        }
        return bike;
    }

    private String getConfirm(Bike bike) {
        StringUtils.writeText(Objects.requireNonNull(bike).toString() + "\n");
        StringUtils.writeText(Messages.CONFIRM_INPUT.getMessage());
        String confirm = "";
        try {
            confirm = reader.readLine();
        } catch (IOException e) {
            StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
        }
        return confirm;
    }

    public Class findBikeType() {
        StringUtils.writeText(Messages.ENTER_NUMBER_OF_TYPE.getMessage());
        try {
            while (true) {
                switch (Integer.parseInt(reader.readLine())) {
                    case 1:
                        return ElectroBike.class;
                    case 2:
                        return FoldingBike.class;
                    case 3:
                        return SpeedelecBike.class;
                    default:
                        StringUtils.writeErrorText(Messages.ENTER_CORRECT_NUMBER.getMessage());
                }
            }
        } catch (NumberFormatException | InputMismatchException | IOException e) {
            StringUtils.writeErrorText(Messages.ENTER_CORRECT_NUMBER.getMessage());
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
        }
        return null;
    }
}
