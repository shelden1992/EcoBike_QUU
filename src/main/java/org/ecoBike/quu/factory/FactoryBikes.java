package org.ecoBike.quu.factory;

import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;

import java.util.List;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class FactoryBikes {

    public static final String SPEEDELEC_BIKE = "SpeedelecBike";
    public static final String ELECTRO_BIKE = "ElectroBike";
    public static final String FOLDING_BIKE = "FoldingBike";

    public static Bike getBike(Class classBike, List<String> tokenList) throws NumberFormatException {
        if (SPEEDELEC_BIKE.equalsIgnoreCase(classBike.getSimpleName())) {
            return newSpeedelecBike(tokenList);
        } else if (ELECTRO_BIKE.equalsIgnoreCase(classBike.getSimpleName())) {
            return newElectroBike(tokenList);
        } else if (FOLDING_BIKE.equalsIgnoreCase(classBike.getSimpleName())) {
            return newFoldingBike(tokenList);
        }
        return null;


    }

    private static FoldingBike newFoldingBike(List<String> tokenList) throws NumberFormatException {
        return FoldingBike.newBuilder()
                .brand(tokenList.get(0))
                .sizeWheels(Integer.parseInt(tokenList.get(1)))
                .numberGears(Integer.parseInt(tokenList.get(2)))
                .weightBike(Integer.parseInt(tokenList.get(3)))
                .isPresenceLights(Boolean.parseBoolean(tokenList.get(4)))
                .color(tokenList.get(5))
                .price(Integer.parseInt(tokenList.get(6)))
                .build();
    }

    private static ElectroBike newElectroBike(List<String> tokenList) throws NumberFormatException {
        return ElectroBike.newBuilder()
                .brand(tokenList.get(0))
                .maximumSpeed(Integer.parseInt(tokenList.get(1)))
                .weightBike(Integer.parseInt(tokenList.get(2)))
                .isPresenceLights(Boolean.parseBoolean(tokenList.get(3)))
                .batteryCapacity(Integer.parseInt(tokenList.get(4)))
                .color(tokenList.get(5))
                .price(Integer.parseInt(tokenList.get(6)))
                .build();
    }

    private static SpeedelecBike newSpeedelecBike(List<String> tokenList) throws NumberFormatException {
        return SpeedelecBike.newBuilder()
                .brand(tokenList.get(0))
                .maximumSpeed(Integer.parseInt(tokenList.get(1)))
                .weightBike(Integer.parseInt(tokenList.get(2)))
                .isPresenceLights(Boolean.parseBoolean(tokenList.get(3)))
                .batteryCapacity(Integer.parseInt(tokenList.get(4)))
                .color(tokenList.get(5))
                .price(Integer.parseInt(tokenList.get(6)))
                .build();
    }
}
