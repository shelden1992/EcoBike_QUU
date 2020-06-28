package org.ecoBike.quu.services;

import org.ecoBike.quu.messages.Error;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String CONFIRM_CASE = "y";
    private static final Logger LOGGER = LoggerFactory.getLogger(InputBikeService.class);
    private Scanner scanner = new Scanner(System.in);

    private void getBikeToken(Field[] fields, List<String> tokenList) throws NumberFormatException {
        Arrays.stream(fields).forEach(field -> {
            String filedName = field.getName().toUpperCase();
            switch (filedName) {
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
                    StringUtils.writeText(String.format("Enter %s", filedName));
                    tokenList.add(scanner.next());
            }
        });
    }

    private void addAvaibLights(List<String> tokenList) {
        StringUtils.writeText(String.format("Enter %s", "IS AVAILABILITY OF LIGHTS AT FRONT?  Type y/n."));
        String isAvailbLights = CONFIRM_CASE.equalsIgnoreCase(scanner.next()) ? "true" : "false";
        tokenList.add(isAvailbLights);
    }

    private void addValidToken(List<String> tokenList, String filedName) throws NumberFormatException {
        StringUtils.writeText(String.format("Enter %s", filedName));
        int token = scanner.nextInt();
        if (token <= 0) {
            throw new NumberFormatException(Error.ERROR2.getName());
        }
        tokenList.add(String.valueOf(token));
    }

    public Bike getNewBike(Class classBike) throws NumberFormatException {
        List<String> tokenList = new ArrayList<>();
        Bike bike = null;

        String confirmCase = "";
        while (!(tokenList.size() == classBike.getDeclaredFields().length && CONFIRM_CASE.equalsIgnoreCase(confirmCase))) {
            tokenList.clear();
            getBikeToken(classBike.getDeclaredFields(), tokenList);
            bike = getBike(classBike, tokenList);
            confirmCase = getConfirm(bike);
        }
        StringUtils.writeText(Messages.ADDED_SUCCESSFULLY.getMessage());
        return bike;
    }

    private String getConfirm(Bike bike) {
        StringUtils.writeText(Objects.requireNonNull(bike).toString() + "\n");
        StringUtils.writeText(Messages.CONFIRM_INPUT.getMessage());
        return scanner.next();
    }
}
